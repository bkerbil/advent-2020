(ns boot-code.machine
  (:require [boot-code.data :as data]
            [boot-code.action :as action]
            [clojure.spec.alpha :as s]))

(declare run-instruction! run-instructions-until!)

(s/def ::state (s/coll-of ::data/instruction))
(s/def ::pointer (s/and int? #(>= % 0)))
(s/def ::accumulator int?)

(s/fdef run-instruction!
        :args (s/cat :state ::state :pointer ::pointer :accumulator ::accumulator))

(defn run-instruction!
  ([state pointer accumulator]
   (action/action! state pointer accumulator)))

(defn run-instructions-until!
  ([state pointer accumulator]
   (run-instructions-until! state pointer accumulator #{}))
  ([state pointer accumulator pointers-visited]
   (let [pointer-value (run-instruction! state pointer accumulator)
         halt? (contains? pointers-visited pointer-value)]
     (if (>= @pointer (count @state))
       {:accumulator @accumulator
        :halted?     false}
       (if halt?
         {:accumulator @accumulator
          :halted?     true}
         (recur state pointer accumulator (conj pointers-visited pointer-value)))))))

(defn pointer-validator-fn
  [value]
  (s/valid? ::pointer value))

(defn state-validator-fn
  [value]
  (s/valid? ::state value))

(defn accumulator-validator-fn
  [value]
  (s/valid? ::accumulator value))