(ns advent.day-14.parser
  (:require [clojure.string :as str]))

(defn str->mask-or-mem
  [text]
  (let [mask (rest (re-find #"^mask = (\S+)$" text))
        mem (rest (re-find #"^mem\[(\d+)\] = (\d+)$" text))]
    (if (empty? mem)
      {:action :mask
       :value  (first mask)}
      {:action  :mem
       :address (Long/parseLong (first mem))
       :value   (Long/parseLong (second mem))})))

(defn string->instructions
  [data]
  (->> data
       (str/split-lines)
       (map str->mask-or-mem)))