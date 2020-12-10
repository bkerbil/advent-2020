(ns advent.day-10.path-test
  (:require [clojure.test :refer :all]
            [advent.day-10.path :refer :all]))

(deftest same-path?-test
  (testing "same-path? /"
    (testing "if one sequence is subset of another, then return true"
      (is (true? (same-path? [1 2 3] [1 2 3])))
      (is (true? (same-path? [1 2 3] [1 2 3 4])))
      (is (true? (same-path? [1 2] [1 2 3 4])))
      (is (true? (same-path? [1 2 1354 59 248342] [1 2]))))
    (testing "different value should immediately return false"
      (is (false? (same-path? [1 2 3 4 5] [1 2 3 4 6])))
      (is (false? (same-path? [1 2 3 4 6] [1 2 3 4 5]))))))