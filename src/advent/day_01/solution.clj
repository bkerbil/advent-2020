(ns advent.day-01.solution
  (:use [criterium.core])
  (:require [clojure.string :as str]))

; Perhaps these could be combined to macro?
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

(defn distinct-and-apply-multiplication
  [values]
  (->> values
       distinct
       first
       (apply *)))

(defn solve-first
  [entries sum]
  (->> (two-entries-matching-sum entries sum)
       distinct-and-apply-multiplication))

(defn solve-second
  [entries sum]
  (->> (three-entries-matching-sum entries sum)
       distinct-and-apply-multiplication))

;(def entries (->> (slurp "input.txt")
;                  str/split-lines
;                  (map #(Integer/parseInt %))))
;(def sum 2020)

;(println (solve-first entries sum))                         ; 1018944
;(println (solve-second entries sum))                        ; 8446464

;(bench (solve-first entries sum))                           ; Execution time mean : 220 µs
;(bench (solve-second entries sum))                          ; Execution time mean : 100 ms