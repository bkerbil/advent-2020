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

(deftest solve-first-test
  (testing "given example input, should return example result"
    (is (= 7 (solve-first [0 0] [3 1] example-chart)))))

(deftest solve-second-test
  (testing "given example inputs, should return example result"
    (is (= 336 (solve-second [0 0] [[1 1] [3 1] [5 1] [7 1] [1 2]] example-chart)))))

