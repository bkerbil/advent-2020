(ns advent.day-05.parser
  (:require [clojure.string :as str]))

(defn string->steps
  [text]
  (let [conversion {\B true, \R true,
                    \F false, \L false}
        letters (seq text)
        rows (take 7 letters)
        columns (take-last 3 letters)]
    {:row    (vec (map conversion rows))
     :column (vec (map conversion columns))}))

(defn input->steps
  [input]
  (->> input
       str/split-lines
       (map string->steps)))