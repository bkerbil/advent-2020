(ns advent.day-01.solution
  (:use [criterium.core])
  (:require [support.reader :as reader]))

(defn two-entries-matching-sum
  [entries sum]
  (for [a entries
        b entries
        :when (= sum (+ a b))]
    #{a b}))

(defn three-entries-matching-sum
  [entries sum]
  (for [a entries
        b entries
        c entries
        :when (= sum (+ a b c))]
    #{a b c}))


;; SOLVE

(defn solve-first
  "Find the two entries that sum to 2020; what do you get if you multiply them together?"
  [entries sum]
  (->> (two-entries-matching-sum entries sum)
       distinct
       first
       (apply *)))

(defn solve-second
  [entries sum]
  (->> (three-entries-matching-sum entries sum)
       distinct
       first
       (apply *)))

;(def entries (->> "input.txt" reader/read-file-as-vec (map reader/string->integer)))
;(def sum 2020)

;(println (solve-first entries sum))                         ; 1018944
;(println (solve-second entries sum))                        ; 8446464

;(bench (solve-first entries sum))                           ; Execution time mean : 148 µs
;(bench (solve-second entries sum))                          ; Execution time mean : 63 ms