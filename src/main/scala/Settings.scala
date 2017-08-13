package thingieCompany

import akka.util.Timeout
import com.typesafe.config.{ ConfigFactory, Config }

class Settings(val config: Config) {
  //thingie-processor settings
  val processorConfig = config.getConfig("thingie-processor")
  import processorConfig._

  val serviceTimeout = Timeout(getFiniteDuration("service-timeout"))
  val sourceDir = getString("source-dir")
  val numberOfProcessors = getInt("number-of-processor")

  def getFiniteDuration(path: String) = {
    import scala.concurrent.duration._
    getDuration(path, java.util.concurrent.TimeUnit.MILLISECONDS).millis
  }
}

object Settings extends Settings(ConfigFactory.load)
