(ns advent.day-05.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-05.solution :as solution]))

(def rows 128)
(def columns 8)
(def data (slurp "./src/advent/day_05/input.txt"))

(deftest solve-test
  (testing "should return right value for first and second puzzles"
    (is (= 955 (solution/solve-first rows columns data)))
    (is (= 569 (solution/solve-second rows columns data)))))