(ns advent.day-12.parser
  (:require [clojure.string :as str]
            [clojure.spec.alpha :as s]))

(declare data->instructions)

(s/def ::string string?)
(s/def ::string-args (s/cat :data ::string))
(s/def ::action #{:N :E :S :W :F :R :L})
(s/def ::value int?)
(s/def ::action-and-value (s/keys :req-un [::action ::value]))
(s/def ::actions (s/coll-of ::action-and-value))

(s/fdef data->instructions
        :args ::string-args
        :ret ::actions)

(defn data->instructions
  [data]
  {:pre  [(s/valid? ::string data)]
   :post [(s/valid? ::actions %)]}
  (->> data
       str/split-lines
       (map #(re-find #"^(\w{1})(\d+)$" %))
       (map rest)
       (map (fn [pair]
              (let [action (keyword (first pair))
                    value (Integer/parseInt (second pair))]
                {:action action
                 :value  value})))))