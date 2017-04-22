package uk.pkerrigan.dmarcparser

import org.scalatest.FlatSpec
import uk.pkerrigan.dmarcparser.report._

import scala.io.Source

class ParserSpec extends FlatSpec {

  val parser = new Parser
  val feedback: Feedback = parser.parse(Source.fromResource("dmarc-1.xml").mkString)
  val untrimmedFeedback: Feedback = parser.parse(Source.fromResource("dmarc-1-untrimmed.xml").mkString)

  "A valid xml report" should "produce a valid Feedback object" in {
    assert(feedback.getClass == classOf[Feedback])
  }

  it should "have its metadata extracted" in {
    assert(feedback.reportMetadata.orgName == "Test org")
    assert(feedback.reportMetadata.email == "noreply@example.com")
    assert(feedback.reportMetadata.extraContactInfo == "https://example.org/dmarc")
    assert(feedback.reportMetadata.reportId == "123456")
    assert(feedback.reportMetadata.dateRange.begin == 1491782400)
    assert(feedback.reportMetadata.dateRange.end == 1491868799)
  }

  it should "have its published policy extracted" in {
    assert(feedback.policyPublished.domain == "patrickkerrigan.uk")
    assert(feedback.policyPublished.dkimAlignment == AlignmentStrict)
    assert(feedback.policyPublished.spfAlignment == AlignmentRelaxed)
    assert(feedback.policyPublished.domainPolicy == DispositionReject)
    assert(feedback.policyPublished.subdomainPolicy == DispositionNone)
    assert(feedback.policyPublished.percentage == 100)
  }

  it should "have its records extracted" in {
    val records = List(
      Record(Row("192.168.1.43", 8, PolicyEvaluated(DispositionReject, DmarcResultFail, DmarcResultFail, List())), Identifier("", "", "patrickkerrigan.uk"), AuthResult(List(), List(SpfAuthResult("patrickkerrigan.uk", SpfDomainScopeMfrom, SpfResultFail)))),
      Record(Row("192.168.34.78", 1, PolicyEvaluated(DispositionNone, DmarcResultPass, DmarcResultPass, List())), Identifier("", "", "patrickkerrigan.uk"), AuthResult(List(DkimAuthResult("patrickkerrigan.uk", "mail", DkimResultPass, "")), List(SpfAuthResult("patrickkerrigan.uk", SpfDomainScopeMfrom, SpfResultPass)))),
      Record(Row("192.168.23.53", 1, PolicyEvaluated(DispositionNone, DmarcResultPass, DmarcResultPass, List())), Identifier("", "", "patrickkerrigan.uk"), AuthResult(List(DkimAuthResult("patrickkerrigan.uk", "mail", DkimResultPass, "")), List(SpfAuthResult("patrickkerrigan.uk", SpfDomainScopeMfrom, SpfResultPass))))
    )

    assert(feedback.records == records)
  }

  "An xml report with untrimmed values" should "produce a valid Feedback object" in {
    assert(untrimmedFeedback.getClass == classOf[Feedback])
  }

  it should "have its metadata extracted" in {
    assert(untrimmedFeedback.reportMetadata.orgName == "Test org")
    assert(untrimmedFeedback.reportMetadata.email == "noreply@example.com")
    assert(untrimmedFeedback.reportMetadata.extraContactInfo == "https://example.org/dmarc")
    assert(untrimmedFeedback.reportMetadata.reportId == "123456")
    assert(untrimmedFeedback.reportMetadata.dateRange.begin == 1491782400)
    assert(untrimmedFeedback.reportMetadata.dateRange.end == 1491868799)
  }

  it should "have its published policy extracted" in {
    assert(untrimmedFeedback.policyPublished.domain == "patrickkerrigan.uk")
    assert(untrimmedFeedback.policyPublished.dkimAlignment == AlignmentStrict)
    assert(untrimmedFeedback.policyPublished.spfAlignment == AlignmentRelaxed)
    assert(untrimmedFeedback.policyPublished.domainPolicy == DispositionReject)
    assert(untrimmedFeedback.policyPublished.subdomainPolicy == DispositionNone)
    assert(untrimmedFeedback.policyPublished.percentage == 100)
  }

  it should "have its records extracted" in {
    val records = List(
      Record(Row("192.168.1.43", 8, PolicyEvaluated(DispositionReject, DmarcResultFail, DmarcResultFail, List())), Identifier("", "", "patrickkerrigan.uk"), AuthResult(List(), List(SpfAuthResult("patrickkerrigan.uk", SpfDomainScopeMfrom, SpfResultFail)))),
      Record(Row("192.168.34.78", 1, PolicyEvaluated(DispositionNone, DmarcResultPass, DmarcResultPass, List())), Identifier("", "", "patrickkerrigan.uk"), AuthResult(List(DkimAuthResult("patrickkerrigan.uk", "mail", DkimResultPass, "")), List(SpfAuthResult("patrickkerrigan.uk", SpfDomainScopeMfrom, SpfResultPass)))),
      Record(Row("192.168.23.53", 1, PolicyEvaluated(DispositionNone, DmarcResultPass, DmarcResultPass, List())), Identifier("", "", "patrickkerrigan.uk"), AuthResult(List(DkimAuthResult("patrickkerrigan.uk", "mail", DkimResultPass, "")), List(SpfAuthResult("patrickkerrigan.uk", SpfDomainScopeMfrom, SpfResultPass))))
    )

    assert(feedback.records == records)
  }

  "A space prefixed valid xml report" should "produce a valid Feedback object" in {
    val feedback: Feedback = parser.parse(Source.fromResource("dmarc-1-space.xml").mkString)
    assert(feedback.getClass == classOf[Feedback])
  }

}
