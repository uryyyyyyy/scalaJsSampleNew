name := """scalajsSample"""

lazy val commonSettings = Seq(
  organization := "com.github.uryyyyyyy",
  scalaVersion := "2.11.8",
  version := "0.1.0"
)

lazy val server = (project in file("server"))
  .dependsOn(coreDomain)
  .settings(commonSettings)
  .settings(
    libraryDependencies ++= Seq(
      "org.scalatestplus.play" %% "scalatestplus-play" % "1.5.1" % Test
    )
  ).enablePlugins(PlayScala)

lazy val coreDomain = (project in file("coreDomain"))
  .settings(commonSettings)

val helloWorldOutputDir        = file("scalaJSDist")

lazy val front = (project in file("front"))
  .settings(commonSettings)
  .dependsOn(coreDomain)
  .settings(
    scalaJSModuleKind := ModuleKind.CommonJSModule,
    crossTarget in(Compile, fullOptJS) := helloWorldOutputDir,
    crossTarget in(Compile, fastOptJS) := helloWorldOutputDir
  ).enablePlugins(ScalaJSPlugin)
