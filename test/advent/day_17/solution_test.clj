(ns advent.day-17.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-17.parser :as parser]
            [advent.day-17.solution :as solution]))

(def space-2d (->> "./src/advent/day_17/input.txt" slurp parser/string->2d-space))
(def cycles 6)

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 273 (solution/solve-first cycles space-2d)))))

(deftest known-examples-test
  (let [example-space-2d [[[0 1 0] [0 0 1] [1 1 1]]]]
    (testing "known examples for first puzzle"
      (testing "should have N amount of cubes after given cycles"
        (is (= 11 (solution/solve-first 1 example-space-2d)))
        (is (= 21 (solution/solve-first 2 example-space-2d)))
        (is (= 38 (solution/solve-first 3 example-space-2d)))))
    (testing "known examples for second puzzle"
      (testing "should have N amount of cubes after given cycles"
        (is (= 29 (solution/solve-second 1 example-space-2d)))
        (is (= 60 (solution/solve-second 2 example-space-2d)))))))