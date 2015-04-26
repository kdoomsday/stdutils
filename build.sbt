name := "stdutils"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

libraryDependencies += "org.scalacheck" %% "scalacheck" % "1.12.2" % "test"
