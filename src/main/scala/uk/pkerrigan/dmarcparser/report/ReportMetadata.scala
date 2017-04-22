package uk.pkerrigan.dmarcparser.report

case class ReportMetadata(orgName: String, email: String, extraContactInfo: String, reportId: String, dateRange: DateRange, errors: List[String])