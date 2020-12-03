(ns advent.day-03.transform)

(def char->integer {\. 0
                    \# 1})

(defn row->integers
  [row]
  (->> row (map char->integer) vec))

(defn transform
  [data]
  (->> data
       (map row->integers)
       vec))