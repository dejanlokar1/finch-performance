name := "finch-performance"
version := "1.0"
scalaVersion := "2.12.8"

libraryDependencies ++= Seq(
  "com.twitter" %% "finagle-http" % "19.11.0",
  "com.github.finagle" %% "finch-core" % "0.31.0"
)

