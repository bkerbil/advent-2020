(ns advent.day-11.generate-test
  (:require [clojure.test :refer :all]
            [advent.day-11.generate :refer :all]))

(deftest opposite-test
  (testing "opposite /"
    (testing "should return opposite value"
      (is (= 123 (opposite -123)))
      (is (= 0 (opposite 0)))
      (is (= -123 (opposite 123)))
      (is (= -9000 (opposite 9000))))))

(deftest generate-x-axis-test
  (testing "generate-x-axis /"
    (testing "should return sequence of tuples, where x value goes from negative width to positive width, while y value stays zero"
      (is (= [[-1 0] [0 0] [1 0]] (generate-x-axis 1)))
      (is (= [[-2 0] [-1 0] [0 0] [1 0] [2 0]] (generate-x-axis 2)))
      (is (= [[-3 0] [-2 0] [-1 0] [0 0] [1 0] [2 0] [3 0]] (generate-x-axis 3))))))

(deftest generate-y-axis-test
  (testing "generate-y-axis /"
    (testing "should return sequence of tuples, where y value goes from negative height to positive height, while x value stays zero"
      (is (= [[0 -1] [0 0] [0 1]] (generate-y-axis 1)))
      (is (= [[0 -2] [0 -1] [0 0] [0 1] [0 2]] (generate-y-axis 2)))
      (is (= [[0 -3] [0 -2] [0 -1] [0 0] [0 1] [0 2] [0 3]] (generate-y-axis 3))))))

(deftest generate-diagonal-test
  (testing "generate-diagonal /"
    (testing "should return sequence of tuples, where absolute values of x and y are same"
      (is (= [[-1 -1] [1 -1] [0 0] [-1 1] [1 1]] (generate-diagonal 1 1)))
      (is (= [[-2 -2] [2 -2] [-1 -1] [1 -1] [0 0] [-1 1] [1 1] [-2 2] [2 2]] (generate-diagonal 2 2))))))

(deftest generate-line-of-sight-test
  (testing "generate-line-of-sight /"
    (testing "should give x-axis, y-axis and diagonal with center removed"
      (is (= [[-2 0] [-1 0] [1 0]
              [2 0] [0 -2] [0 -1]
              [0 1] [0 2] [-2 -2]
              [2 -2] [-1 -1] [1 -1]
              [-1 1] [1 1] [-2 2] [2 2]] (generate-line-of-sight 2 2))))))