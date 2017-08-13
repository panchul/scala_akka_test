import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestActors, TestKitBase}
import com.typesafe.config.ConfigFactory
import org.scalacheck.{Gen, _}
import org.specs2.ScalaCheck
import org.specs2.matcher.ThrownExpectations
import org.specs2.Specification
import thingieCompany.ingest.ThingieProcessorActor

import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class ThingieProcessorActor2Specification extends Specification
	with ThrownExpectations
	with ScalaCheck
{
	def is = s2"""
  Test with Generators using ThingieProcessor2Specification $testThingieWorks2
"""

	implicit def arbitraryAkka: Arbitrary[Akka] =
		Arbitrary(Gen.const(Akka()))

	val thingieGen: Gen[(String, String)] = Gen.oneOf(Seq(("hi", "hi dear"),("bye", "bye dear")))
	var didTheThingie2 = 0
	var myActor2: ActorRef = null

	val testThingieWorks2 = myfunc(thingieGen)

	def myfunc(myGen:Gen[(String, String)]) = prop {
		(t:Tuple2[String,String], akka: Akka) =>
			import akka._ ;
			akka {
				within(20.seconds) {

					val demo= system.actorOf(Props[ThingieProcessorActor])

					println(s"""[TRACE from myfunc()] Gen TestKit: demo is $demo""")
					val msg_send = t._1 //"hi"
					val msg_recv = t._2 //"hi dear"

					//demo! msg_send
					//demo ! (msg_send)(testActor)
					send(demo, msg_send)

					println(s"""[TRACE from myfunc()] Gen TestKit: sent \"$msg_send\" to $demo""")

					println(s"""[TRACE from myfunc()] Gen TestKit: expecting \"$msg_recv\"... """)

					expectMsg(10.second, msg_recv)

					println(s"""[TRACE from myfunc()] Gen TestKit: after expectMsg """)

					// success

					ok
				}
			}
	}.setGen1(myGen)

}

case class Akka() extends org.specs2.specification.After with TestKitBase with ImplicitSender {
	lazy val system = ActorSystem(
		"ThingieProcessor2Specs",
		ConfigFactory.parseString("""
      akka.loggers = ["akka.testkit.TestEventListener"]
    """)
	)
	//def before: Unit = {
	//	//val demo0 = system.actorOf(Props[ThingieProcessor],"test-actorg")
	//	()
	//}
	def after: Unit =
	{
		//    system.terminate()
		//()
	}

	def send(actor: ActorRef, msg: Any): Unit = actor.!(msg)(testActor)
}