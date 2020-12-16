(ns advent.day-14.solution
  (:use [criterium.core])
  (:require [advent.day-14.parser :as p]
            [advent.day-14.protocols :as proto]
            [advent.day-14.docking-program :refer [->Docking-Program] :as d]
            [advent.day-14.action :as a]))

(defn solve-first
  [instructions program]
  (if (empty? instructions)
    (proto/initialize program)
    (recur (rest instructions) (a/action (first instructions) program))))

;(def instructions (->> "input.txt" slurp p/string->instructions))
;(def docking-program (->Docking-Program {} nil))

;(println (solve-first instructions docking-program))        ; 15919415426101

;(bench (solve-first instructions docking-program))          ; 4.5 ms