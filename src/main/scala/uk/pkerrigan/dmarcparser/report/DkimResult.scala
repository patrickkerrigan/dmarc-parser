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
  def fromString(text: String): DkimResult = text.toLowerCase match {
    case DkimResultNone.value => DkimResultNone
    case DkimResultPass.value => DkimResultPass
    case DkimResultFail.value => DkimResultFail
    case DkimResultPolicy.value => DkimResultPolicy
    case DkimResultNeutral.value => DkimResultNeutral
    case DkimResultTempError.value => DkimResultTempError
    case DkimResultPermError.value => DkimResultPermError
    case _ => DkimResultNone
  }
}