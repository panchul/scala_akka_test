package thingieCompany

import akka.actor.{ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory
import thingieCompany.ingest.ThingieProcessorActor

import scala.concurrent.Await

object Main extends App {

  println(s"Starting the application...")

  val system = ActorSystem("thingie-processors", ConfigFactory.load().getConfig("thingie-processor"))

  val createdThingie = system.actorOf(ThingieProcessorActor.props, ThingieProcessorActor.name)
  println(s"""[TRACE] created createdThingie \"$createdThingie\"""")
 
  val myMsg = "hi"
  val theThingieActor: ActorSelection = system.actorSelection(createdThingie.path)
  println(s"""[TRACE] sending \"$myMsg\" to $theThingieActor""")
  system.actorSelection(createdThingie.path).!(myMsg)         // we are ont an actor, so dead letters here. :-)
  println(s"""[TRACE] sent \"$myMsg\" to $theThingieActor""")


  //Add hook for graceful shutdown
  sys.addShutdownHook {
    system.log.info("Shutting down")
    system.terminate()
    Await.result(system.whenTerminated, Settings.serviceTimeout.duration)
    //    reservationConsumer.shutdown()

    //TODO: fix logger
    println(s"Actor system '${system.name}' successfully shut down")
  }
}