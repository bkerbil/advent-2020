(ns advent.day-08.parser
  (:require [boot-code.data :as data])
  (:import (boot_code.data Instruction)))

(defn string->instructions
  [s]
  (vec (map data/string->instruction s)))

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

(defn changes
  [instructions]
  (->> instructions
       (reduce instructions->map {:index-value 0})
       (dissoc-index-value)
       (remove op-acc?)
       (map switch-op-jmp-and-nop)
       (into (sorted-map))))