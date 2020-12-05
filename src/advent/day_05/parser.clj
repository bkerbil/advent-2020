(ns advent.day-05.parser
  (:require [clojure.string :as str]))

(defn string->binary
  [text]
  (-> text
      (str/replace #"[BR]" "1")
      (str/replace #"[FL]" "0")))

(defn input->binary
  [input]
  (->> input
       str/split-lines
       (map string->binary)))