package uk.pkerrigan.dmarcparser.report

sealed trait Disposition {def value: String}
case object DispositionNone extends Disposition {val value="none"}
case object DispositionQuarantine extends Disposition {val value="quarantine"}
case object DispositionReject extends Disposition {val value="reject"}

object Disposition {
  def fromString(text: String): Disposition = text match {
    case t if t.equalsIgnoreCase(DispositionNone.value) => DispositionNone
    case t if t.equalsIgnoreCase(DispositionQuarantine.value) => DispositionQuarantine
    case t if t.equalsIgnoreCase(DispositionReject.value) => DispositionReject
    case _ => DispositionNone
  }
}