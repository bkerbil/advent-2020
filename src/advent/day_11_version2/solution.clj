(ns advent.day-11-version2.solution
  (:use [criterium.core])
  (:require [advent.day-11-version2.parser :as parser]
            [advent.day-11-version2.protocols :as proto]
            [advent.day-11-version2.pre-corona :as pre-corona]))

(defn solve-first
  [state]
  (->> state
       pre-corona/->Chart
       proto/final-state
       :state
       flatten
       frequencies
       (#(get % 1))))

;(def state (->> "input.txt" slurp parser/data->state))
;(println (solve-first state))                               ; 2338

;(bench (solve-first state))                                 ; Execution time mean : 3 sec