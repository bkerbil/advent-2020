(ns advent.day-12.solution-test
  (:require
    [clojure.test :refer :all]
    [advent.day-12.parser :as parser]
    [advent.day-12.solution :as solution]))

(def instructions (->> "./src/advent/day_12/input.txt" slurp parser/data->instructions))

(deftest known-examples-test
  (let [example-instructions "F10\nN3\nF7\nR90\nF11"]
    (testing "known examples for first and second puzzles"
      (is (= 25 (solution/solve-first (parser/data->instructions example-instructions)))))
    (is (= 286 (solution/solve-second (parser/data->instructions example-instructions))))))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 508 (solution/solve-first instructions)))
    (is (= 30761 (solution/solve-second instructions)))))