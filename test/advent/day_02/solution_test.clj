(ns advent.day-02.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-02.solution :as solution]
            [clojure.string :as str]))

(def passwords (->> (slurp "./src/advent/day_02/input.txt")
                    str/split-lines))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 410 (solution/solve-first passwords)))
    (is (= 694 (solution/solve-second passwords)))))