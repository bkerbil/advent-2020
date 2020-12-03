(ns advent.day-03.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-03.solution :refer :all]
            [advent.day-03.transform :as t]))

(def example-input ["..##......."
                    "#...#...#.."
                    ".#....#..#."
                    "..#.#...#.#"
                    ".#...##..#."
                    "..#.##....."
                    ".#.#.#....#"
                    ".#........#"
                    "#.##...#..."
                    "#...##....#"
                    ".#..#...#.#"])

(def example-chart (t/transform example-input))

(deftest solve-multiple-test
  (testing "given example input, should return example result"
    (is (= 7 (solve-multiple [0 0] example-chart [[3 1]])))
    (is (= 336 (solve-multiple [0 0] example-chart [[1 1] [3 1] [5 1] [7 1] [1 2]])))))