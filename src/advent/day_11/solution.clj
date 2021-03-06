(ns advent.day-11.solution
  (:use [criterium.core])
  (:require [advent.day-11.parser :as parser]
            [advent.day-11.vision :as vision]
            [advent.day-11.rule :as rule]
            [advent.day-11.chart :as chart]))

(defn solve
  [neighbours-fn rule-fn chart]
  (->> ((chart/stabilize-fn chart neighbours-fn rule-fn) chart)
       flatten
       frequencies
       (#(get % 1))))

(def solve-first (partial solve vision/neighbours-adjacent-fn rule/pre-corona))
(def solve-second (partial solve vision/neighbours-line-of-sight-fn rule/post-corona))

;(def chart (->> "input.txt" slurp parser/data->chart))

;(println (solve-first chart))                               ; 2338
;(println (solve-second chart))                              ; 2134

;(bench (solve-first chart))                                 ; Execution time mean : 2.7 sec
;(bench (solve-second chart))                                ; Execution time mean : 3.3 sec