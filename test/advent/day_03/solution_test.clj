(ns advent.day-03.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-03.solution :as solution]
            [advent.day-03.transform :as transform]
            [clojure.string :as str]))

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

(def example-chart (transform/transform example-input))

(deftest solve-multiple-test
  (testing "given example input, should return example result"
    (is (= 7 (solution/solve example-chart [[3 1]])))
    (is (= 336 (solution/solve example-chart [[1 1] [3 1] [5 1] [7 1] [1 2]])))))


(def chart (->> (slurp "./src/advent/day_03/input.txt")
                (str/split-lines)
                (transform/transform)))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 156 (solution/solve chart [[3 1]])))
    (is (= 3521829480 (solution/solve chart [[1 1] [3 1] [5 1] [7 1] [1 2]])))))