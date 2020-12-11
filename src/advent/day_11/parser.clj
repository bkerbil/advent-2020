(ns advent.day-11.parser
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(declare data->chart)

(s/def ::string string?)
(s/def ::string-args (s/cat :data ::string))
(s/def ::status #{:floor :empty :occupied})

(s/def ::vector-of-statuses (s/coll-of ::status :kind vector))
(s/def ::2d-vectors-of-status (s/coll-of ::vector-of-statuses :kind vector))

(defn- string->status-seq
  [text]
  (let [conversions {\. :floor
                     \L :empty
                     \# :occupied}]
    (vec (map #(conversions %) text))))

(s/fdef data->chart
        :args ::string-args
        :ret ::2d-vectors-of-status)

(defn data->chart
  [data]
  {:pre  [(s/valid? ::string data)]
   :post [(s/valid? ::2d-vectors-of-status %)]}
  (->> data
       (str/split-lines)
       (map string->status-seq)
       vec))