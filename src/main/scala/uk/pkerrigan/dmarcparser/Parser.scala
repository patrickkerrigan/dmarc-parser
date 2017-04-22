package uk.pkerrigan.dmarcparser

import javax.xml.parsers.{SAXParser, SAXParserFactory}

import uk.pkerrigan.dmarcparser.report._

import scala.language.postfixOps
import scala.xml.{Elem, Node, XML}

class Parser extends ParserTrait {

  private def extractDateRange(xml: Node): DateRange = {
    val dateRange = xml \ "date_range" head;
    DateRange((dateRange \ "begin" text).trim.toLong, (dateRange \ "end" text).trim.toLong)
  }

  private def extractErrors(xml: Node): List[String] =
    xml \ "error" map (_.text.trim) toList


  private def extractMetaData(xml: Elem): ReportMetadata = {
    val meta = xml \ "report_metadata" head;
    ReportMetadata(
      (meta \ "org_name" text).trim,
      (meta \ "email" text).trim,
      (meta \ "extra_contact_info" text).trim,
      (meta \ "report_id" text).trim,
      extractDateRange(meta),
      extractErrors(meta))
  }

  private def extractPolicyPublished(xml: Elem): PolicyPublished = {
    val policy = xml \ "policy_published" head;
    PolicyPublished(
      (policy \ "domain" text).trim,
      Alignment.fromString((policy \ "adkim" text).trim),
      Alignment.fromString((policy \ "aspf" text).trim),
      Disposition.fromString((policy \ "p" text).trim),
      Disposition.fromString((policy \ "sp" text).trim),
      (policy \ "pct" text).trim.toInt,
      (policy \ "fo" text).trim
    )
  }

  private def extractIdentifiers(xml: Node): Identifier = {
    val identifiers = xml \ "identifiers" head;
    Identifier(
      (identifiers \ "envelope_to" text).trim,
      (identifiers \ "envelope_from" text).trim,
      (identifiers \ "header_from" text).trim
    )
  }

  private def extractPolicyOverrideReason(xml: Node): PolicyOverrideReason = {
    PolicyOverrideReason(
      PolicyOverride.fromString((xml \ "type" text).trim),
      (xml \ "comment" text).trim
    )
  }

  private def extractPolicyOverrideReasons(xml: Node): List[PolicyOverrideReason] =
    xml \ "reason" map extractPolicyOverrideReason toList

  private def extractPolicyEvaluated(xml: Node): PolicyEvaluated = {
    val policy = xml \ "policy_evaluated" head;
    PolicyEvaluated(
      Disposition.fromString((policy \ "disposition" text).trim),
      DmarcResult.fromString((policy \ "dkim" text).trim),
      DmarcResult.fromString((policy \ "spf" text).trim),
      extractPolicyOverrideReasons(policy)
    )
  }

  private def extractRow(xml: Node): Row = {
    val row = xml \ "row" head;
    Row(
      (row \ "source_ip" text).trim,
      (row \ "count" text).trim.toInt,
      extractPolicyEvaluated(row)
    )
  }

  private def extractAuthResults(xml: Node): AuthResult = {
    val auth = xml \ "auth_results" head;
    AuthResult(
      extractDkimResults(auth),
      extractSpfResults(auth)
    )
  }

  private def extractDkimResult(xml: Node): DkimAuthResult = {
    DkimAuthResult(
      (xml \ "domain" text).trim,
      (xml \ "selector" text).trim,
      DkimResult.fromString((xml \ "result" text).trim),
      (xml \ "human_result" text).trim
    )
  }

  private def extractDkimResults(xml: Node): List[DkimAuthResult] =
    xml \ "dkim" map extractDkimResult toList


  private def extractSpfResult(xml: Node): SpfAuthResult = {
    SpfAuthResult(
      (xml \ "domain" text).trim,
      SpfDomainScope.fromString((xml \ "scope" text).trim),
      SpfResult.fromString((xml \ "result" text).trim)
    )
  }

  private def extractSpfResults(xml: Node): List[SpfAuthResult] =
    xml \ "spf" map extractSpfResult toList


  private def extractRecord(xml: Node):Record = {
    Record(
      extractRow(xml),
      extractIdentifiers(xml),
      extractAuthResults(xml)
    )
  }

  private def extractRecords(xml: Elem): List[Record] =
    xml \ "record" map extractRecord toList


  def parse(rawReport: String): Feedback = {
    val xml = XML.withSAXParser(buildXmlParser()).loadString(rawReport.substring(rawReport.indexOf("<")))
    Feedback(
      1.0,
      extractMetaData(xml),
      extractPolicyPublished(xml),
      extractRecords(xml)
    )
  }

  private def buildXmlParser(): SAXParser = {
    val f = SAXParserFactory.newInstance()
    f.setNamespaceAware(false)
    f.setFeature("http://xml.org/sax/features/external-general-entities", false)
    f.setFeature("http://xml.org/sax/features/external-parameter-entities", false)
    f.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true)
    f.newSAXParser()
  }
}
