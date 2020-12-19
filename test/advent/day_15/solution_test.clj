(ns advent.day-15.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-15.memory-game :as game]
            [advent.day-15.solution :as solution]))

(deftest known-examples-test
  (let [target 2020]
    (testing "known examples for first puzzle"
      (is (= 1 (solution/solve (game/initialize [1 3 2]) target)))
      (is (= 10 (solution/solve (game/initialize [2 1 3]) target)))
      (is (= 27 (solution/solve (game/initialize [1 2 3]) target)))
      (is (= 78 (solution/solve (game/initialize [2 3 1]) target)))
      (is (= 438 (solution/solve (game/initialize [3 2 1]) target)))
      (is (= 1836 (solution/solve (game/initialize [3 1 2]) target))))))

(deftest solve-test
  (let [target 2020]
    (testing "should return correct value for first and second puzzles"
      (is (= 517 (solution/solve (game/initialize [5 2 8 16 18 0 1]) target))))))