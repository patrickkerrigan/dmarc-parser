package uk.pkerrigan.dmarcparser

import uk.pkerrigan.dmarcparser.report.Feedback

trait ParserTrait {
  def parse(rawReport: String): Feedback
}
