package thingieCompany.ingest

import akka.actor.{Actor, ActorLogging, Props}

object ThingieProcessorActor {
	
	def props: Props = Props(classOf[ThingieProcessorActor])
	
	def name: String = {
		val myname = this.getClass.getSimpleName
		println (s"""ThingieProcessorActor name is \"$myname\" """)
		this.getClass.getSimpleName
	}

}

class ThingieProcessorActor extends Actor with ActorLogging {

	override def receive = {
			case msg:String => {
				println(s"""[TRACE] ThingieProcessorActor: got \"$msg\" """)
				println(s"""[TRACE] ThingieProcessorActor: sender is \"$sender\" """)
				sender ! msg ++ " dear"
			}
			case otherwise => {
				println(s"""[TRACE] ThingieProcessorActor: sender is \"$sender\" """)
				println(s"""[TRACE] E is \"$otherwise\" """)
        log.info("got otherwise: " + otherwise.toString)
			}
	}
}