(ns advent.day-18.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-18.parser :as parser]
            [advent.day-18.solution :as solution]))

(def equations (->> "./src/advent/day_18/input.txt" slurp parser/parse-file))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 31142189909908 (solution/solve-first equations)))
    (is (= 323912478287549 (solution/solve-second equations)))))