(ns advent.day-11.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-11.parser :as parser]
            [advent.day-11.solution :as solution]))

(deftest solve-test
  (let [chart (->> "./src/advent/day_11/input.txt" slurp parser/data->chart)
        width 98
        height 93]
    (testing "should return correct value for first and second puzzles"
      (is (= 2338 (solution/solve-first width height chart))))))

(deftest known-examples-test
  (let [chart (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n")
        width 10
        height 10]
    (testing "known examples for first puzzle"
      (is (= 37 (solution/solve-first width height chart))))
    (testing "known examples for second puzzle")))