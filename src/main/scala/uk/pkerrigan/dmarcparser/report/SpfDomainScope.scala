package uk.pkerrigan.dmarcparser.report

sealed trait SpfDomainScope {def value: String}
case object SpfDomainScopeHelo extends SpfDomainScope {val value="helo"}
case object SpfDomainScopeMfrom extends SpfDomainScope {val value="mfrom"}

object SpfDomainScope {
  def fromString(text: String): SpfDomainScope = text.toLowerCase match {
    case SpfDomainScopeHelo.value => SpfDomainScopeHelo
    case SpfDomainScopeMfrom.value => SpfDomainScopeMfrom
    case _ => SpfDomainScopeMfrom
  }
}