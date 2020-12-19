(ns advent.day-15.memory-game-test
  (:require [clojure.test :refer :all]
            [advent.day-15.memory-game :refer :all]))

(deftest first-spoken?-test
  (testing "first-spoken?"
    (testing "if a given number has not been spoken before, then return true"
      (is (true? (first-spoken? 0 {})))
      (is (true? (first-spoken? 1 {1 [0]})))
      (is (true? (first-spoken? 3 {0 [1]})))
      (is (true? (first-spoken? 6 {0 [1] 3 [2]})))
      (is (true? (first-spoken? 6 {0 [1] 3 [2] 6 [3]})))
      (is (true? (first-spoken? 1 {0 [1 4] 3 [5 6] 6 [3]})))
      (is (true? (first-spoken? 4 {0 [4 8] 1 [7] 3 [5 6] 6 [3]}))))
    (testing "if a given number has been spoken before, then return false"
      (is (false? (first-spoken? 0 {0 [1 4] 3 [2] 6 [3]})))
      (is (false? (first-spoken? 3 {0 [1 4] 3 [2 5] 6 [3]})))
      (is (false? (first-spoken? 3 {0 [1 4] 3 [5 6] 6 [3]})))
      (is (false? (first-spoken? 0 {0 [4 8] 1 [7] 3 [5 6] 6 [3]}))))))

(deftest update-history-test
  (testing "update-history"
    (testing "should add turn to spoken number"
      (is (= {0 [1]} (update-history 0 1 {})))
      (is (= {0 [1] 3 [2]} (update-history 3 2 {0 [1]})))
      (is (= {0 [1] 3 [2] 6 [3]} (update-history 6 3 {0 [1] 3 [2]})))
      (is (= {0 [4 1] 3 [2] 6 [3]} (update-history 0 4 {0 [1] 3 [2] 6 [3]})))
      (is (= {0 [4 1] 3 [5 2] 6 [3]} (update-history 3 5 {0 [4 1] 3 [2] 6 [3]})))
      (is (= {0 [4 1] 3 [6 5] 6 [3]} (update-history 3 6 {0 [4 1] 3 [5 2] 6 [3]})))
      (is (= {0 [4 1] 1 [7] 3 [6 5] 6 [3]} (update-history 1 7 {0 [4 1] 3 [6 5] 6 [3]})))
      (is (= {0 [8 4] 1 [7] 3 [6 5] 6 [3]} (update-history 0 8 {0 [4 1] 1 [7] 3 [6 5] 6 [3]})))
      (is (= {0 [8 4] 1 [7] 3 [6 5] 4 [9] 6 [3]} (update-history 4 9 {0 [8 4] 1 [7] 3 [6 5] 6 [3]})))
      (is (= {0 [10 8] 1 [7] 3 [6 5] 4 [9] 6 [3]} (update-history 0 10 {0 [8 4] 1 [7] 3 [6 5] 4 [9] 6 [3]}))))))

(deftest speak-test
  (testing "speak"
    (testing "known state changes"
      (let [turn-3 {:last-spoken 6, :last-turn 3, :history {0 [1], 3 [2], 6 [3]}}
            turn-4 {:last-spoken 0, :last-turn 4, :history {0 [4 1], 3 [2], 6 [3]}}
            turn-5 {:last-spoken 3, :last-turn 5, :history {0 [4 1], 3 [5 2], 6 [3]}}
            turn-6 {:last-spoken 3, :last-turn 6, :history {0 [4 1], 3 [6 5], 6 [3]}}
            turn-7 {:last-spoken 1, :last-turn 7, :history {0 [4 1], 1 [7], 3 [6 5], 6 [3]}}
            turn-8 {:last-spoken 0, :last-turn 8, :history {0 [8 4], 1 [7], 3 [6 5], 6 [3]}}
            turn-9 {:last-spoken 4, :last-turn 9, :history {0 [8 4], 1 [7], 3 [6 5], 4 [9], 6 [3]}}
            turn-10 {:last-spoken 0, :last-turn 10, :history {0 [10 8], 1 [7], 3 [6 5], 4 [9], 6 [3]}}]
        (is (= turn-4 (speak turn-3)))
        (is (= turn-5 (speak turn-4)))
        (is (= turn-6 (speak turn-5)))
        (is (= turn-7 (speak turn-6)))
        (is (= turn-8 (speak turn-7)))
        (is (= turn-9 (speak turn-8)))
        (is (= turn-10 (speak turn-9)))))))