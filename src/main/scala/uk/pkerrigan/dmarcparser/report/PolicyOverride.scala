package uk.pkerrigan.dmarcparser.report

sealed trait PolicyOverride {def value: String}
case object PolicyOverrideForwarded extends PolicyOverride {val value="forwarded"}
case object PolicyOverrideSampledOut extends PolicyOverride {val value="sampled_out"}
case object PolicyOverrideTrustedForwarder extends PolicyOverride {val value="trusted_forwarder"}
case object PolicyOverrideMailingList extends PolicyOverride {val value="mailing_list"}
case object PolicyOverrideLocalPolicy extends PolicyOverride {val value="local_policy"}
case object PolicyOverrideOther extends PolicyOverride {val value="other"}

object PolicyOverride {
  def fromString(text: String): PolicyOverride = text match {
    case t if t.equalsIgnoreCase(PolicyOverrideForwarded.value) => PolicyOverrideForwarded
    case t if t.equalsIgnoreCase(PolicyOverrideSampledOut.value) => PolicyOverrideSampledOut
    case t if t.equalsIgnoreCase(PolicyOverrideTrustedForwarder.value) => PolicyOverrideTrustedForwarder
    case t if t.equalsIgnoreCase(PolicyOverrideMailingList.value) => PolicyOverrideMailingList
    case t if t.equalsIgnoreCase(PolicyOverrideLocalPolicy.value) => PolicyOverrideLocalPolicy
    case t if t.equalsIgnoreCase(PolicyOverrideOther.value) => PolicyOverrideOther
    case _ => PolicyOverrideOther
  }
}