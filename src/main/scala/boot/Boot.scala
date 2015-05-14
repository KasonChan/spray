package boot

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import service.ServiceActor
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by kasonchan on 5/13/15.
 */
object Boot extends App {

  // Create a ActorSystem to host the application
  implicit val system = ActorSystem("spray-can")

  // Create and start our service actor
  val service = system.actorOf(Props[ServiceActor], "service")

  implicit val timeout = Timeout(5.seconds)

  // Start a new HTTP server on port 9000 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 9000)

}
