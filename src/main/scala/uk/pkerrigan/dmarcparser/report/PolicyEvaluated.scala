package uk.pkerrigan.dmarcparser.report

case class PolicyEvaluated(disposition: Disposition, dkim: DmarcResult, spf: DmarcResult, reasons: List[PolicyOverrideReason])