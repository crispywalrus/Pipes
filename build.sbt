
name := "pipes"
organization := "flyingwalrusllc"
scalaVersion := "2.11.8"

initialCommands in console :=
  """
        |import io.circe._
        |import io.circe.syntax._
        |import io.circe.generic.auto._
        |import io.circe.literal._
        |import io.circe.jackson._
        |import cats._
        |import cats.implicits._
        |import cats.data._
        |import io.catbird.finagle._
        |import io.iteratee._
        |import io.iteratee.twitter._
        |import com.twitter.util.{ Await, Future }
        |import com.twitter.finagle._
  """.stripMargin

lazy val compilerOptions = Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-language:",
  "-unchecked",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-numeric-widen",
  "-Xfuture"
)

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-circe" % "0.11.0-SNAPSHOT",
  "io.circe" %% "circe-generic" % "0.5.0-M1",
  "io.circe" %% "circe-literal" % "0.5.0-M1",
  "com.twitter" %% "finagle-http" % "6.35.0",
  "com.twitter" %% "finagle-redis" % "6.35.0",
  "io.catbird" %% "catbird-finagle" % "0.5.0",
  "io.iteratee" %% "iteratee-twitter" % "0.5.0"
)
