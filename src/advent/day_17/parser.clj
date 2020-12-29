(ns advent.day-17.parser
  (:require [clojure.string :as str]))

(def conversion {\. 0
                 \# 1})

(defn string->2d-space
  [text]
  [(->> text
        str/split-lines
        (map (fn [row]
               (vec (map
                      (fn [value]
                        (get conversion value))
                      (seq row)))))
        vec)])