(ns advent.day-08.solution
  (:use [criterium.core])
  (:require [boot-code.machine :as machine]
            [advent.day-08.parser :as parser]
            [clojure.string :as str]))

(defn solve-first
  [instructions]
  (let [state (ref instructions :validator machine/state-validator-fn)
        pointer (ref 0 :validator machine/pointer-validator-fn)
        accumulator (ref 0 :validator machine/accumulator-validator-fn)]
    (machine/run-instructions-until! state pointer accumulator)))

(defn solve-second
  [instructions changes]
  (loop [c changes
         results {}]
    (let [state (ref instructions :validator machine/state-validator-fn)
          pointer (ref 0 :validator machine/pointer-validator-fn)
          accumulator (ref 0 :validator machine/accumulator-validator-fn)]
      (let [[index change] (first c)]
        (dosync
          (alter state assoc-in [index] change))
        (let [result (machine/run-instructions-until! state pointer accumulator)
              result-final (hash-map index (assoc result :change change))]
          (if (:halted? result)
            (recur (rest c) (merge results result-final))
            result))))))

(def instructions (->> (slurp "input.txt") str/split-lines parser/string->instructions))
(def changes (parser/changes instructions))

(println (solve-first instructions))                        ; 1801
(println (solve-second instructions changes))               ; 2060

;(bench (solve-first instructions))                          ; Execution time mean : 1.3 ms
;(bench (solve-second instructions changes))                 ; Execution time mean : 165 ms