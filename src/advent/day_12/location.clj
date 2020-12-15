(ns advent.day-12.location)

(defn update-location
  [this x y]
  (-> this
      (update :x + x)
      (update :y + y)))