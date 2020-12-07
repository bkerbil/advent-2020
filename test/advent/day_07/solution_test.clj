(ns advent.day-07.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-07.solution :as solution]
            [clojure.string :as str]
            [advent.day-07.parser :as parser]))

(def bags (->> (slurp "./src/advent/day_07/input.txt")
               str/split-lines
               (map parser/parse)
               (reduce merge)))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 101 (solution/solve-first :shiny-gold bags)))
    (is (= 108636 (solution/solve-second :shiny-gold bags)))))