(ns advent.day-11-version2.solution
  (:use [criterium.core])
  (:require [advent.day-11-version2.parser :as parser]
            [advent.day-11-version2.chart :refer :all]))

(defn solve
  [neighbours-fn rule-fn state]
  (->> (stabilize-fn state neighbours-fn rule-fn)
       flatten
       frequencies
       (#(get % 1))))

(def solve-first (partial solve neighbours-adjacent-fn rule-pre-corona))
(def solve-second (partial solve neighbours-line-of-sight-fn rule-post-corona))

;(def state (->> "input.txt" slurp parser/data->chart))

;(println (solve-first state))                               ; 2338
;(println (solve-second state))                              ; 2134

;(bench (solve-first state))                                 ; Execution time mean : 2.7 sec
;(bench (solve-second state))                                ; Execution time mean : 3.3 sec