(ns advent.day-12.instruction
  (:require [advent.day-12.protocols :as proto]
            [advent.day-12.location :as l]))

(declare direction->numeric-changes update-direction turn-right turn-left)

(defrecord Instruction [x y direction]
  proto/Move
  (north [this value]
    "Move north by the given value."
    (let [[x y] (direction->numeric-changes :north value)]
      (l/update-location this x y)))
  (east [this value]
    "Move east by the given value."
    (let [[x y] (direction->numeric-changes :east value)]
      (l/update-location this x y)))
  (south [this value]
    "Move south by the given value."
    (let [[x y] (direction->numeric-changes :south value)]
      (l/update-location this x y)))
  (west [this value]
    "Move west by the given value."
    (let [[x y] (direction->numeric-changes :west value)]
      (l/update-location this x y)))
  (forward [this value]
    "Move forward by given value in the direction the ship is currently facing."
    (let [[x y] (direction->numeric-changes direction value)]
      (l/update-location this x y)))
  (right [this degrees]
    "Turn right the given number of degrees."
    (let [new-direction (turn-right direction degrees)]
      (update-direction this new-direction)))
  (left [this degrees]
    "Turn left the given number of degrees."
    (let [new-direction (turn-left direction degrees)]
      (update-direction this new-direction))))

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
        direction-change (quot degrees 90)
        new-direction (get number->direction (mod (f direction-value direction-change) 4))]
    new-direction))

(def turn-right (partial turn +))
(def turn-left (partial turn -))

(defn update-direction
  [this direction]
  (assoc this :direction direction))