(ns advent.day-12.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-12.solution :as solution]
            [advent.day-12.parser :as parser]))

(def instructions (->> "./src/advent/day_12/input.txt" slurp parser/data->instructions))

(deftest known-examples-test
  (testing "known examples for first puzzle"
    (is (= 25 (solution/solve-first (parser/data->instructions "F10\nN3\nF7\nR90\nF11"))))
    ;(testing "known examples for second puzzle"
    ))


(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 508 (solution/solve-first instructions)))
    ;(is (= 9000 (solution/solve-second numbers)))
    ))