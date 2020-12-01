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

;(def input (->> "input.txt" reader/read-file-as-vec (map reader/string->integer)))
;(def target-value 2020)

(defn solve-first
  "Find the two entries that sum to 2020; what do you get if you multiply them together?"
  [input target]
  (->> (two-entries-matching-sum input target)
       distinct
       first
       (apply *)))

(defn solve-second
  [input target]
  (->> (three-entries-matching-sum input target)
       distinct
       first
       (apply *)))

;(println (solve-first input target-value))                  ; 1018944
;(println (solve-second input target-value))                 ; 8446464

;(bench (solve-first input 2020))                            ; Execution time mean : 148 µs
;(bench (solve-second input 2020))                           ; Execution time mean : 63 ms