(ns advent.day-14.bitmask
  (:require [clojure.string :as str]))

(defn apply-bitmask
  [bitmask value]
  (let [value-as-bits (Long/toString value 2)]
    (loop [bitmasks (reverse bitmask)
           values (reverse value-as-bits)
           result []]
      (let [b (first bitmasks)
            v (first values)]
        (cond
          (empty? bitmasks) (Long/parseLong (->> result reverse (str/join "")) 2)
          (or (= b \0) (= b \1)) (recur (rest bitmasks) (rest values) (conj result b))
          (and (= b \X) (not (nil? v))) (recur (rest bitmasks) (rest values) (conj result v))
          (= v nil) (recur (rest bitmasks) (rest values) (conj result \0)))))))