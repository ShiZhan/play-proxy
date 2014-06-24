package controllers

import play.api.mvc.{ Controller, Action, Result }
import play.api.mvc.Results.Status
import play.api.libs.concurrent.Execution.Implicits._
import play.api.libs.ws.WS
import play.api.Play.current

object Proxy extends Controller {
  def index(url: String) = Action.async {
    WS.url(url).get().map(response =>
      Status(response.status)(response.body).as(response.header("Content-Type").getOrElse("text/plain")))
  }
}
