package day5

import java.io.File
import scala.annotation.tailrec
import scala.io.Source

object BinaryBoarding extends App {

  case class BinarySeat(row: BinaryEncoding)

  case class Seat(row: Int, column: Int, id: Int)

  def getSeatId(row: Int, col: Int) = row * 8 + col

  def binarySeat2Seat(s: String): Seat = {
    val (binaryRow, binaryCol) = s.splitAt(7)
    val row = binaryStr2Num(binaryRow, 'F' , 'B')
    val col = binaryStr2Num(binaryCol, 'L' , 'R')
    val id = getSeatId(row, col)
    Seat(row, col, id)
  }

  def binaryStr2Num(s: String, zeroChar: Char, oneChar: Char): Int = {
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

  def withFileLines[A](file: File)(fn: Seq[String] => A): A = {
    val source = Source.fromFile(file)
    try {
      fn(source.getLines().toList)
    } finally source.close()
  }


}
