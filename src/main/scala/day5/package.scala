import com.typesafe.config.{Config, ConfigFactory}

import scala.io.Source

package object day5 {

  sealed trait BinaryEncoding {
    def zeroChar: Char

    def oneCHar: Char
  }

  case object BinaryRow extends BinaryEncoding {
    override def zeroChar: Char = 'F'

    override def oneCHar: Char = 'B'
  }

  case object BinaryColumn extends BinaryEncoding {
    override def zeroChar: Char = 'L'

    override def oneCHar: Char = 'R'
  }


  def withFileLines[A](file: String)(fn: Seq[String] => A): A = {
    val source = Source.fromFile(file)
    try {
      fn(source.getLines().toList)
    } finally source.close()
  }

  val config: Config = ConfigFactory.load().resolve().getConfig("day5")

  val filename: String = config.getString("filename")

}
