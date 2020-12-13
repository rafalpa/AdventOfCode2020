package day4

import scala.io.Source
import java.io.File

object PassportParser extends App {


  type Passport = Map[String, String]
  val expectedFields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
  val filename = "./src/main/resources/input.txt"

  val splitInputIntoPassports: String => List[String] = (s: String) => s.split("\n\n").toList

  val parseStrings2Passports: List[String] => List[Passport] = (input: List[String]) => input
    .map(p => p.split("\\s+")) // split by one or many whitespaces
    .map(pass => pass
      .map {
        field =>
          val fieldSplitted: Array[String] = field.split(":")
          (fieldSplitted(0), fieldSplitted(1))
      }
      .toMap
    )

  val addValidationInfo: List[Passport] => List[(Passport, Boolean)] = (passports: List[Passport]) => passports.map(pass => (pass, expectedFields.subsetOf(pass.keySet)))

  val divideBetweenValidInvalid: List[(Passport, Boolean)] => (List[(Passport, Boolean)], List[(Passport, Boolean)]) = (step3: List[(Passport, Boolean)]) => step3.partition(_._2)

  val (validPassports, invalidPassports) = withFileAsString(filename) {
    splitInputIntoPassports andThen
      parseStrings2Passports andThen
      addValidationInfo andThen
      divideBetweenValidInvalid
  }

  println("valid passports:")
  validPassports.foreach(pass => println(s"${pass}\n>>>>>>>>>>>>>>>>"))
  println("invalid passports:")
  invalidPassports.foreach(pass => println(s"${pass}\n>>>>>>>>>>>>>>>>"))


  println(s"valid passports count: ${validPassports.size}")
  println(s"invalid passports count: ${invalidPassports.size}")

}
