(ns advent.day-12.location)

(defn update-location
  [instruction x y]
  (-> instruction
      (update :x + x)
      (update :y + y)))