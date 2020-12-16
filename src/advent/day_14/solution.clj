(ns advent.day-14.solution
  (:use [criterium.core])
  (:require [advent.day-14.parser :as p]
            [advent.day-14.protocols :as proto]
            [advent.day-14.docking-program :refer [->Docking-Program] :as d]))

(defn solve-first
  [instructions]
  (loop [i instructions
         program (->Docking-Program {} nil)]
    (if (empty? i)
      (proto/initialize program)
      (let [a (first i)
            result (d/action a program)]
        (recur (rest i) result)))))

;(def instructions (->> "input.txt" slurp p/string->instructions))

;(println (solve-first instructions))                        ; 15919415426101

;(bench (solve-first instructions))                          ; 4.5 ms