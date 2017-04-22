package uk.pkerrigan.dmarcparser.report

sealed trait DmarcResult {def value: String}
case object DmarcResultPass extends DmarcResult {val value="pass"}
case object DmarcResultFail extends DmarcResult {val value="fail"}

object DmarcResult {
  def fromString(text: String): DmarcResult = text match {
    case t if t.equalsIgnoreCase(DmarcResultPass.value) => DmarcResultPass
    case t if t.equalsIgnoreCase(DmarcResultFail.value) => DmarcResultFail
    case _ => DmarcResultFail
  }
}