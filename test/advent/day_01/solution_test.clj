(ns advent.day-01.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-01.solution :as s]))

(def mock-input [1721 979 366 299 675 1456])
(def target-value 2020)

(deftest solve-test
  (testing "given entries and a sum, should find such set of numbers which equal to the given sum if summed,
  and return multiplication of such numbers"
    (is (= 514579 (s/solve-first mock-input target-value)))
    (is (= 241861950 (s/solve-second mock-input target-value)))))