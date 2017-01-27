package thingieCompany.ingest

import akka.actor.{Actor, ActorLogging, Props}

object ThingieProcessor {
	def props: Props = Props(classOf[ThingieProcessor])

	def name: String = this.getClass.getSimpleName

}

class ThingieProcessor extends Actor with ActorLogging {

	override def receive = {
		//case msg => {
			case "hi" => {
				println(s"""[TRACE] DemoActor: gottttt \"hi\" """)
				println(s"""[TRACE] DemoActor: sender is \"$sender\" """)
				sender ! "hi" ++" dear"
			}
			case msg =>
				println(s"""[TRACE] DemoActor: got \"$msg\" """)
				println(s"""[TRACE] DemoActor: sender is \"$sender\" """)

	}
}