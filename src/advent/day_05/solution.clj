(ns advent.day-05.solution
  (:use [criterium.core])
  (:require [advent.day-05.parser :as parser]
            [advent.day-05.binary-search :as search]))

(defn solve-first
  [rows columns boarding-passes]
  (->> boarding-passes
       (parser/input->steps)
       (map (partial search/binary-search-for-seat rows columns))
       (sort >)
       first))

(defn solve-second
  [rows columns boarding-passes]
  (let [all-seats (set (range 71 955))
        used-seats (->> boarding-passes
                        (parser/input->steps)
                        (map (partial search/binary-search-for-seat rows columns))
                        sort
                        set)
        result (first (clojure.set/difference all-seats used-seats))]
    result))

;(def rows 128)
;(def columns 8)
;(def boarding-passes (slurp "input.txt"))

;(println (solve-first rows columns boarding-passes))        ; 955
;(println (solve-second rows columns boarding-passes))       ; 569

;(bench (solve-first rows columns boarding-passes))          ; Execution time mean : 14 ms
;(bench (solve-second rows columns boarding-passes))         ; Execution time mean : 14 ms