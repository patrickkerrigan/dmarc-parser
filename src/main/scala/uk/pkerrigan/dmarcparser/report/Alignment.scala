package uk.pkerrigan.dmarcparser.report

sealed trait Alignment {
  def value: String
}
case object AlignmentRelaxed extends Alignment {val value = "r"}
case object AlignmentStrict extends Alignment {val value = "s"}

object Alignment {
  def fromString(text: String): Alignment = text.toLowerCase match {
    case AlignmentRelaxed.value => AlignmentRelaxed
    case AlignmentStrict.value => AlignmentStrict
    case _ => AlignmentRelaxed
  }
}