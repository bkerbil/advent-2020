(ns advent.day-04.passport-test
  (:require [clojure.test :refer :all]
            [advent.day-04.passport :as passport]
            [advent.day-04.parser :as parser]
            [clojure.spec.alpha :as s]))

(defn single-passport
  [text]
  (->> text
       parser/line->map
       passport/map->unqualified
       passport/unqualified->passport))

(deftest specifications-test
  (testing "specifications should work"
    (testing "byr (Birth Year) - four digits; at least 1920 and at most 2002."
      (is (true? (s/valid? ::passport/byr 1920)))
      (is (true? (s/valid? ::passport/byr 1961)))
      (is (true? (s/valid? ::passport/byr 2002)))
      (is (false? (s/valid? ::passport/byr 1919)))
      (is (false? (s/valid? ::passport/byr 2003))))
    (testing "iyr (Issue Year) - four digits; at least 2010 and at most 2020."
      (is (true? (s/valid? ::passport/iyr 2010)))
      (is (true? (s/valid? ::passport/iyr 2015)))
      (is (true? (s/valid? ::passport/iyr 2020)))
      (is (false? (s/valid? ::passport/iyr 2009)))
      (is (false? (s/valid? ::passport/iyr 2021))))
    (testing "eyr (Expiration Year) - four digits; at least 2020 and at most 2030."
      (is (true? (s/valid? ::passport/eyr 2020)))
      (is (true? (s/valid? ::passport/eyr 2025)))
      (is (true? (s/valid? ::passport/eyr 2030)))
      (is (false? (s/valid? ::passport/eyr 2019)))
      (is (false? (s/valid? ::passport/eyr 2031))))
    (testing "hgt (Height) - a number followed by either cm or in: "
      (testing "If cm, the number must be at least 150 and at most 193."
        (is (true? (s/valid? ::passport/hgt [150 :cm])))
        (is (true? (s/valid? ::passport/hgt [190 :cm])))
        (is (true? (s/valid? ::passport/hgt [193 :cm])))
        (is (false? (s/valid? ::passport/hgt [149 :cm])))
        (is (false? (s/valid? ::passport/hgt [194 :cm]))))
      (testing "If in, the number must be at least 59 and at most 76."
        (is (true? (s/valid? ::passport/hgt [59 :in])))
        (is (true? (s/valid? ::passport/hgt [60 :in])))
        (is (true? (s/valid? ::passport/hgt [76 :in])))
        (is (false? (s/valid? ::passport/hgt [58 :in])))
        (is (false? (s/valid? ::passport/hgt [77 :in])))))
    (testing "hcl (Hair Color) - a # followed by exactly six characters 0-9 or a-f."
      (is (true? (s/valid? ::passport/hcl "#123abc")))
      (is (false? (s/valid? ::passport/hcl "#123abz")))
      (is (false? (s/valid? ::passport/hcl "123abc"))))
    (testing "ecl (Eye Color) - exactly one of: amb blu brn gry grn hzl oth."
      (is (true? (s/valid? ::passport/ecl :brn)))
      (is (false? (s/valid? ::passport/ecl :wat))))
    (testing "pid (Passport ID) - a nine-digit number, including leading zeroes."
      (is (true? (s/valid? ::passport/pid "000000001")))
      (is (false? (s/valid? ::passport/pid "0123456789"))))
    (testing "cid (Country ID) - ignored, missing or not."
      (is (true? (s/valid? ::passport/cid 123)))
      (is (true? (s/valid? ::passport/cid 9000)))
      (is (true? (s/valid? ::passport/cid nil))))))

(deftest examples-tests
  (testing "invalid passports"
    (is (false? (passport/valid? (single-passport "eyr:1972 cid:100\nhcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926"))))
    (is (false? (passport/valid? (single-passport "iyr:2019\nhcl:#602927 eyr:1967 hgt:170cm\necl:grn pid:012533040 byr:1946"))))
    (is (false? (passport/valid? (single-passport "hcl:dab227 iyr:2012\necl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277"))))
    (is (false? (passport/valid? (single-passport "hgt:59cm ecl:zzz\neyr:2038 hcl:74454a iyr:2023\npid:3556412378 byr:2007")))))
  (testing "valid passports"
    (is (true? (passport/valid? (single-passport "pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980\nhcl:#623a2f"))))
    (is (true? (passport/valid? (single-passport "eyr:2029 ecl:blu cid:129 byr:1989\niyr:2014 pid:896056539 hcl:#a97842 hgt:165cm"))))
    (is (true? (passport/valid? (single-passport "hcl:#888785\nhgt:164cm byr:2001 iyr:2015 cid:88\npid:545766238 ecl:hzl\neyr:2022"))))
    (is (true? (passport/valid? (single-passport "iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719"))))))