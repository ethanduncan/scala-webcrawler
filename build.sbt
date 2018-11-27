import Dependencies._

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization := "com.example",
      scalaVersion := "2.12.7",
      version      := "0.1.0-SNAPSHOT"
    )),
    name := "scala-web-crawler",
    libraryDependencies ++= Seq(
      "org.jsoup" % "jsoup" % "1.11.3",
      scalaTest % Test
    )
  )
