package object day4 {

  import java.io._
  import scala.io.Source
  import com.typesafe.config._


  def withFileAsString[A](filename: String)(fn: String => A): A = {
    val source = Source.fromFile(filename)
    try{
      fn(source.mkString.trim)
    } finally source.close()
  }

  def withFileLines[A](file: File)(fn: Seq[String] => A): A = {
    val source = Source.fromFile(file)
    try{
      fn(source.getLines().toList)
    } finally source.close()
  }



  val config: Config = ConfigFactory.load().resolve().getConfig("day4")

  val filename: String = config.getString("filename")
  val fieldSeparator: String = config.getString("field_separator")

}
