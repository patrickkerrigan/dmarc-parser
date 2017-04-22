package uk.pkerrigan.dmarcparser.report

case class SpfAuthResult(domain: String, scope: SpfDomainScope, result: SpfResult)