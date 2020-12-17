(ns advent.day-14.docking-program
  (:require [advent.day-14.protocols :as proto]
            [clojure.string :as str]))

(declare apply-bitmask)

(defrecord Docking-Program
  [memory bitmask]
  proto/Operations
  (update-bitmask [program new-bitmask]
    (assoc program :bitmask new-bitmask))
  (write-value-to-memory [program address value]
    (let [result (apply-bitmask bitmask value)]
      (assoc-in program [:memory address] result)))
  (initialize [_]
    (reduce + (map val memory))))

(defn apply-bitmask
  ([bitmask value]
   (apply-bitmask (reverse bitmask) (-> (Long/toString value 2) reverse) []))
  ([bitmask value result]
   (let [b (first bitmask)
         v (first value)]
     (cond
       (empty? bitmask) (Long/parseLong (->> result reverse (str/join "")) 2)
       (or (= b \0) (= b \1)) (recur (rest bitmask) (rest value) (conj result b))
       (nil? v) (recur (rest bitmask) (rest value) (conj result \0))
       :default (recur (rest bitmask) (rest value) (conj result v))))))