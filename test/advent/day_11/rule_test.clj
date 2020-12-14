(ns advent.day-11.rule-test
  (:require [clojure.test :refer :all]
            [advent.day-11.rule :refer :all]))

(deftest rule-test
  (testing "rule"
    (testing "empty [chair]"
      (testing "there is more than one adjacent occupied seat"
        (is (= 0 (pre-corona {:point 0 :occupied 1})))
        (is (= 0 (pre-corona {:point 0 :occupied 4})))
        (is (= 0 (pre-corona {:point 0 :occupied 5})))
        (is (= 0 (pre-corona {:point 0 :occupied 8}))))
      (testing "there is no occupied adjacent seats")
      (is (= 1 (pre-corona {:point 0 :occupied 0}))))
    (testing "occupied [chair]"
      (testing "there is four or more adjacent occupied seats"
        (is (= 0 (pre-corona {:point 1 :occupied 4})))
        (is (= 0 (pre-corona {:point 1 :occupied 5})))
        (is (= 0 (pre-corona {:point 1 :occupied 7})))
        (is (= 0 (pre-corona {:point 1 :occupied 8}))))
      (testing "there is less than four adjacent occupied seats"
        (is (= 1 (pre-corona {:point 1 :occupied 0})))
        (is (= 1 (pre-corona {:point 1 :occupied 1})))
        (is (= 1 (pre-corona {:point 1 :occupied 2})))
        (is (= 1 (pre-corona {:point 1 :occupied 3})))))
    (testing "floor"
      (is (nil? (pre-corona {:point nil}))))))