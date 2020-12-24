(ns advent.day-16.solution
  (:use [criterium.core])
  (:require [advent.day-16.ticket :as ticket]
            [advent.day-16.parser :as parser]))

(defn solve-first
  [rules tickets]
  (->> (ticket/transform-tickets rules tickets)
       (ticket/error-rate)))

(def rules (->> "rules.txt" slurp parser/rules-parser))
(def nearby-tickets (->> "nearby-tickets.txt" slurp parser/tickets-parser))

(println (solve-first rules nearby-tickets))                ; 20048
;(bench (solve-first rules nearby-tickets))                  ; Execution time mean : 20 ms