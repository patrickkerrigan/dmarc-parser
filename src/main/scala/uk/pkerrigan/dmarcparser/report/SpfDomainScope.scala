package uk.pkerrigan.dmarcparser.report

sealed trait SpfDomainScope {def value: String}
case object SpfDomainScopeHelo extends SpfDomainScope {val value="helo"}
case object SpfDomainScopeMfrom extends SpfDomainScope {val value="mfrom"}

object SpfDomainScope {
  def fromString(text: String): SpfDomainScope = text match {
    case t if t.equalsIgnoreCase(SpfDomainScopeHelo.value) => SpfDomainScopeHelo
    case t if t.equalsIgnoreCase(SpfDomainScopeMfrom.value) => SpfDomainScopeMfrom
    case _ => SpfDomainScopeMfrom
  }
}