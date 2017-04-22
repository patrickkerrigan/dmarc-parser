organization := "uk.pkerrigan"
name := "dmarc-parser"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.6"
libraryDependencies += "org.apache.commons" % "commons-email" % "1.4"
libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.0.1" % "test"