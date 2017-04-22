package uk.pkerrigan.dmarcparser

import uk.pkerrigan.dmarcparser.report.Feedback

trait EmailParserTrait {
  def parseEmail(email: String): Option[Feedback]
}
