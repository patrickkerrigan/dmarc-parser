package uk.pkerrigan.dmarcparser.report

case class Feedback(version: Double, reportMetadata: ReportMetadata, policyPublished: PolicyPublished, records: List[Record])