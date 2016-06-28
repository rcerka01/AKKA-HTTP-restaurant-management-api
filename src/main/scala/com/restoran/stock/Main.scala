import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.restoran.stock.routers.StockRouter
import com.typesafe.config.ConfigFactory

/**
  * Created by raitis on 25/06/2016.
  */

object Main extends App {

  implicit val actorSystem = ActorSystem()
  implicit val materilizer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher

  val config = ConfigFactory.load()
  val host = config.getString("http.host")
  val port = config.getInt("http.port")

  val bindingFuture = Http().bindAndHandle(StockRouter.route, host, port)
}
