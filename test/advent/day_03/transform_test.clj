(ns advent.day-03.transform-test
  (:require [clojure.test :refer :all]
            [advent.day-03.transform :refer :all]))

(deftest transform-test
  (testing "given a sequence of strings, should convert into multidimensional vector of keywords"
    (is (= [[0 1]
            [1 0]] (transform [".#" "#."])))
    (is (= [[0 1 0 0]
            [1 1 0 0]
            [0 0 0 0]
            [1 1 1 1]] (transform [".#.." "##.." "...." "####"])))))