package uk.pkerrigan.dmarcparser.report

case class DkimAuthResult(domain: String, selector: String, result: DkimResult, humanResult: String)