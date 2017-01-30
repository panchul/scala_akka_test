import akka.actor.Actor.Receive
import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestActorRef, TestActors, TestKitBase}
import com.typesafe.config.ConfigFactory
import org.scalacheck._
import org.specs2.ScalaCheck
import org.specs2.matcher.ThrownExpectations
import org.specs2.Specification
import thingieCompany.ingest.ThingieProcessor

import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

//class ThingieProcessor extends Actor {
//	override def receive: Receive = {
//		case "hi" => sender() ! "hi dear"
//			println("got mess hi")
//		case _ => unhandled("Unhandled")
//	}
//}

class ThingieProcessor2Specification extends Specification
	with ThrownExpectations
	with ScalaCheck
{
	def is = s2"""
  Test thingie2 test2 $testThingieWorks2
"""

	implicit def arbitraryAkka: Arbitrary[Akka] =
		Arbitrary(Gen.const(Akka()))

	val thingieGen: Gen[(String, String)] = Gen.oneOf(Seq(("hi", "hi dear")))
	var didTheThingie2 = 0
	var myActor2: ActorRef = null

	val testThingieWorks2 = prop { (t:Tuple2[String,String], akka: Akka) =>
		import akka._ ; akka {
		within(20.seconds)
		{
			val demo= system.actorOf(Props[ThingieProcessor])

			println(s"""[TRACE] Gen TestKit: demo is $demo""")
			val msg_send = t._1 //"hi"
		val msg_recv = t._2 //"hi dear"

			//demo! msg_send
			//demo ! (msg_send)(testActor)
			send(demo, msg_send)

			println(s"""[TRACE] Gen TestKit: sent \"$msg_send\" to $demo""")

			println(s"""[TRACE] Gen TestKit: expecting \"$msg_recv\"... """)

			expectMsg(10.second, msg_recv)

			println(s"""[TRACE] Gen TestKit: after expectMsg """)

			// success

			ok.not.not
		}
	}
	}.setGen1(thingieGen)

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