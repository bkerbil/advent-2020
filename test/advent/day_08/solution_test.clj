(ns advent.day-08.solution-test
  (:require [clojure.test :refer :all]
            [boot-code.machine :as machine]
            [advent.day-08.parser :as parser]
            [advent.day-08.solution :as solution]
            [clojure.string :as str]))

(def instructions (->> (slurp "./src/advent/day_08/input.txt")
                       str/split-lines
                       parser/string->instructions))
(def changes (parser/changes instructions))

(def state (ref instructions :validator machine/state-validator-fn))
(def pointer (ref 0 :validator machine/pointer-validator-fn))
(def accumulator (ref 0 :validator machine/accumulator-validator-fn))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= {:accumulator 1801, :halted? true} (solution/solve-first instructions)))
    (is (= {:accumulator 2060, :halted? false} (solution/solve-second state pointer accumulator changes)))))