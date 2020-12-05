(ns advent.day-05.parser
  (:require [clojure.string :as str]))

(defn string->steps
  [text]
  (let [conversion {\F :left \B :right \L :left \R :right}
        letters (seq text)
        rows (take 7 letters)
        columns (take-last 3 letters)]
    {:row    (map conversion rows)
     :column (map conversion columns)}))

(defn input->steps
  [input]
  (->> input
       str/split-lines
       (map string->steps)))