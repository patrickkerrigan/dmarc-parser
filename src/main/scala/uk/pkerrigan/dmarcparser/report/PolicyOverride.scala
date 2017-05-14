package uk.pkerrigan.dmarcparser.report

sealed trait PolicyOverride {def value: String}
case object PolicyOverrideForwarded extends PolicyOverride {val value="forwarded"}
case object PolicyOverrideSampledOut extends PolicyOverride {val value="sampled_out"}
case object PolicyOverrideTrustedForwarder extends PolicyOverride {val value="trusted_forwarder"}
case object PolicyOverrideMailingList extends PolicyOverride {val value="mailing_list"}
case object PolicyOverrideLocalPolicy extends PolicyOverride {val value="local_policy"}
case object PolicyOverrideOther extends PolicyOverride {val value="other"}

object PolicyOverride {
  def fromString(text: String): PolicyOverride = text.toLowerCase match {
    case PolicyOverrideForwarded.value => PolicyOverrideForwarded
    case PolicyOverrideSampledOut.value => PolicyOverrideSampledOut
    case PolicyOverrideTrustedForwarder.value => PolicyOverrideTrustedForwarder
    case PolicyOverrideMailingList.value => PolicyOverrideMailingList
    case PolicyOverrideLocalPolicy.value => PolicyOverrideLocalPolicy
    case PolicyOverrideOther.value => PolicyOverrideOther
    case _ => PolicyOverrideOther
  }
}