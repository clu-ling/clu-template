import sbtassembly.MergeStrategy
import com.typesafe.sbt.packager.docker.DockerChmodType


// use commit hash as the version
// enablePlugins(GitVersioning)
// git.uncommittedSignifier := Some("DIRTY") // with uncommitted changes?
// git.baseVersion := "0.1.0-SNAPSHOT"

lazy val commonScalacOptions = Seq(
  "-feature",
  "-unchecked",
  "-deprecation",
  "-Xlint",
  "-Yno-adapted-args",
  "-Ywarn-dead-code",
  "-Ywarn-unused",
  "-encoding", "utf8"
)


routesGenerator := InjectedRoutesGenerator

lazy val commonSettings = Seq(
  organization := "{{ cookiecutter.class_path }}",
  scalaVersion := "2.12.10",
  // we want to use -Ywarn-unused-import most of the time
  scalacOptions ++= commonScalacOptions,
  scalacOptions += "-Ywarn-unused-import",
  // -Ywarn-unused-import is annoying in the console
  scalacOptions in (Compile, console) := commonScalacOptions,
  // show test duration
  testOptions in Test += Tests.Argument("-oD"),
  // avoid dep. conflict in assembly task for webapp
  excludeDependencies += "commons-logging" % "commons-logging",
  parallelExecution in Test := false
)

// example specifying credentials using ENV variables:
// AWS_ACCESS_KEY_ID="XXXXXX" AWS_SECRET_KEY="XXXXXX"

lazy val sharedDeps = {
  libraryDependencies ++= {
    val odinsonVersion      = "0.3.0-SNAPSHOT"
    Seq(
      "ai.lum"        %% "common"               % "0.1.2",
      //"ai.lum"        %% "odinson-core"         % odinsonVersion
    )
  }
}

lazy val assemblySettings = Seq(
  // Trick to use a newer version of json4s with spark (see https://stackoverflow.com/a/49661115/1318989)
  assemblyShadeRules in assembly := Seq(
    ShadeRule.rename("org.json4s.**" -> "shaded_json4s.@1").inAll
  ),
  assemblyMergeStrategy in assembly := {
    case refOverrides if refOverrides.endsWith("reference-overrides.conf") => MergeStrategy.first
    case logback if logback.endsWith("logback.xml") => MergeStrategy.first
    case netty if netty.endsWith("io.netty.versions.properties") => MergeStrategy.first
    case "messages" => MergeStrategy.concat
    case PathList("META-INF", "terracotta", "public-api-types") => MergeStrategy.concat
    case PathList("play", "api", "libs", "ws", xs @ _*) => MergeStrategy.first
    case PathList("org", "apache", "lucene", "analysis", xs @ _ *) => MergeStrategy.first
    case x =>
      val oldStrategy = (assemblyMergeStrategy in assembly).value
      oldStrategy(x)
  }
)

lazy val buildInfoSettings = Seq(
  buildInfoPackage := "{{ cookiecutter.class_path }}.mr",
  buildInfoOptions += BuildInfoOption.BuildTime,
  buildInfoKeys := Seq[BuildInfoKey](
    name, version, scalaVersion, sbtVersion, libraryDependencies, scalacOptions,
    "gitCurrentBranch" -> { git.gitCurrentBranch.value },
    "gitHeadCommit" -> { git.gitHeadCommit.value.getOrElse("") },
    "gitHeadCommitDate" -> { git.gitHeadCommitDate.value.getOrElse("") },
    "gitUncommittedChanges" -> { git.gitUncommittedChanges.value }
  )
)

lazy val reader = (project in file("reader"))
  .settings(commonSettings)
  .settings(sharedDeps)
  .settings(assemblySettings)
  .settings(
    test in assembly := {}
  )

val gitDockerTag = settingKey[String]("Git commit-based tag for docker")
ThisBuild / gitDockerTag := {
  val shortHash: String = git.gitHeadCommit.value.get.take(7)  
  val uncommittedChanges: Boolean = (git.gitUncommittedChanges).value
  s"""${shortHash}${if (uncommittedChanges) "-DIRTY" else ""}"""
}

