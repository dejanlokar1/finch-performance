import com.twitter.conversions.DurationOps._
import com.twitter.finagle.http.Method.Get
import com.twitter.finagle.http.path._
import com.twitter.finagle.http.service.{HttpResponseClassifier, RoutingService}
import com.twitter.finagle.http.{Status, Request => Req, Response => Res}
import com.twitter.finagle.{Http, Service}
import com.twitter.util.{Await, Future}

object FinagleServer extends App {
  private val route = RoutingService.byMethodAndPathObject {
    case Get -> Root / "echo" => Service.mk(echo)
  }

  private val server = Http.server
    .withResponseClassifier(HttpResponseClassifier.ServerErrorsAsFailures)
    .withRequestTimeout(1.second)
    .serve(s"0.0.0.0:8080", route)

  println(s"Binding to 0.0.0.0:8080")
  Await.ready(server)

  private def echo(req: Req): Future[Res] = {
    val p1 = req.getParam("param1")
    val p2 = req.getParam("param2")
    val p3 = req.getParam("param3")
    val p4 = req.getParam("param4")
    val p5 = req.getParam("param5")

    val res = response(Status.Ok, s"received parameter: $p1 $p2 $p3 $p4 $p5\n")
    Future.value(res)
  }

  private def response(status: Status, content: String): Res = {
    val res = Res(status)
    res.setContentString(content)
    res.setContentType("text/plain")

    res
  }
}
