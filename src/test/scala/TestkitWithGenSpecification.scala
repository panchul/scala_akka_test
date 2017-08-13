import akka.actor.ActorSystem
import akka.actor.Actor
import akka.actor.Props
import akka.testkit.{ImplicitSender, TestKit}
import org.specs2.mutable.SpecificationLike
import thingieCompany.ingest.ThingieProcessorActor
import org.scalacheck.{Gen, Prop, Shrink}
import org.specs2.execute
import org.specs2.execute.AsResult
import org.specs2.scalacheck.AsResultProp
import org.scalacheck.Prop

class TestkitWithGenSpecification extends TestKit(ActorSystem())
	with ImplicitSender
	with SpecificationLike {

	"ThingieProcessor" should {

//		"Bounce off messages correctly " in {
//			val thingieGen: Gen[Int] = Gen.oneOf(Seq(1))
//			val demo = system.actorOf(Props[ThingieProcessor],"test-actor2")
//
//			// check(//Prop.forAll((n: Int) => n + 0 == n), Params(minSuccessfulTests = 5))
//			Prop.forAll(thingieGen) { a:Int =>
//
//					demo ! "aaa"
//					println(s"""[TRACE] TestKit: sent \"$a\" to $demo""")
//					expectMsg("aaa dear")
//					println(s"""[TRACE] TestKit: got \"$a\" """)
//					success
//					//true must beEqualTo(true)
//				Prop.passed
//			}
//		}

//		"Bounce off messages correctly " in {
//			val thingieGen: Gen[(String, String)] = Gen.oneOf(Seq(("hi", "hi dear")))
//			val demo = system.actorOf(Props[ThingieProcessor],"test-actor2")
//
//			Prop.forAll(thingieGen)  { case (sent:String, received:String) =>
//
//				demo ! sent
//				println(s"""[TRACE] TestKit: sent \"$sent\" to $demo""")
//				expectMsg(received)
//				println(s"""[TRACE] TestKit: got \"$received\" """)
//				success
//				//true must beEqualTo(true)
//			}
//		}

		"Test with hardcoded values in TestkitWithGenSpecification" in {
			val demo = system.actorOf(Props[ThingieProcessorActor],"test-actor")
			val msg_send = "hi"
			val msg_recv = "hi dear"
			demo ! msg_send
			println(s"""[TRACE] TestKit: sent \"$msg_send\" to $demo""")
			expectMsg(msg_recv)
			println(s"""[TRACE] TestKit: got \"$msg_recv\" """)
			success
		}

	}

}
