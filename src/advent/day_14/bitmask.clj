(ns advent.day-14.bitmask
  (:require [clojure.string :as str]))

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