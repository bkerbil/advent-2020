(ns advent.day-11-version2.parser
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(declare data->state)

(s/def ::string string?)
(s/def ::string-args (s/cat :data ::string))
(s/def ::status (s/and (s/nilable int?)))
(s/def ::vector-of-statuses (s/coll-of ::status :kind vector))
(s/def ::2d-vector-of-vectors-of-statuses (s/coll-of ::vector-of-statuses :kind vector))

(defn- string->status-seq
  [text]
  (let [conversions {\. nil, \L 0, \# 1}]
    (vec (map #(conversions %) text))))

(s/fdef data->chart
        :args ::string-args
        :ret ::2d-vector-of-vectors-of-statuses)

(defn data->chart
  [data]
  {:pre  [(s/valid? ::string data)]
   :post [(s/valid? ::2d-vector-of-vectors-of-statuses %)]}
  (->> data
       (str/split-lines)
       (map string->status-seq)
       vec))