(ns advent.day-08.parser
  (:require [boot-code.data :as data])
  (:import (boot_code.data Instruction)))

(defn string->instructions
  [s]
  (vec (map data/string->instruction s)))

(defn instructions->map
  [instructions instruction]
  (-> (assoc instructions (:index instructions) instruction)
      (update :index inc)))

(defn dissoc-index-value
  [instructions]
  (dissoc instructions :index))

(defn op-acc?
  [[_ instruction]]
  (if (= (:operation instruction) :acc)
    true
    false))

(defn switch-ops
  [[index instruction]]
  (let [{:keys [operation argument]} instruction]
    (if (= operation :jmp)
      {index (Instruction. :nop argument)}
      {index (Instruction. :jmp argument)})))

(defn changes
  [instructions]
  (->> instructions
       (reduce instructions->map {:index 0})
       (dissoc-index-value)
       (remove op-acc?)
       (map switch-ops)
       (into (sorted-map))))