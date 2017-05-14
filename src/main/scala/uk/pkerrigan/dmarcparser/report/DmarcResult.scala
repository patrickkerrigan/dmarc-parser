package uk.pkerrigan.dmarcparser.report

sealed trait DmarcResult {def value: String}
case object DmarcResultPass extends DmarcResult {val value="pass"}
case object DmarcResultFail extends DmarcResult {val value="fail"}

object DmarcResult {
  def fromString(text: String): DmarcResult = text.toLowerCase match {
    case DmarcResultPass.value => DmarcResultPass
    case DmarcResultFail.value => DmarcResultFail
    case _ => DmarcResultFail
  }
}