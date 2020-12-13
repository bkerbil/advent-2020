(ns advent.day-11-version2.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-11-version2.parser :as parser]
            [advent.day-11-version2.protocols]
            [advent.day-11-version2.pre-corona]
            [advent.day-11-version2.solution :as solution]))

(deftest solve-test
  (let [chart (->> "./src/advent/day_11/input.txt" slurp parser/data->state)
        width 98
        height 93]
    (testing "should return correct value for first and second puzzles"
      ; TODO fix, too slow
      ;(is (= 2338 (solution/solve-first width height chart adjacent)))
      ;(is (= 2134 (solution/solve-first width height chart adjacent-line-of-sight)))
      )))

(deftest known-examples-test
  (let [state (parser/data->state "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n")]
    (testing "known examples for first puzzle"
      (is (= 37 (solution/solve-first state))))
    (testing "known examples for second puzzle"
      ;(is (= 26 (solution/solve-second state)))
      )))