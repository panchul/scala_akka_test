name := "thingieProcessor"
version := "1.0"
scalaVersion := "2.11.8"

resolvers ++= Seq(
	Resolver.sonatypeRepo("snapshots"),
	"central" at "http://repo1.maven.org/maven2",
	"Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
	"Oss repository" at "http://oss.sonatype.org/content/repositories/snapshots/",
	"Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases",
	"scoverage-bintray" at "https://dl.bintray.com/sksamuel/sbt-plugins/",
	"sonatype-snapshots" at "https://oss.sonatype.org/content/repositories/snapshots"
)

libraryDependencies ++= Seq(
	"com.typesafe" % "config" % "1.3.0",
	"com.typesafe.akka" %% "akka-actor" % "2.4.9",
	"com.typesafe.akka" %% "akka-slf4j" % "2.4.9",
	"ch.qos.logback" % "logback-classic" % "1.1.7",
	"org.slf4j" % "slf4j-api" % "1.7.21",
	"org.slf4j" % "log4j-over-slf4j" % "1.7.21",
  "org.specs2" %% "specs2-core" % "3.8.4" % "test" exclude("org.scalaz", "scalaz-core_2.11"),
  "org.specs2" %% "specs2-scalacheck" % "3.8.4",
  "org.specs2" %% "specs2-mock" % "3.8.4" % "test",
  "com.google.jimfs" % "jimfs" % "1.1",
  "org.specs2" %% "specs2-junit" % "3.8.4" % "test",
  "org.scalaz" %% "scalaz-core" % "7.2.3",
  "org.scalaz" %% "scalaz-effect" % "7.2.3",
  "com.typesafe.akka" %% "akka-testkit" % "2.4.9"
)

