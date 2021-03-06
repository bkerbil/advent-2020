(ns advent.day-16.solution-test
  (:require [clojure.test :refer :all]
            [advent.day-16.parser :as parser]
            [advent.day-16.solution :as solution]))

(def rules (->> "./src/advent/day_16/rules.txt" slurp parser/rules-parser))
(def tickets (->> "./src/advent/day_16/nearby-tickets.txt" slurp parser/tickets-parser))
(def your-ticket (->> "./src/advent/day_16/your-ticket.txt" slurp parser/your-ticket))

(deftest solve-test
  (testing "should return correct value for first and second puzzles"
    (is (= 20048 (solution/solve-first rules tickets)))
    (is (= 4810284647569 (solution/solve-second rules tickets your-ticket)))))

(deftest known-examples-test
  (testing "known examples for first puzzle"
    (let [rules [{:name :class, :lmin 1, :lmax 3, :hmin 5, :hmax 7}
                 {:name :row, :lmin 6, :lmax 11, :hmin 33, :hmax 44}
                 {:name :seat, :lmin 13, :lmax 40, :hmin 45, :hmax 50}]
          tickets [[7 3 47] [40 4 50] [55 2 20] [38 6 12]]]
      (is (= 71 (solution/solve-first rules tickets))))))