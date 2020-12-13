package day5

import java.io.File
import scala.annotation.tailrec
import scala.io.Source

object BinaryBoarding {

  case class Seat(row: Int, column: Int, id: Int)

  object Seat {
    implicit val seatOrderingById: Ordering[Seat] = Ordering.fromLessThan((a, b) => a.id.compareTo(b.id) < 0 )
  }

  def getSeatId(row: Int, col: Int): Int = row * 8 + col

  def binarySeat2Seat(s: String): Seat = {
    val (binaryRow, binaryCol) = s.splitAt(7)
    val row = binaryStr2Num(binaryRow, BinaryRow)
    val col = binaryStr2Num(binaryCol, BinaryColumn)
    val id = getSeatId(row, col)
    Seat(row, col, id)
  }

  def binaryStr2Num(s: String, encoding: BinaryEncoding): Int = {
    val zeroChar = encoding.zeroChar
    val oneChar = encoding.oneCHar
    @tailrec
    def loop(remaining: String, acc: Int): Int = {
      if (remaining.isEmpty) acc
      else {
        val currChar = remaining.head
        val num = (currChar match {
          case `zeroChar` => 0.0
          case `oneChar` => scala.math.pow(2,remaining.length - 1)
        }).toInt
        loop(remaining.tail, acc + num)
      }
    }

    loop(s, 0)
  }

  def getMissingSeatId(ids: Set[Int]): Int = {
    ((ids.min to ids.max).toSet diff ids).head
  }

  def main(args: Array[String]): Unit = {

    val allSeats = withFileLines(filename){
      _.map(binarySeat2Seat)
    }

    val highestId = allSeats.max.id

    println(">>PART 1")
    println(s"Highest ID: $highestId")

    val missingId = getMissingSeatId(allSeats.map(_.id).toSet)

    println(">>PART 2")
    println(s"missing id is: $missingId")

  }

}
