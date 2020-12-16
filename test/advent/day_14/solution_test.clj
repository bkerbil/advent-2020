(ns advent.day-14.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-14.parser :as parser]
            [advent.day-14.solution :as solution]))

(def example-input "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\nmem[8] = 11\nmem[7] = 101\nmem[8] = 0")

(deftest known-examples-test
  (let [instructions (parser/string->instructions example-input)]
    (testing "known examples for first puzzle"
      (is (= 165 (solution/solve-first instructions))))
    (testing "known examples for second puzzle"
      ;(is (= 26 (solution/solve-second instructions)))
      )))

(deftest solve-test
  (let [instructions (->> "./src/advent/day_14/input.txt" slurp parser/string->instructions)]
    (testing "should return correct value for first and second puzzles"
      (is (= 15919415426101 (solution/solve-first instructions)))
      ;(is (= 30761 (solution/solve-second instructions)))
      )))