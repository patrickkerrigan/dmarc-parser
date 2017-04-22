package uk.pkerrigan.dmarcparser

import org.scalatest.FlatSpec
import uk.pkerrigan.dmarcparser.report._

import scala.io.Source

class EmailParserSpec extends FlatSpec with ParserTrait {

  val emailParser = new EmailParser(this)
  var passedToParser: String = ""

  "An email with a zip file" should "invoke the DMARC parser with unzipped contents" in {
    emailParser.parseEmail(Source.fromResource("test.zip.eml").mkString)
    assert(passedToParser == "Test zip file")
  }

  "An email with a gzip file" should "invoke the DMARC parser with unzipped contents" in {
    emailParser.parseEmail(Source.fromResource("test.gz.eml").mkString)
    assert(passedToParser == "Test gzip file")
  }

  override def parse(rawReport: String): Feedback = {
    passedToParser = rawReport.trim
    Feedback(1.0, ReportMetadata("", "", "", "", DateRange(1,1), List()), PolicyPublished("", AlignmentRelaxed, AlignmentRelaxed, DispositionNone, DispositionNone, 0, ""), List())
  }
}
