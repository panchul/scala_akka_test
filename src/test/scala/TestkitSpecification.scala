import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import akka.testkit.{ TestKit, ImplicitSender }
import org.specs2.mutable.SpecificationLike

import thingieCompany.ingest.ThingieProcessorActor


class TestkitSpecification extends TestKit(ActorSystem()) with ImplicitSender with SpecificationLike {

	"ThingieProcessor" should {

		"Test with hardcoded values using TestkitSpecification" in {
			val demo = system.actorOf(Props[ThingieProcessorActor],"test-actor")
			val msg_send = "hi"
			val msg_recv = "hi dear" // the expected value, we know this is what we need to get back from 'hi'
			demo ! msg_send
			println(s"""[TRACE] TestKit: sent \"$msg_send\" to $demo""")
			expectMsg(msg_recv)
			println(s"""[TRACE] TestKit: got \"$msg_recv\" """)
			success   // we need this 'success' so the output of println() would not be the last value.
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
