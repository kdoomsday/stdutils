name := "stdutils"

version := "1.0"

scalaVersion := "2.11.6"

scalacOptions ++= Seq("-unchecked", "-deprecation", "-feature")

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0" cross CrossVersion.full)

libraryDependencies ++= Seq(
  "org.scalacheck" %% "scalacheck" % "1.12.2" % "test",
  "com.github.mpilquist" %% "simulacrum" % "0.7.0"
)
