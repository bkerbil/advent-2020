(ns advent.day-11.rule-test
  (:require [clojure.test :refer :all]
            [advent.day-11.rule :refer :all]))

(deftest action-test
  (testing "action"
    (testing "seat is empty and no occupied seats in sight"
      (is (= 1 (action-adjacent 0 [0 0 0 0 0 0 0 0])))
      (is (= 1 (action-adjacent 0 [0 0])))
      (is (= 1 (action-adjacent 0 []))))
    (testing "seat is occupied and four or more seats are also occupied"
      (is (= 0 (action-adjacent 1 [1 1 1 1 0 0 0 0])))
      (is (= 0 (action-adjacent 1 [1 1 1 1 1 0 0 0])))
      (is (= 0 (action-adjacent 1 [1 1 1 1 1 1 1 1]))))
    (testing "default case, should return given value"
      (is (= 1 (action-adjacent 1 [0 0 0 0 0 0 0 0])))
      (is (= 0 (action-adjacent 0 [1 0 0 0 0 0 0 0]))))))