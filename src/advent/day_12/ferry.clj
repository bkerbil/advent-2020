(ns advent.day-12.ferry
  (:require [advent.day-12.protocols :as proto]
            [advent.day-12.direction :as d]
            [advent.day-12.location :as l]))

(defrecord Ferry [x y direction]
  proto/Move
  (north [this value]
    "Move north by the given value."
    (let [[x y] (d/direction->numeric-changes :north value)]
      (l/update-location this x y)))
  (east [this value]
    "Move east by the given value."
    (let [[x y] (d/direction->numeric-changes :east value)]
      (l/update-location this x y)))
  (south [this value]
    "Move south by the given value."
    (let [[x y] (d/direction->numeric-changes :south value)]
      (l/update-location this x y)))
  (west [this value]
    "Move west by the given value."
    (let [[x y] (d/direction->numeric-changes :west value)]
      (l/update-location this x y)))
  (forward [this value]
    "Move forward by given value in the direction the ship is currently facing."
    (let [[x y] (d/direction->numeric-changes direction value)]
      (l/update-location this x y)))
  (right [this degrees]
    "Turn right the given number of degrees."
    (let [new-direction (d/turn-right direction degrees)]
      (d/update-direction this new-direction)))
  (left [this degrees]
    "Turn left the given number of degrees."
    (let [new-direction (d/turn-left direction degrees)]
      (d/update-direction this new-direction))))