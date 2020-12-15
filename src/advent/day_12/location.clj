(ns advent.day-12.location)

(defn update-coordinates
  [x-key y-key instruction x y]
  (-> instruction
      (update x-key + x)
      (update y-key + y)))

(def update-location (partial update-coordinates :x :y))
(def update-waypoint (partial update-coordinates :wx :wy))

(defn assoc-waypoint
  [instruction wx wy]
  (assoc instruction :wx wx :wy wy))