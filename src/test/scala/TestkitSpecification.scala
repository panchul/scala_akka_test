import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import akka.testkit.{ TestKit, ImplicitSender }
import org.specs2.mutable.SpecificationLike

import thingieCompany.ingest.ThingieProcessor


class TestkitSpecification extends TestKit(ActorSystem()) with ImplicitSender with SpecificationLike {

	"ThingieProcessor" should {

		"Bounce off messages incorrectly" in {
			val demo = system.actorOf(Props[ThingieProcessor],"test-actor")
			val msg_send = "hi"
			val msg_recv = "hi dear"
			demo ! msg_send
			println(s"""[TRACE] TestKit: sent \"$msg_send\" to $demo""")
			expectMsg(msg_recv)
			println(s"""[TRACE] TestKit: got \"$msg_recv\" """)
			success
		}

//		"Bounce off messages correctly " in {
//			val demo = system.actorOf(Props[ThingieProcessor],"test-actor2")
//			val msg_send = "hi"
//			val msg_recv = "hi deer"
//			demo ! msg_send
//			println(s"""[TRACE] TestKit: sent \"$msg_send\" to $demo""")
//			expectMsg(msg_recv)
//			println(s"""[TRACE] TestKit: got \"$msg_recv\" """)
//			success
//		}
	}

}
