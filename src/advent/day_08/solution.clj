(ns advent.day-08.solution
  (:use [criterium.core])
  (:require [boot-code.data :as data]
            [boot-code.machine :as machine]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.spec.test.alpha :as stest]))

(defn solve-first
  [state pointer accumulator]
  (machine/run-instructions-until! state pointer accumulator))

;(def instructions (->> (slurp "input.txt")
;                       str/split-lines
;                       (map data/string->instruction)
;                       vec))

;(def state (ref instructions :validator machine/state-validator-fn))
;(def pointer (ref 0 :validator machine/pointer-validator-fn))
;(def accumulator (ref 0 :validator machine/accumulator-validator-fn))

; uncomment for debugging
(comment (add-watch state :state-debug (fn [k _ref old-state new-state]
                                         (let [oldval (set old-state)
                                               newval (set new-state)
                                               old (first (set/difference oldval newval))
                                               new (first (set/difference newval oldval))]
                                           (println k "         " old "->" new))))

         (add-watch pointer :pointer-debug (fn [k _ref old-state new-state]
                                             (println k "       " old-state "->" new-state)))

         (add-watch accumulator :accumulator-debug (fn [k _ref old-state new-state]
                                                     (println k "   " old-state "->" new-state))))

;(println (stest/enumerate-namespace 'boot-code.data))
;(println (stest/enumerate-namespace 'boot-code.machine))
;(println (stest/enumerate-namespace 'boot-code.action))

;(println (solve-first state pointer accumulator))           ; => 1801
;(bench (solve-first state pointer accumulator))             ; Execution time mean : 850 µs