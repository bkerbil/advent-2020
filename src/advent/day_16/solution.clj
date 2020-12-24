(ns advent.day-16.solution
  (:use [criterium.core])
  (:require [advent.day-16.ticket :as ticket]
            [advent.day-16.parser :as parser]))

(defn solve-first
  [rules tickets]
  (->> (ticket/transform-tickets rules tickets)
       (ticket/error-rate)))

(defn solve-second
  [rules tickets your-ticket]
  (let [valid-tickets (->> (ticket/transform-tickets rules tickets) (ticket/filter-valid))
        p (ticket/possibilities rules valid-tickets)
        whittled (ticket/whittler p)
        indexes (->> (into (sorted-map) whittled) (take 6) (map second))
        result (reduce (fn [acc k] (* acc (nth your-ticket k))) 1 indexes)]
    result))

;(def rules (->> "rules.txt" slurp parser/rules-parser))
;(def nearby-tickets (->> "nearby-tickets.txt" slurp parser/tickets-parser))
;(def your-ticket (->> "your-ticket.txt" slurp parser/your-ticket))

;(println (solve-first rules nearby-tickets))                ; 20048
;(println (solve-second rules nearby-tickets your-ticket))   ; 4810284647569

;(bench (solve-first rules nearby-tickets))                  ; Execution time mean : 20 ms
;(bench (solve-second rules nearby-tickets your-ticket))     ; Execution time mean : 38 ms