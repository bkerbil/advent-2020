(ns advent.day-07.parser
  (:require [clojure.string :as str]))

(defn string->keyword
  [text]
  (keyword (str/replace text " " "-")))

(defn string->bag
  [text]
  (let [[_ matches] (re-find #"^(.*) bags contain" text)
        joined (str/replace matches " " "-")
        keywordized (keyword joined)]
    keywordized))

(defn string->contents-as-sets
  [text]
  (let [[_ matches] (flatten (re-seq #".* bags contain (.+).$" text))
        split (re-seq #" (\D+) bag[s]?" matches)
        contents (reduce (fn [bags [_ bag]]
                           (let [kw (string->keyword bag)]
                             (conj bags kw))) #{} split)]
    contents))

(defn string->contents-as-map
  [text]
  (let [[_ matches] (flatten (re-seq #".* bags contain (.+).$" text))
        split (re-seq #"(\d+) (\D+) bag[s]?" matches)
        contents (reduce (fn [bags [_ amount bag]]
                           (let [kw (string->keyword bag)
                                 value (Integer/parseInt amount)]
                             (merge bags (hash-map kw value)))) {} split)]
    contents))

(defn string->bags-with-set
  [text]
  (let [bag (string->bag text)
        contents (string->contents-as-sets text)]
    (hash-map bag contents)))

(defn string->bag-with-map
  [text]
  (let [bag (string->bag text)
        contents (string->contents-as-map text)]
    (hash-map bag contents)))