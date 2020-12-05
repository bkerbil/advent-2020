(ns advent.day-05.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-05.solution :as solution]))

(def data (slurp "./src/advent/day_05/input.txt"))

(deftest solve-test
  (testing "should return right value for first and second puzzles"
    (is (= 955 (solution/solve-first data)))
    (is (= 569 (solution/solve-second data)))))