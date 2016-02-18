name := "100knocks_scala"

scalaVersion := "2.10.5"

scalacOptions ++= Seq("-encoding", "UTF-8")

libraryDependencies ++= Seq(
  "org.scala-lang" % "scala-reflect" % "2.10.5",
  "io.argonaut" %% "argonaut" % "6.1-M5" exclude("org.scala-lang", "scala-compiler") changing()
)
