(ns advent.day-12.solution
  (:use [criterium.core])
  (:require [advent.day-12.parser :as parser]
            [advent.day-12.instruction :refer [->Instruction]]
            [advent.day-12.instruction-proper :refer [->Instruction-Proper]]
            [advent.day-12.support :as support]))

(defn solve
  [instruction instructions]
  (->> (support/reduce-instructions instructions instruction)
       support/manhattan-distance))

(def solve-first (partial solve (->Instruction 0 0 :east)))
(def solve-second (partial solve (->Instruction-Proper 0 0 10 1 :east)))

;(def instructions (->> "input.txt" slurp parser/data->instructions))

;(println (solve-first instructions))                        ; 508
;(println (solve-second instructions))                       ; 30761

;(bench (solve-first instructions))                          ; Execution time mean : 150 µs
;(bench (solve-second instructions))                         ; Execution time mean : 220 µs