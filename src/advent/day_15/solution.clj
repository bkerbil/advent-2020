(ns advent.day-15.solution
  (:use [criterium.core])
  (:require [advent.day-15.memory-game :as game]))

(defn solve
  [initialized target]
  (loop [current initialized]
    (if (= (:last-turn current) target)
      (:last-spoken current)
      (recur (game/speak current)))))

(def input [5 2 8 16 18 0 1])
;(def initialized (game/initialize input))

;(println (solve initialized 2020))                          ; 517
;(println (solve initialized 30000000))                      ; 1047739

;(bench (solve initialized 2020))                            ; Execution time mean : 2 ms
;(time (solve initialized 30000000))                         ; 40 seconds