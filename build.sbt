ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

libraryDependencies += "com.github.sbt" % "junit-interface" % "0.13.3" % Test

libraryDependencies += "org.scalafx" %% "scalafx" % "19.0.0-R30"

lazy val root = (project in file("."))
  .settings(
    name := "PPS-Project-2021-2022"
  )
