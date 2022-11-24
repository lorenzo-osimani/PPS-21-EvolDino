ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

libraryDependencies ++= Seq(
  "com.github.sbt" % "junit-interface" % "0.13.3" % Test,
  "org.scalatest" %% "scalatest" % "3.2.14" % Test,
  "org.typelevel" %% "cats-effect" % "3.3.14",
  "it.unibo.alice.tuprolog" % "2p-core" % "4.1.1",
  "it.unibo.alice.tuprolog" % "2p-ui" % "4.1.1",
  "org.scalafx" %% "scalafx" % "19.0.0-R30"
)

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-encoding",
  "utf8",
  "-feature",
  "-language:implicitConversions"
)

fork := true

lazy val root = (project in file("."))
  .settings(name := "PPS-Project-2021-2022")
