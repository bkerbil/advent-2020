(ns advent.day-12.solution
  (:use [criterium.core])
  (:require [advent.day-12.parser :as parser]
            [advent.day-12.instruction :refer [->Instruction]]
            [advent.day-12.support :as support]))

(defn solve-first
  [instructions]
  (let [instruction (support/reduce-instructions instructions (->Instruction 0 0 :east))
        manhattan-distance (support/manhattan-distance instruction)]
    manhattan-distance))

;(def instructions (->> "input.txt" slurp parser/data->instructions))

;(println (solve-first instructions))                        ; 508

;(bench (solve-first instructions))                          ; Execution time mean : 160 µs