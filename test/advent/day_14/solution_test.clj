(ns advent.day-14.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-14.parser :as parser]
            [advent.day-14.docking-program :refer [->Docking-Program]]
            [advent.day-14.docking-program-version-2 :refer [->Docking-Program-Version-2]]
            [advent.day-14.solution :as solution]))

(def docking-program (->Docking-Program {} nil))
(def docking-program-version-2 (->Docking-Program-Version-2 {} nil))

(deftest known-examples-test
  (let [example-input "mask = XXXXXXXXXXXXXXXXXXXXXXXXXXXXX1XXXX0X\nmem[8] = 11\nmem[7] = 101\nmem[8] = 0"
        example-input-2 "mask = 000000000000000000000000000000X1001X\nmem[42] = 100\nmask = 00000000000000000000000000000000X0XX\nmem[26] = 1"
        instructions (parser/string->instructions example-input)
        instructions-2 (parser/string->instructions example-input-2)]
    (testing "known examples for first puzzle"
      (is (= 165 (solution/solve instructions docking-program))))
    (testing "known examples for second puzzle"
      (is (= 208 (solution/solve instructions-2 docking-program-version-2))))))

(deftest solve-test
  (let [instructions (->> "./src/advent/day_14/input.txt" slurp parser/string->instructions)]
    (testing "should return correct value for first and second puzzles"
      (is (= 15919415426101 (solution/solve instructions docking-program)))
      (is (= 3443997590975 (solution/solve instructions docking-program-version-2))))))