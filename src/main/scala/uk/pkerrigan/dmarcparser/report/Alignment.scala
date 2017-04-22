package uk.pkerrigan.dmarcparser.report

sealed trait Alignment {
  def value: String
}
case object AlignmentRelaxed extends Alignment {val value = "r"}
case object AlignmentStrict extends Alignment {val value = "s"}

object Alignment {
  def fromString(text: String): Alignment = text match {
    case t if t.equalsIgnoreCase(AlignmentRelaxed.value) => AlignmentRelaxed
    case t if t.equalsIgnoreCase(AlignmentStrict.value) => AlignmentStrict
    case _ => AlignmentRelaxed
  }
}