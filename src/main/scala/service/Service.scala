package service

import akka.actor.Actor
import spray.http.MediaTypes._
import spray.routing._

/**
 * Created by kasonchan on 5/13/15.
 */
// Don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class ServiceActor extends Actor with Service {

  // The HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // This actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(route)
}

// This trait defines the service behavior independently from the service actor
trait Service extends HttpService {

  val route =
    path("") {
      get {
        respondWithMediaType(`text/html`) {
          // XML is marshalled to `text/xml` by default, so we simply override
          // here
          complete {
            <html>
              <body>
                <h1>Say hello to
                  <i>spray-routing</i>
                  on
                  <i>spray-can</i>
                  !</h1>
                This is the initial commit.
              </body>
            </html>
          }
        }
      }
    }

}