(ns advent.day-08.solution
  (:use [criterium.core]
        [clojure.pprint])
  (:require [boot-code.data :as data]
            [boot-code.machine :as machine]
            [advent.day-08.parser :as parser]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.spec.test.alpha :as stest]))

(defn solve-first
  [state pointer accumulator]
  (machine/run-instructions-until! state pointer accumulator))

(defn solve-second
  [state pointer accumulator changes]
  (let [original-state @state]
    (loop [c changes
           results {}]
      (if (empty? c)
        results
        (let [[index change] (first c)]
          (dosync
            (ref-set state original-state)
            (ref-set pointer 0)
            (ref-set accumulator 0))
          (dosync
            (alter state assoc-in [index] change))
          (let [result (machine/run-instructions-until! state pointer accumulator)
                result-final (hash-map index (assoc result :change change))]
            (if (:halted? result)
              (recur (rest c) (merge results result-final))
              result)))))))

;(def instructions (->> (slurp "input.txt")
;                       str/split-lines
;                       parser/string->instructions))
;(def changes (parser/changes instructions))

;(def state (ref instructions :validator machine/state-validator-fn))
;(def pointer (ref 0 :validator machine/pointer-validator-fn))
;(def accumulator (ref 0 :validator machine/accumulator-validator-fn))

;(println (stest/enumerate-namespace 'boot-code.data))
;(println (stest/enumerate-namespace 'boot-code.machine))
;(println (stest/enumerate-namespace 'boot-code.action))

;(println (solve-first state pointer accumulator))           ; 1801
;(println (solve-second state pointer accumulator changes))  ; 2060

;(bench (solve-first state pointer accumulator))             ; Execution time mean : 850 µs
;(bench (solve-second state pointer accumulator changes))    ;