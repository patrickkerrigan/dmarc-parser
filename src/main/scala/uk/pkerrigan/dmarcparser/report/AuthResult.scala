package uk.pkerrigan.dmarcparser.report

case class AuthResult(dkim: List[DkimAuthResult], spf: List[SpfAuthResult])