(ns advent.day-03.transform)

(def char->keyword {\. :open
                    \# :tree})

(defn row->values
  [row]
  (->> row (map char->keyword) vec))

(defn transform
  [data]
  (->> data
       (map row->values)
       vec))