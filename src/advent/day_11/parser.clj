(ns advent.day-11.parser
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(declare data->chart)

(s/def ::string string?)
(s/def ::string-args (s/cat :data ::string))
(s/def ::status (s/and (s/nilable int?)))
(s/def ::vector-of-statuses (s/coll-of ::status :kind vector))

(defn- string->status-seq
  [text]
  (let [conversions {\. nil                                 ; floor
                     \L 0                                   ; empty
                     \# 1}]                                 ; occupied
    (map #(conversions %) text)))

(s/fdef data->chart
        :args ::string-args
        :ret ::vector-of-statuses)

(defn data->chart
  [data]
  {:pre  [(s/valid? ::string data)]
   :post [(s/valid? ::vector-of-statuses %)]}
  (->> data
       (str/split-lines)
       (map string->status-seq)
       flatten
       vec))