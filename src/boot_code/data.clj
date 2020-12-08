(ns boot-code.data
  (:require [clojure.spec.alpha :as s])
  (:gen-class))

(declare string->instruction)

(def string->operator {"+" 1
                       "-" -1})

(defrecord Instruction [operation argument])

(s/def ::operation #{:acc :jmp :nop})
(s/def ::argument int?)
(s/def ::instruction (s/keys :req-un [::operation ::argument]))

(s/fdef string->instruction
        :args (s/cat :text string?)
        :ret ::instruction)

(defn string->instruction
  [text]
  {:post [(s/valid? ::instruction %)]}
  (let [[_ op polarity arg] (re-find #"^(\w+) (\+|\-){1}(\d+)$" text)
        operation (keyword op)
        argument (* (string->operator polarity) (Integer/parseInt arg))]
    (Instruction. operation argument)))