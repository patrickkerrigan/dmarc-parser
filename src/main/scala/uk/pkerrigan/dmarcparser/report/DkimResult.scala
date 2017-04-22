package uk.pkerrigan.dmarcparser.report

sealed trait DkimResult {def value: String}
case object DkimResultNone extends DkimResult {val value="none"}
case object DkimResultPass extends DkimResult {val value="pass"}
case object DkimResultFail extends DkimResult {val value="fail"}
case object DkimResultPolicy extends DkimResult {val value="policy"}
case object DkimResultNeutral extends DkimResult {val value="neutral"}
case object DkimResultTempError extends DkimResult {val value="temperror"}
case object DkimResultPermError extends DkimResult {val value="permerror"}

object DkimResult {
  def fromString(text: String): DkimResult = text match {
    case t if t.equalsIgnoreCase(DkimResultNone.value) => DkimResultNone
    case t if t.equalsIgnoreCase(DkimResultPass.value) => DkimResultPass
    case t if t.equalsIgnoreCase(DkimResultFail.value) => DkimResultFail
    case t if t.equalsIgnoreCase(DkimResultPolicy.value) => DkimResultPolicy
    case t if t.equalsIgnoreCase(DkimResultNeutral.value) => DkimResultNeutral
    case t if t.equalsIgnoreCase(DkimResultTempError.value) => DkimResultTempError
    case t if t.equalsIgnoreCase(DkimResultPermError.value) => DkimResultPermError
    case _ => DkimResultNone
  }
}