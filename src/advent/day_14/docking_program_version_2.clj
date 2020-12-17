(ns advent.day-14.docking-program-version-2
  (:require [advent.day-14.protocols :as proto]
            [clojure.string :as str]))

(declare apply-bitmask expand-bitmask)

(defrecord Docking-Program-Version-2
  [memory bitmask]
  proto/Operations
  (update-bitmask [program new-bitmask]
    (assoc program :bitmask new-bitmask))
  (write-value-to-memory [program address value]
    (let [addresses (->> (apply-bitmask bitmask address)
                         expand-bitmask
                         (map #(Long/parseLong % 2)))]
      (reduce (fn [prog address] (assoc-in prog [:memory address] value)) program addresses)))
  (initialize [_]
    (reduce + (map val memory))))

(defn add-0-and-1-to-tails
  [tails]
  (if (empty? tails)
    [[\0] [\1]]
    (reduce (fn [acc v] (conj acc (conj v \0) (conj v \1))) [] tails)))

(defn add-value-to-tails
  [tails value]
  (if (empty? tails)
    [[value]]
    (map (fn [tail] (conj tail value)) tails)))

(defn apply-bitmask
  ([bitmask value]
   (apply-bitmask (reverse bitmask) (-> (Long/toString value 2) reverse) []))
  ([bitmask value result]
   (let [b (first bitmask)
         v (first value)]
     (cond
       (empty? bitmask) (->> result reverse (str/join ""))
       (= b \X) (recur (rest bitmask) (rest value) (conj result b))
       (= b \1) (recur (rest bitmask) (rest value) (conj result b))
       (nil? v) (recur (rest bitmask) (rest value) (conj result \0))
       :default (recur (rest bitmask) (rest value) (conj result v))))))

(defn expand-bitmask
  ([bitmask]
   (expand-bitmask (reverse bitmask) []))
  ([bitmask result]
   (if (empty? bitmask)
     (->> result (map reverse) (map #(str/join "" %)))
     (let [bit (first bitmask)]
       (if (= bit \X)
         (recur (rest bitmask) (add-0-and-1-to-tails result))
         (recur (rest bitmask) (add-value-to-tails result bit)))))))