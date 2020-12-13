package object day4 {

  import java.io._
  import scala.io.Source

  val inputFile = new File("")

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

}
