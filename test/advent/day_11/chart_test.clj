(ns advent.day-11.chart-test
  (:require [clojure.test :refer :all]
            [advent.day-11.chart :refer :all]))

(deftest coordinate->pointer-test
  (testing "coordinate->pointer"
    (testing "with 5 x 5 grid"
      (is (= 0 (coordinate->pointer 0 0 5)))
      (is (= 1 (coordinate->pointer 1 0 5)))
      (is (= 2 (coordinate->pointer 2 0 5)))
      (is (= 3 (coordinate->pointer 3 0 5)))
      (is (= 4 (coordinate->pointer 4 0 5)))
      (is (= 5 (coordinate->pointer 0 1 5)))
      (is (= 6 (coordinate->pointer 1 1 5)))
      (is (= 7 (coordinate->pointer 2 1 5)))
      (is (= 8 (coordinate->pointer 3 1 5)))
      (is (= 9 (coordinate->pointer 4 1 5)))
      (is (= 10 (coordinate->pointer 0 2 5)))
      (is (= 24 (coordinate->pointer 4 4 5))))
    (testing "with 2 x 5 grid"
      (is (= 0 (coordinate->pointer 0 0 2)))
      (is (= 1 (coordinate->pointer 1 0 2)))
      (is (= 2 (coordinate->pointer 0 1 2)))
      (is (= 3 (coordinate->pointer 1 1 2)))
      (is (= 4 (coordinate->pointer 0 2 2)))
      (is (= 5 (coordinate->pointer 1 2 2)))
      (is (= 6 (coordinate->pointer 0 3 2)))
      (is (= 7 (coordinate->pointer 1 3 2)))
      (is (= 8 (coordinate->pointer 0 4 2)))
      (is (= 9 (coordinate->pointer 1 4 2))))))

(deftest neighbours-adjacent-test
  (testing "neighbours-adjacent"
    (let [chart [1 nil 1 1 1 1 1 nil 1]]
      (is (= [1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 0 0 3 3)))
      (is (= [1 1 1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 1 0 3 3)))
      (is (= [1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 2 0 3 3)))
      (is (= [1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 0 1 3 3)))
      (is (= [1 1 1 1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 1 1 3 3)))
      (is (= [1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 2 1 3 3)))
      (is (= [1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 0 2 3 3)))
      (is (= [1 1 1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 1 2 3 3)))
      (is (= [1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 2 2 3 3))))
    (let [chart [1 nil 1 1 1 1 1 1 1 nil 1 nil 1 1 1 1]]
      (is (= [1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 0 0 4 4)))
      (is (= [1 1 1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 1 0 4 4)))
      (is (= [1 1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 2 0 4 4)))
      (is (= [1 1 1] (neighbours-adjacent chart [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]] 3 0 4 4))))))