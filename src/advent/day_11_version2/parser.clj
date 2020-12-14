(ns advent.day-11-version2.parser
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(declare data->chart)

(s/def ::string string?)
(s/def ::string-args (s/cat :data ::string))
(s/def ::status (s/and (s/nilable int?)))
(s/def ::status-vector (s/coll-of ::status :kind vector))
(s/def ::2d-status-vector (s/coll-of ::status-vector :kind vector))

(defn- string->status-seq
  [text]
  (let [conversions {\. nil, \L 0, \# 1}]
    (vec (map #(conversions %) text))))

(s/fdef data->chart
        :args ::string-args
        :ret ::2d-status-vector)

(defn data->chart
  [data]
  {:pre  [(s/valid? ::string data)]
   :post [(s/valid? ::2d-status-vector %)]}
  (->> data
       (str/split-lines)
       (map string->status-seq)
       vec))