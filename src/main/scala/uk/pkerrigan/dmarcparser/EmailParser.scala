package uk.pkerrigan.dmarcparser

import java.io.ByteArrayInputStream
import java.nio.charset.CodingErrorAction
import java.util.Properties
import java.util.zip.{GZIPInputStream, ZipInputStream}
import javax.activation.DataSource
import javax.mail.Session
import javax.mail.internet.MimeMessage

import org.apache.commons.mail.util.MimeMessageParser
import uk.pkerrigan.dmarcparser.report.Feedback

import scala.io._

class EmailParser(parser: ParserTrait = new Parser()) extends EmailParserTrait{

  implicit val codec = Codec("UTF-8")
  codec.onMalformedInput(CodingErrorAction.REPLACE)
  codec.onUnmappableCharacter(CodingErrorAction.REPLACE)

  def parseEmail(email: String): Option[Feedback] = {
    val s = Session.getDefaultInstance(new Properties())
    val is = new ByteArrayInputStream(email.getBytes)
    val message = new MimeMessage(s, is)
    val messageParser = new MimeMessageParser(message).parse()

    if (messageParser.getAttachmentList.isEmpty)
      None
    else
      extract(messageParser.getAttachmentList.get(0))
  }

  private def extract(a: DataSource): Option[Feedback] = a match {
    case `a` if a.getContentType.equals("application/gzip") => extractGzip(a)
    case `a` if a.getContentType.equals("application/x-gzip") => extractGzip(a)
    case `a` if a.getContentType.equals("application/zip") => extractZip(a)
    case `a` if a.getContentType.equals("application/x-zip-compressed") => extractZip(a)
    case _ => None
  }

  private def extractZip(a: DataSource): Option[Feedback] = {
    val zip = new ZipInputStream(a.getInputStream)
    zip.getNextEntry
    val rawXml = Source.fromInputStream(zip).mkString
    if (rawXml == "") None else Some(parser.parse(rawXml))
  }
  private def extractGzip(a: DataSource): Option[Feedback] = {
    val zip = new GZIPInputStream(a.getInputStream)
    val rawXml = Source.fromInputStream(zip).mkString
    if (rawXml == "") None else Some(parser.parse(rawXml))
  }
}
