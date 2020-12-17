(ns advent.day-14.solution
  (:use [criterium.core])
  (:require [advent.day-14.parser :as p]
            [advent.day-14.protocols :as proto]
            [advent.day-14.docking-program :refer [->Docking-Program]]
            [advent.day-14.docking-program-version-2 :refer [->Docking-Program-Version-2]]
            [advent.day-14.action :as a]))

(defn solve
  [instructions program]
  (if (empty? instructions)
    (proto/initialize program)
    (recur (rest instructions) (a/action (first instructions) program))))

;(def instructions (->> "input.txt" slurp p/string->instructions))
;(def docking-program (->Docking-Program {} nil))
;(def docking-program-version-2 (->Docking-Program-Version-2 {} nil))

;(println (solve instructions docking-program))              ; 15919415426101
;(println (solve instructions docking-program-version-2))    ; 3443997590975

;(bench (solve instructions docking-program))                ; 4.5 ms
;(bench (solve instructions docking-program-version-2))      ; 190 ms