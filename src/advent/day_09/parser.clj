(ns advent.day-09.parser
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(declare data->numbers)

(s/def ::string string?)
(s/def ::string-args (s/cat :data ::string))
(s/def ::numbers (s/coll-of int?))

(s/fdef data->numbers
        :args ::string-args
        :ret ::numbers)

(defn data->numbers
  "Converts rows of numbers as a string to a sequence of integers."
  [data]
  {:pre  [(s/valid? ::string data)]
   :post [(s/valid? ::numbers %)]}
  (->> data
       str/split-lines
       (map #(Long/parseLong %))))