(ns advent.day-11.vision-test
  (:require [clojure.test :refer :all]
            [advent.day-11.vision :refer :all]
            [advent.day-11.parser :as parser]))

(def chart
  (parser/data->chart
    "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n"))

(deftest neighbours-test
  (let [directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]]]
    (testing "neighbours-adjacent-fn"
      (testing "chart"
        (is (= [0 0] (neighbours-adjacent-fn chart 0 0 directions)))
        (is (= [0 0 0 0 0] (neighbours-adjacent-fn chart 1 0 directions)))
        (is (= [0 0 0 0] (neighbours-adjacent-fn chart 2 0 directions)))
        (is (= [0 0 0] (neighbours-adjacent-fn chart 9 0 directions)))
        (is (= [0 0 0] (neighbours-adjacent-fn chart 9 1 directions)))
        (is (= [0] (neighbours-adjacent-fn chart 0 9 directions)))
        (is (= [0 0] (neighbours-adjacent-fn chart 9 9 directions)))
        (is (= [0 0 0 0 0 0 0 0] (neighbours-adjacent-fn chart 4 8 directions)))
        (is (= [0 0 0 0 0] (neighbours-adjacent-fn chart 5 5 directions)))))
    (testing "neighbours-line-of-sight-fn"
      (testing "chart post corona"
        (is (= [0 0 0 0 0 0] (neighbours-line-of-sight-fn chart 7 2 directions)))))))