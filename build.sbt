ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.1.1"

libraryDependencies += "org.scalafx" %% "scalafx" % "19.0.0-R30"

lazy val root = (project in file("."))
  .settings(
    name := "PPS-Project-2021-2022"
  )
