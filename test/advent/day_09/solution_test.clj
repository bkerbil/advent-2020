(ns advent.day-09.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-09.parser :as parser]
            [advent.day-09.solution :as solution]))

(def numbers (parser/data->numbers (slurp "./src/advent/day_09/input.txt")))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 556543474 (solution/solve-first 25 numbers)))
    (is (= 76096372 (solution/solve-second 556543474 numbers)))))