package uk.pkerrigan.dmarcparser.report

sealed trait SpfResult {def value: String}
case object SpfResultNone extends SpfResult {val value="none"}
case object SpfResultPass extends SpfResult {val value="pass"}
case object SpfResultFail extends SpfResult {val value="fail"}
case object SpfResultSoftFail extends SpfResult {val value="softfail"}
case object SpfResultNeutral extends SpfResult {val value="neutral"}
case object SpfResultTempError extends SpfResult {val value="temperror"}
case object SpfResultPermError extends SpfResult {val value="permerror"}

object SpfResult {
  def fromString(text: String): SpfResult = text.toLowerCase match {
    case SpfResultNone.value => SpfResultNone
    case SpfResultPass.value => SpfResultPass
    case SpfResultFail.value => SpfResultFail
    case SpfResultSoftFail.value => SpfResultSoftFail
    case SpfResultNeutral.value => SpfResultNeutral
    case SpfResultTempError.value => SpfResultTempError
    case "unknown" => SpfResultTempError
    case SpfResultPermError.value => SpfResultPermError
    case "error" => SpfResultPermError
    case _ => SpfResultNone
  }
}
