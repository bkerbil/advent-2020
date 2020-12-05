(ns advent.day-05.solution
  (:use [criterium.core])
  (:require [clojure.set :as set]
            [advent.day-05.parser :as parser]
            [advent.day-05.binary-search :as search]))

(defn solve-first
  [boarding-passes]
  (->> boarding-passes
       (parser/input->binary)
       (map search/find-seat-id)
       (apply max)))

(defn solve-second
  [boarding-passes]
  (let [used-seats (->> boarding-passes (parser/input->binary) (map search/find-seat-id) set)
        min (apply min used-seats)
        max (apply max used-seats)
        all-seats (set (range min (inc max)))
        result (first (set/difference all-seats used-seats))]
    result))

;(def boarding-passes (slurp "input.txt"))

;(println (solve-first boarding-passes))                     ; 955
;(println (solve-second boarding-passes))                    ; 569

;(bench (solve-first boarding-passes))                       ; Execution time mean : 3 ms
;(bench (solve-second boarding-passes))                      ; Execution time mean : 3 ms