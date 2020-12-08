(ns boot-code.action)

(defn get-value!
  [kw state pointer]
  (get (get-in @state [@pointer]) kw))

(def get-operation! (partial get-value! :operation))
(def get-argument! (partial get-value! :argument))

(defmulti action!
          (fn [state pointer _accumulator]
            (get-operation! state pointer)))

(defmethod action! :nop
  [_state pointer _accumulator]
  (dosync
    (alter pointer inc)))

(defmethod action! :acc
  [state pointer accumulator]
  (dosync
    (alter accumulator + (get-argument! state pointer))
    (alter pointer inc)))

(defmethod action! :jmp
  [state pointer _accumulator]
  (dosync
    (alter pointer + (get-argument! state pointer))))