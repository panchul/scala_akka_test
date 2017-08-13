package thingieCompany.ingest

import java.io.{BufferedInputStream, InputStream}

import akka.actor.{ActorRef, Props}
import akka.testkit.{EventFilter, TestActorRef, TestProbe}
import org.scalacheck.Prop.True
import org.scalacheck.{Gen, Prop}
import org.specs2.ScalaCheck
import org.specs2.matcher.ThrownExpectations
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterAll

import scala.util.Random
import scala.concurrent.duration._

class ThingieProcessorActorSpecification extends Specification
	with ThrownExpectations
	with ScalaCheck{

	override def is =
		s2"""
       Test thingie using Gens $testThingieWorks
    """

	val thingieGen: Gen[(Int, Int)] = Gen.oneOf(Seq((1, 2)))

	def testThingieWorks = {
		testThingieWorksParametrized(thingieGen)
	}

	def testThingieWorksParametrized(useGen: Gen[(Int, Int)]) = {
		new AkkaTestkitSpecs2Support {
			within(5 second) {
				//Prop.forAll(useGen) { case (first: Int, second: Int) =>
					Prop.forAll(Gen.oneOf(Seq((1, 2)))) { case (first: Int, second: Int) =>
					//				1 must beEqualTo(2)
					first must beEqualTo(second)
				}
			}
		}
	}
}
