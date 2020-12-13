package day4

object PassportParser {

  type Passport = Map[String, String]
  val expectedFields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

  val splitInputIntoPassports: String => List[String] = (s: String) => s.split("\n\n").toList

  val parseStrings2Passports: String => Passport = (input: String) => input
    .split("\\s+") // split by one or many whitespaces
    .map {
      field =>
        val fieldSplitted: Array[String] = field.split(fieldSeparator)
        (fieldSplitted(0), fieldSplitted(1))
    }
    .toMap

  val validatePassport: Passport => Boolean = (passport: Passport) => expectedFields.subsetOf(passport.keySet)

  val processEntry: String => Boolean = parseStrings2Passports andThen validatePassport

  val divideBetweenValidInvalid: List[(Passport, Boolean)] => (List[(Passport, Boolean)], List[(Passport, Boolean)]) = (step3: List[(Passport, Boolean)]) => step3.partition(_._2)

  def sanityCheck(): Unit = {
    // for inspection
    val (validPassports, invalidPassports) = withFileAsString(filename) {
      splitInputIntoPassports
    }
      .map(parseStrings2Passports)
      .partition(validatePassport)

    println("valid passports:")
    validPassports.foreach(println)
    println(">>>>>>>>>>>>>>>>>>>>>>>>>\n\n\ninvalid passports:")
    invalidPassports.foreach(println)

    println(s"valid passports count: ${validPassports.size}")
    println(s"invalid passports count: ${invalidPassports.size}")
  }

  def main(args: Array[String]): Unit = {

    //    uncomment for verbose inspection of passport validation
    //    sanityCheck()

    // final solution
    val result = withFileAsString(filename) {
      splitInputIntoPassports
    }
      .count(processEntry)

    println(s"count of valid passports: $result")
  }

}