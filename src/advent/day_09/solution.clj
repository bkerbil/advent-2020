(ns advent.day-09.solution
  (:use [criterium.core])
  (:require [advent.day-09.parser :as parser]
            [advent.day-09.first_solution :as first-solution]
            [advent.day-09.second_solution :as second-solution]))

(defn solve-first
  [amount numbers]
  (first-solution/first-non-sum amount numbers))

(defn solve-second
  [target numbers]
  (second-solution/first-cumulative-sum-matches target numbers))

;(def numbers (parser/data->numbers (slurp "input.txt")))

;(println (solve-first 25 numbers))                          ; 556543474
;(println (solve-second 556543474 numbers))                  ; 76096372

;(bench (solve-first 25 numbers))                            ; 3 ms
;(bench (solve-second 556543474 numbers))                    ; 280 ms