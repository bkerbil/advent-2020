(ns advent.day-11.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-11.parser :as parser]
            [advent.day-11.solution :as solution]
            [advent.day-11.generate :as generate]))

(deftest solve-test
  (let [chart (->> "./src/advent/day_11/input.txt" slurp parser/data->chart)
        width 98
        height 93
        adjacent [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]]
        adjacent-line-of-sight (generate/generate-line-of-sight width height)]
    (testing "should return correct value for first and second puzzles"
      ; TODO fix, too slow
      ;(is (= 2338 (solution/solve-first width height chart adjacent)))
      ;(is (= 2134 (solution/solve-first width height chart adjacent-line-of-sight)))
      )))

(deftest known-examples-test
  (let [chart (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n")
        width 10
        height 10
        adjacent [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]]
        adjacent-line-of-sight (generate/generate-line-of-sight width height)]
    (testing "known examples for first puzzle"
      (is (= 37 (solution/solve-first width height chart adjacent))))
    (testing "known examples for second puzzle"
      (is (= 26 (solution/solve-second width height chart adjacent-line-of-sight))))))