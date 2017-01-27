package thingieCompany.ingest

import akka.actor.{ActorSystem, Terminated}
import akka.testkit.{ImplicitSender, TestKit}
import com.typesafe.config.ConfigFactory
import org.specs2.mutable._

import scala.concurrent.Future

abstract class AkkaTestkitSpecs2Support(_system: ActorSystem) extends TestKit(_system)
	with After
	with ImplicitSender {

	def this() = this(ActorSystem(
		"ThingieProcessorSpecs",
		ConfigFactory.parseString("""
        akka.loggers = ["akka.testkit.TestEventListener"]
        akka.logger-startup-timeout = 30s
        akka.loglevel                         = "WARNING"
        akka.stdout-loglevel                  = "WARNING"
        akka.logging-filter = "akka.event.slf4j.Slf4jLoggingFilter"
        akka.log-dead-letters                 = 0
        akka.log-dead-letters-during-shutdown = off
        akka.log-config-on-start              = off
        akka.event-handlers = ["akka.event.EventHandler$DefaultListener"]
        akka.event-handler-level = "WARNING"
        akka.actor {
          debug {
            receive                      = off
            autoreceive                  = off
            lifecycle                    = off
            fsm                          = off
            event-stream                 = off
          }
        }
        akka.remote {
          log-sent-messages = off
          log-received-messages = off
        }
    """)
	))

	def after: Future[Terminated] = system.terminate()
}
