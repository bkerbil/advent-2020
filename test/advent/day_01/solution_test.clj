(ns advent.day-01.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-01.solution :as solution]
            [clojure.string :as str]))

(def entries (->> (slurp "./src/advent/day_01/input.txt")
                  str/split-lines
                  (map #(Integer/parseInt %))))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 1018944 (solution/solve-first entries 2020)))
    (is (= 8446464 (solution/solve-second entries 2020)))))