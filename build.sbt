ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.2.0"

ThisBuild / javacOptions := Seq("-source", "17.0.2")

libraryDependencies ++= {
  val osNames = Seq("linux", "mac", "win")
  osNames.flatMap(os => Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
    .map(m => "org.openjfx" % s"javafx-$m" % "19" classifier os))
}

libraryDependencies ++= Seq(
  "com.github.sbt" % "junit-interface" % "0.13.3" % Test,
  "org.scalatest" %% "scalatest" % "3.2.14" % Test,
  "org.typelevel" %% "cats-core" % "2.9.0",
  "org.typelevel" %% "cats-effect" % "3.4.1",
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
