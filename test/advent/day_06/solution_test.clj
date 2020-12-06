(ns advent.day-06.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-06.solution :as solution]))

(def data (slurp "./src/advent/day_06/input.txt"))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 6748 (solution/solve-first data)))
    (is (= 3445 (solution/solve-second data)))))