package uk.pkerrigan.dmarcparser.report

sealed trait Disposition {def value: String}
case object DispositionNone extends Disposition {val value="none"}
case object DispositionQuarantine extends Disposition {val value="quarantine"}
case object DispositionReject extends Disposition {val value="reject"}

object Disposition {
  def fromString(text: String): Disposition = text.toLowerCase match {
    case DispositionNone.value => DispositionNone
    case DispositionQuarantine.value => DispositionQuarantine
    case DispositionReject.value => DispositionReject
    case _ => DispositionNone
  }
}