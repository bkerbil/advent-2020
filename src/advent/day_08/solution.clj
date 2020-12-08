(ns advent.day-08.solution
  (:use [criterium.core]
        [clojure.pprint])
  (:require [boot-code.data :as data]
            [boot-code.machine :as machine]
            [clojure.string :as str]
            [clojure.set :as set]
            [clojure.spec.test.alpha :as stest])
  (:import (boot_code.data Instruction)))

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

(defn instructions->map
  [instructions instruction]
  (-> (assoc instructions (:index-value instructions) instruction)
      (update :index-value inc)))

(defn dissoc-index-value
  [instructions]
  (dissoc instructions :index-value))

(defn op-acc?
  [[_ instruction]]
  (if (= (:operation instruction) :acc)
    true
    false))

(defn switch-op-jmp-and-nop
  [[index instruction]]
  (let [{:keys [operation argument]} instruction]
    (cond
      (= operation :jmp) {index (Instruction. :nop argument)}
      (= operation :nop) {index (Instruction. :jmp argument)})))

;(def instructions (->> (slurp "input.txt")
;                       str/split-lines
;                       (map data/string->instruction)
;                       vec))

;(def changes (->> instructions
;                  (reduce instructions->map {:index-value 0})
;                  (dissoc-index-value)
;                  (remove op-acc?)
;                  (map switch-op-jmp-and-nop)
;                  (into (sorted-map))))

;(def state (ref instructions :validator machine/state-validator-fn))
;(def pointer (ref 0 :validator machine/pointer-validator-fn))
;(def accumulator (ref 0 :validator machine/accumulator-validator-fn))

; uncomment for debugging
(comment
  (add-watch state :state-debug (fn [k _ref old-state new-state]
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

;(println (solve-first state pointer accumulator))           ; 1801
;(println (solve-second state pointer accumulator changes))  ; 2060

;(bench (solve-first state pointer accumulator))             ; Execution time mean : 850 µs
;(bench (solve-second state pointer accumulator changes))    ;