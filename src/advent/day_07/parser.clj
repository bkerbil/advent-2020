(ns advent.day-07.parser
  (:require [clojure.string :as str]))

(defn string->keyword
  [text]
  (keyword (str/replace text " " "-")))

(defn finds->value
  [finds]
  (try
    (->> (drop 1 finds)
         (map #(str/split % #", "))
         flatten
         (map #(str/replace % #" bags?" ""))
         (map #(re-find #"^(\d+) (.*)$" %))
         (map rest)
         (map (fn [[amount bag]] [(string->keyword bag) (Integer/parseInt amount)]))
         (reduce concat)
         vec)
    (catch Exception _
      [])))

(defn parse
  [text]
  (let [finds (rest (re-find #"^(.*) bags contain (.*).$" text))
        k (-> (first finds) (string->keyword))
        v (finds->value finds)]
    (hash-map k v)))