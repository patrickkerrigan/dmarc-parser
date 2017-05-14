[![Build Status](https://img.shields.io/travis/patrickkerrigan/dmarc-parser.svg?style=flat-square)](https://travis-ci.org/patrickkerrigan/dmarc-parser) ![Scala 2.12](https://img.shields.io/badge/scala-2.12-red.svg?style=flat-square)

# dmarc-parser

A Scala library for parsing DMARC aggregate reports as defined in [RFC 7489](https://tools.ietf.org/html/rfc7489#appendix-C)

## How to use

Simply pass a raw aggregate report email (headers and attachments included) into an instance of ```EmailParser``` like so:

```scala
val parser = new EmailParser
val email = "Your raw email here..."

val report = parser.parseEmail(email)
```

