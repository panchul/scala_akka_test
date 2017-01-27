import akka.actor.{ActorRef, ActorSystem, Props}
import akka.testkit.{ImplicitSender, TestKitBase}
import com.typesafe.config.ConfigFactory
import org.scalacheck._
import org.specs2.ScalaCheck
import org.specs2.matcher.ThrownExpectations
import org.specs2.Specification
import thingieCompany.ingest.ThingieProcessor

import scala.concurrent.duration._
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class ThingieProcessor2Specification extends Specification
	with ThrownExpectations
	with ScalaCheck
{
//	def is = s2"""
// Test thingie2 test1 $testThingieWorks
//  Test thingie2 test2 $testThingieWorks2
//"""

	def is = s2"""
  Test thingie2 test2 $testThingieWorks2
"""

//	def is = s2"""
//  Test thingie2 test1 $testThingieWorks
//"""

	def genInt = Gen.oneOf(Seq(1,2,3,4,5,6,7,8,9,0))
  var didTheThingie = 0
	implicit def arbitraryAkka: Arbitrary[Akka] =
		Arbitrary(Gen.const(Akka()))

	var myActor: ActorRef = null

	val testThingieWorks = prop { (i: Int, akka: Akka) =>
		import akka._; akka {
			within(5.seconds)
		  {
				val demo =
				// keeps failing
				// 	system.actorSelection("test-actorg").resolveOne(5.second).onComplete{
				//		case Success(actorRef) => actorRef
				//		case Failure(ex) => system.actorOf(Props[ThingieProcessor], "test-actorg")
				//	}
				if(didTheThingie == 0) {
					didTheThingie = 1
					myActor = system.actorOf(Props[ThingieProcessor], "test-actorg1")
					myActor
				} else {
					myActor
				}

				demo ! "message"

				println(s"""[TRACE] Gen TestKit: i is $i """)
				println(s"""[TRACE] Gen TestKit: demo is $demo """)
				ok
		  }
		}
	}.setGen1(genInt)

	val thingieGen: Gen[(String, String)] = Gen.oneOf(Seq(("hi", "hi dear")))
	var didTheThingie2 = 0
	var myActor2: ActorRef = null

	val testThingieWorks2 = prop { (t:Tuple2[String,String], akka: Akka) =>
		import akka._ ; akka {
			within(5.seconds)
			{
				val demo2=
					if(didTheThingie2 == 0) {
						didTheThingie2 = 1
						myActor2 = system.actorOf(Props[ThingieProcessor], "test-actorg")
						myActor2
					} else {
						myActor2
					}

				val demo= system.actorOf(Props[ThingieProcessor], "test-actorg2")

				println(s"""[TRACE] Gen TestKit: demo is $demo""")
				val msg_send = t._1 //"hi"
				val msg_recv = t._2 //"hi dear"

				demo! msg_send
				//demo ! (msg_send)(testActor)
				//send(demo, msg_send)

				println(s"""[TRACE] Gen TestKit: sent \"$msg_send\" to $demo""")

				println(s"""[TRACE] Gen TestKit: expecting \"$msg_recv\"... """)

				expectMsg(msg_recv)

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
			system.terminate()
			//()
		}
}
