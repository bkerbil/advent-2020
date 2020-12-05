(ns advent.day-05.solution
  (:use [criterium.core])
  (:require [advent.day-05.parser :as parser]
            [advent.day-05.binary-search :as search]))

(defn solve-first
  [boarding-passes]
  (->> boarding-passes
       (parser/input->steps)
       (map search/find-seat-id)
       (sort >)
       first))

(defn solve-second
  [boarding-passes]
  (let [all-seats (set (range 71 955))
        used-seats (->> boarding-passes
                        (parser/input->steps)
                        (map search/find-seat-id)
                        sort
                        set)
        result (first (clojure.set/difference all-seats used-seats))]
    result))

(def boarding-passes (slurp "input.txt"))

(println (solve-first boarding-passes))                     ; 955
(println (solve-second boarding-passes))                    ; 569

;(bench (solve-first boarding-passes))                       ; Execution time mean : 4 ms
;(bench (solve-second boarding-passes))                      ; Execution time mean : 4 ms