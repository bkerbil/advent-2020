(ns advent.day-08.solution-test
  (:require [clojure.test :refer :all]
            [boot-code.data :as data]
            [boot-code.machine :as machine]
            [advent.day-08.solution :as solution]
            [clojure.string :as str]))

(def instructions (->> (slurp "./src/advent/day_08/input.txt")
                       str/split-lines
                       (map data/string->instruction)
                       vec))

(def state (ref instructions :validator machine/state-validator-fn))
(def pointer (ref 0 :validator machine/pointer-validator-fn))
(def accumulator (ref 0 :validator machine/accumulator-validator-fn))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 1801 (solution/solve-first state pointer accumulator)))))