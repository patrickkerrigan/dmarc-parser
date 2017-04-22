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
  def fromString(text: String): SpfResult = text match {
    case t if t.equalsIgnoreCase(SpfResultNone.value) => SpfResultNone
    case t if t.equalsIgnoreCase(SpfResultPass.value) => SpfResultPass
    case t if t.equalsIgnoreCase(SpfResultFail.value) => SpfResultFail
    case t if t.equalsIgnoreCase(SpfResultSoftFail.value) => SpfResultSoftFail
    case t if t.equalsIgnoreCase(SpfResultNeutral.value) => SpfResultNeutral
    case t if t.equalsIgnoreCase(SpfResultTempError.value) => SpfResultTempError
    case t if t.equalsIgnoreCase("unknown") => SpfResultTempError
    case t if t.equalsIgnoreCase(SpfResultPermError.value) => SpfResultPermError
    case t if t.equalsIgnoreCase("error") => SpfResultPermError
    case _ => SpfResultNone
  }
}
