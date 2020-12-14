(ns advent.day-11-version2.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-11-version2.parser :as parser]
            [advent.day-11-version2.chart]
            [advent.day-11-version2.solution :as solution]))

(deftest known-examples-test
  (let [state (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n")]
    (testing "known examples for first puzzle"
      (is (= 37 (solution/solve-first state))))
    (testing "known examples for second puzzle"
      (is (= 26 (solution/solve-second state))))))