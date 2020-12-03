(ns advent.day-03.transform-test
  (:require [clojure.test :refer :all]
            [advent.day-03.transform :refer :all]))

(deftest transform-test
  (testing "given a sequence of strings, should convert into multidimensional vector of keywords"
    (is (= [[:open :tree]
            [:tree :open]] (transform [".#" "#."])))
    (is (= [[:open :tree :open :open]
            [:tree :tree :open :open]
            [:open :open :open :open]
            [:tree :tree :tree :tree]] (transform [".#.." "##.." "...." "####"])))))