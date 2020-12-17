(ns advent.day-14.docking-program-version-2-test
  (:require [clojure.test :refer :all]
            [advent.day-14.docking-program-version-2 :refer :all]))

(deftest apply-bitmask-test
  (testing "When this program goes to write to memory address 42, it first applies the bitmask"
    (is (= "000000000000000000000000000000X1101X" (apply-bitmask "000000000000000000000000000000X1001X" 42)))))

(deftest add-0-and-1-to-tails-test
  (testing "when given empty vector, should return"
    (is (= [[\0] [\1]] (add-0-and-1-to-tails []))))
  (testing "when given vector with values, should add 0 and 1 to each values ends"
    (is (= [[\0 \0] [\0 \1] [\1 \0] [\1 \1]] (add-0-and-1-to-tails [[\0] [\1]])))
    (is (= [[\0 \0 \0] [\0 \0 \1] [\1 \1 \0] [\1 \1 \1]] (add-0-and-1-to-tails [[\0 \0] [\1 \1]])))))

(deftest add-value-to-tails-test
  (testing "when given empty tails, should add just value"
    (is (= [[\0]] (add-value-to-tails [] \0)))
    (is (= [[\1]] (add-value-to-tails [] \1))))
  (testing "when given vector with values, should add value at end"
    (is (= [[\0 \0 \0 \0]] (add-value-to-tails [[\0 \0 \0]] \0)))
    (is (= [[\0 \0 \0 \1]] (add-value-to-tails [[\0 \0 \0]] \1)))))

(deftest expand-bitmask-test
  (is (= #{"000000000000000000000000000000011010"
           "000000000000000000000000000000011011"
           "000000000000000000000000000000111010"
           "000000000000000000000000000000111011"} (set (expand-bitmask "000000000000000000000000000000X1101X")))))