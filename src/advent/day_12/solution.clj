(ns advent.day-12.solution
  (:use [criterium.core])
  (:require [advent.day-12.parser :as parser]
            [advent.day-12.ferry :refer [->Ferry]]
            [advent.day-12.support :as support]))

(defn solve-first
  [instructions]
  (loop [i instructions
         ferry (->Ferry 0 0 :east)]
    (if (empty? i)
      (support/manhattan-distance ferry)
      (let [instruction (first i)
            action (get support/action-parser (:action instruction))
            value (:value instruction)
            ferry-moved (action ferry value)]
        (recur (rest i) ferry-moved)))))

;(def instructions (->> "input.txt" slurp parser/data->instructions))

;(println (solve-first instructions))                        ; 508

;(bench (solve-first instructions))                          ; Execution time mean : 185 µs