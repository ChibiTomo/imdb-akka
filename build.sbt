name := "imdb-akka"

version := "0.1.0"

scalaVersion := "2.13.7"

lazy val JavaOptions = Seq()

scalacOptions ++= Seq("-feature")
(Test / javaOptions) ++= JavaOptions
(reStart / mainClass) := Some("com.github.chibitomo.Main")
(reStart / javaOptions) ++= JavaOptions

val akkaVersion      = "2.6.17"
val scalatestVersion = "3.2.10"
libraryDependencies ++= Seq(
  "com.typesafe.akka"  %% "akka-stream"             % akkaVersion,
  "com.typesafe.akka"  %% "akka-actor"              % akkaVersion,
  "com.lightbend.akka" %% "akka-stream-alpakka-csv" % "3.0.3",
  "org.scalatest"      %% "scalatest"               % scalatestVersion % "test"
)
