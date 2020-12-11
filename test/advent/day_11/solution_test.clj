(ns advent.day-11.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-11.parser :as parser]
            [advent.day-11.solution :as solution]))

(def numbers (->> "./src/advent/day_11/input.txt" slurp parser/data->chart))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 2338 (solution/solve-first numbers)))
    ;(is (= 3543369523456 (solution/solve-second numbers)))
    ))

(def numbers-example-1 (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n"))

(deftest known-examples-test
  (testing "known examples for first puzzle"
    (is (= 37 (solution/solve-first numbers-example-1))))
  (testing "known examples for second puzzle"
    ))