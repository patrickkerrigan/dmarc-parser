package uk.pkerrigan.dmarcparser

import org.scalatest.{BeforeAndAfter, FlatSpec}
import uk.pkerrigan.dmarcparser.report._

import scala.io.Source

class EmailParserSpec extends FlatSpec with BeforeAndAfter with ParserTrait {

  val dummyFeedback = Feedback(1.0, ReportMetadata("", "", "", "", DateRange(1,1), List()), PolicyPublished("", AlignmentRelaxed, AlignmentRelaxed, DispositionNone, DispositionNone, 0, ""), List())
  val emailParser = new EmailParser(this)
  var passedToParser: String = ""

  before {
    passedToParser = ""
  }

  "An email with a zip file" should "invoke the DMARC parser with unzipped contents" in {
    val result = emailParser.parseEmail(Source.fromResource("test.zip.eml").mkString)
    assert(passedToParser == "Test zip file")
    assert(result.get == dummyFeedback)
  }

  "An email with a gzip file" should "invoke the DMARC parser with unzipped contents" in {
    val result = emailParser.parseEmail(Source.fromResource("test.gz.eml").mkString)
    assert(passedToParser == "Test gzip file")
    assert(result.get == dummyFeedback)
  }

  "An email with a an unrecognised attachment" should "not invoke the DMARC parser" in {
    val result = emailParser.parseEmail(Source.fromResource("test.txt.eml").mkString)
    assert(passedToParser == "")
    assert(result.isEmpty)
  }

  "An email with no attachment" should "not invoke the DMARC parser" in {
    val result = emailParser.parseEmail(Source.fromResource("test.none.eml").mkString)
    assert(passedToParser == "")
    assert(result.isEmpty)
  }

  override def parse(rawReport: String): Feedback = {
    passedToParser = rawReport.trim
    dummyFeedback
  }
}
