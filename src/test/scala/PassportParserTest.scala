import org.scalatest.FunSuite
import day4.PassportParser._

class PassportParserTest extends FunSuite {
  test("first passport is valid - all eight fields are present") {
    val entry = "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd\nbyr:1937 iyr:2017 cid:147 hgt:183cm"
    assert(processEntry(entry))
  }

  test("The second passport is invalid - it is missing hgt (the Height field)") {
    val entry = "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884\nhcl:#cfa07d byr:1929"
    assert(!processEntry(entry))
  }

  test("The third passport is interesting; the only missing field is cid, so it looks like data from North Pole Credentials, not a passport at all! Surely, nobody would mind if you made the system temporarily ignore missing cid fields. Treat this \"passport\" as valid") {
    val entry = "hcl:#ae17e1 iyr:2013\neyr:2024\necl:brn pid:760753108 byr:1931\nhgt:179cm"
    assert(processEntry(entry))
  }

  test("The fourth passport is missing two fields, cid and byr. Missing cid is fine, but missing any other field is not, so this passport is invalid") {
    val entry = "hcl:#cfa07d eyr:2025 pid:166559648\niyr:2011 ecl:brn hgt:59in"
    assert(!processEntry(entry))
  }

}
