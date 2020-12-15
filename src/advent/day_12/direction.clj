(ns advent.day-12.direction)

(def direction->number {:north 0, :east 1, :south 2, :west 3})
(def number->direction {0 :north, 1 :east, 2 :south, 3 :west})

(defn direction->numeric-changes
  [direction value]
  (case direction
    :north [0 value]
    :east [value 0]
    :south [0 (- value)]
    :west [(- value) 0]))

(defn turn
  [f direction degrees]
  (let [direction-value (get direction->number direction)
        change-value (quot degrees 90)
        new-direction (get number->direction (mod (f direction-value change-value) 4))]
    new-direction))

(def turn-right (partial turn +))
(def turn-left (partial turn -))

(defn update-direction
  [this direction]
  (assoc this :direction direction))