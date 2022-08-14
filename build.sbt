val tapirVersion  = "1.0.4"
val http4sVersion = "0.23.14"

lazy val root = project
  .in(file("."))
  .settings(
    name         := "tapir-http4s-todo-mvc",
    version      := "0.2",
    scalaVersion := "2.13.8",
    libraryDependencies ++=
      Seq(
        "com.softwaremill.sttp.tapir"   %% "tapir-core"                     % tapirVersion,
        "com.softwaremill.sttp.tapir"   %% "tapir-http4s-server"            % tapirVersion,
        "com.softwaremill.sttp.tapir"   %% "tapir-json-circe"               % tapirVersion,
        "org.http4s"                    %% "http4s-dsl"                     % http4sVersion,
        "org.http4s"                    %% "http4s-server"                  % http4sVersion,
        "ch.qos.logback"                 % "logback-classic"                % "1.3.0-alpha4",
        "org.webjars"                    % "swagger-ui"                     % "3.20.9",
        "com.softwaremill.sttp.tapir"   %% "tapir-swagger-ui-bundle"        % tapirVersion,
        "com.softwaremill.sttp.tapir"   %% "tapir-openapi-docs"             % tapirVersion,
        "com.softwaremill.sttp.tapir"   %% "tapir-openapi-circe-yaml"       % "1.0.0-M9",
        "com.softwaremill.sttp.client3" %% "async-http-client-backend-cats" % "3.7.4"      % Test,
        "com.softwaremill.sttp.tapir"   %% "tapir-sttp-client"              % tapirVersion % Test,
        "org.scalatest"                 %% "scalatest"                      % "3.2.13"     % Test
      ),
    scalacOptions --= Seq("-Ywarn-value-discard")
    
  )
