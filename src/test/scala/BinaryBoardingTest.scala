import org.scalatest.{FunSuite, Matchers}
import day5.BinaryBoarding._

class BinaryBoardingTest extends FunSuite with Matchers {
  test("BFFFBBFRRR: row 70, column 7, seat ID 567") {
    binarySeat2Seat("BFFFBBFRRR") should be (Seat(70, 7, 567))
  }

  test("FFFBBBFRRR: row 14, column 7, seat ID 119") {
    binarySeat2Seat("FFFBBBFRRR") should be (Seat(14, 7, 119))
  }

  test("BBFFBBFRLL: row 102, column 4, seat ID 820.") {
    binarySeat2Seat("BBFFBBFRLL") should be (Seat(102, 4, 820))
  }
}
