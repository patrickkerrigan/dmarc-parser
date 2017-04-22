package uk.pkerrigan.dmarcparser.report

case class PolicyPublished(domain: String, dkimAlignment: Alignment, spfAlignment: Alignment, domainPolicy: Disposition, subdomainPolicy: Disposition, percentage: Int, failureReporting: String)