(ns advent.day-10.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-10.parser :as parser]
            [advent.day-10.solution :as solution]))

(def numbers (->> "./src/advent/day_10/input.txt" slurp parser/data->numbers (apply conj [0 157]) sort))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 1984 (solution/solve-first numbers)))
    (is (= 3543369523456 (solution/solve-second numbers)))))

(def numbers-example-1 (sort [16 10 15 5 1 11 7 19 6 12 4 0 22]))
(def numbers-example-2 (sort [28 33 18 42 31 14 46 20 48 47
                              24 23 49 45 19 38 39 11 1 32
                              25 35 8 17 7 9 4 2 34 10 3 0 52]))

(deftest known-examples-test
  (testing "known examples for first puzzle"
    (is (= 35 (solution/solve-first numbers-example-1)))
    (is (= 220 (solution/solve-first numbers-example-2))))
  (testing "known examples for second puzzle"
    (is (= 8 (solution/solve-second numbers-example-1)))
    (is (= 19208 (solution/solve-second numbers-example-2)))))