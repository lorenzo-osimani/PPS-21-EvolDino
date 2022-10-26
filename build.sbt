ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

libraryDependencies += "com.github.sbt" % "junit-interface" % "0.13.3" % Test

libraryDependencies += "org.scalafx" %% "scalafx" % "19.0.0-R30"

libraryDependencies += "org.scalatest" %% "scalatest" % "3.2.14" % Test

scalacOptions ++= Seq("-unchecked", "-deprecation", "-encoding", "utf8", "-feature")

fork := true

lazy val root = (project in file("."))
  .settings(
    name := "PPS-Project-2021-2022"
  )