lazy val packagerSettings = {
  Seq(
    // see https://www.scala-sbt.org/sbt-native-packager/formats/docker.html
    dockerUsername := Some("{{ cookiecutter.docker_username }}"),
    dockerAliases ++= Seq(
      dockerAlias.value.withTag(Option("latest")),
      dockerAlias.value.withTag(Option(gitDockerTag.value)),
      // see https://github.com/sbt/sbt-native-packager/blob/master/src/main/scala/com/typesafe/sbt/packager/docker/DockerAlias.scala
    ),
    packageName in Docker := "{{ cookiecutter.repo_name }}-rest-api", // ex: "{{ cookiecutter.project_name }}-reader-rest-api"
    // "openjdk:11-jre-alpine"
    dockerBaseImage := "openjdk:11",
    maintainer in Docker := "{{ cookiecutter.docker_maintainer }}",
    dockerExposedPorts in Docker := Seq(9000),
    javaOptions in Universal ++= Seq(
      "-J-Xmx2G",
      // avoid writing a PID file
      "-Dplay.server.pidfile.path=/dev/null"
    )
  )
}

lazy val webapp = (project in file("rest"))
  .enablePlugins(PlayScala)
  .enablePlugins(BuildInfoPlugin)
  .aggregate(reader)
  .dependsOn(reader)
  .settings(commonSettings)
  .settings(packagerSettings)
  .settings(sharedDeps)
  .settings(buildInfoSettings)
  .settings(assemblySettings)
  .settings(
    mainClass in assembly := Some("play.core.server.ProdServerStart"), // FIXME template, is this core subproject?
    fullClasspath in assembly += Attributed.blank(PlayKeys.playPackageAssets.value)
  )

lazy val web = taskKey[Unit]("Launches the webapp in dev mode.")
web := (run in Compile in webapp).toTask("").value

lazy val editGrammars = taskKey[Unit]("Copies and modifies grammars for reading via resources")


// FIXME: These settings are only picked up if they are left top-level
flatten in EditSource := false
// change pathPrefix in grammars to read from resources
substitutions in EditSource += sub("""pathPrefix: .*$""".r, "pathPrefix: \"{{ cookiecutter.class_path.replace('.', '/') }}/reader/grammars/{{ cookiecutter.project_name }}\"")

variables in EditSource += "actionFlow" -> "DOLLAR{actionFlow}"
variables in EditSource += "agents" -> "DOLLAR{agents}"
variables in EditSource += "causeType" -> "DOLLAR{causeType}"
variables in EditSource += "decrease_triggers" -> "DOLLAR{decrease_triggers}"
variables in EditSource += "effectType" -> "DOLLAR{effectType}"
variables in EditSource += "eventAction" -> "DOLLAR{eventAction}"
variables in EditSource += "event_action" -> "DOLLAR{event_action}"
variables in EditSource += "eventLabel" -> "DOLLAR{eventLabel}"
variables in EditSource += "forms" -> "DOLLAR{forms}"
variables in EditSource += "formsWords" -> "DOLLAR{formsWords}"
variables in EditSource += "increase_triggers" -> "DOLLAR{increase_triggers}"
variables in EditSource += "pathPrefix" -> "DOLLAR{pathPrefix}"
variables in EditSource += "priority" -> "DOLLAR{priority}"
variables in EditSource += "rulePriority" -> "DOLLAR{rulePriority}"
variables in EditSource += "trigger" -> "DOLLAR{trigger}"

substitutions in EditSource += sub("""DOLLAR[{]""".r, """\${""", SubAll)

(sources in EditSource) ++= (baseDirectory.value / "reader"/ "grammars" ** "*.yml").get
{% set class_list = cookiecutter.class_path.replace('.', '/').split('/') %}
{% if class_list|length > 1%}
targetDirectory in EditSource := (baseDirectory.value / "reader" / "src" / "main" / "resources" / {% for i in range(class_list|length-1) %} "{{ class_list[i] }}" / {% endfor %} "{{ class_list|last }}")
{% else %}
targetDirectory in EditSource := (baseDirectory.value / "reader" / "src" / "main" / "resources" / "{{ class_list.0 }}" )
{% endif %}


editGrammars := {
  println("Copying and modifying grammars to read from resources...")
  val _ = (edit in EditSource).value
}

lazy val cp = taskKey[Unit]("Copies api directories from target to docs")

cp := {
  println{"Copying api documentation..."}
  def copyDocs(): Unit = {
    val projects = Seq("reader", "rest")
    for (s <- projects) {
      println(s"$s docs...")
      val source = baseDirectory.value / s / "target" / "scala-2.12" / "api"
      val target = baseDirectory.value / "docs" / "api" / s
      IO.copyDirectory(source, target, overwrite = true)
    }
  }
  copyDocs()
}


addCommandAlias("copyGrammars", ";clean;editsource:clean;editGrammars")

addCommandAlias("cleanTest", ";copyGrammars;test")

addCommandAlias("dockerize", ";copyGrammars;docker:publishLocal")

addCommandAlias("documentize", ";clean;doc;cp")