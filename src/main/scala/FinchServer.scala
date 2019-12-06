import com.twitter.conversions.DurationOps._
import com.twitter.finagle.Http
import com.twitter.finagle.http.service.HttpResponseClassifier
import com.twitter.util.Await
import io.finch.syntax.get
import io.finch.{Bootstrap, Ok, Text, param, path}

object FinchServer extends App {
  private val echo = get(
    path("echo") ::
    param[String]("param1") ::
    param[String]("param2") ::
    param[String]("param3") ::
    param[String]("param4") ::
    param[String]("param5")
  ) { (p1: String, p2: String, p3: String, p4: String, p5: String) =>
    Ok(s"received parameter: $p1 $p2 $p3 $p4 $p5\n")
  }

  private val route = Bootstrap
    .configure(includeDateHeader = false, includeServerHeader = false)
    .serve[Text.Plain](echo)
    .toService

  private val server = Http.server
    .withResponseClassifier(HttpResponseClassifier.ServerErrorsAsFailures)
    .withRequestTimeout(1.second)
    .serve(s"0.0.0.0:8080", route)

  println(s"Binding to 0.0.0.0:8080")
  Await.ready(server)
}