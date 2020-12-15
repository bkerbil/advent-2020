(ns advent.day-12.instruction-proper
  (:require [advent.day-12.protocols :as proto]
            [advent.day-12.location :as l]))

(declare rotate-clockwise-degrees rotate-counterclockwise-degrees)

(defrecord Instruction-Proper [x y wx wy direction]
  proto/Move
  (north [this value]
    "Move the waypoint north by the given value."
    (l/update-waypoint this 0 value))
  (east [this value]
    "Move the waypoint east by the given value."
    (l/update-waypoint this value 0))
  (south [this value]
    "Move the waypoint south by the given value."
    (l/update-waypoint this 0 (- value)))
  (west [this value]
    "Move the waypoint west by the given value."
    (l/update-waypoint this (- value) 0))
  (forward [this value]
    "Move forward to the waypoint a number of times equal to the given value."
    (let [dx (* value wx)
          dy (* value wy)]
      (l/update-location this dx dy)))
  (right [this degrees]
    "Rotate the waypoint around the ship clockwise given degrees."
    (let [[wx-rotated wy-rotated] (rotate-clockwise-degrees degrees [wx wy])]
      (l/assoc-waypoint this wx-rotated wy-rotated)))
  (left [this degrees]
    "Rotate the waypoint around the ship counter-clockwise given degrees."
    (let [[wx-rotated wy-rotated] (rotate-counterclockwise-degrees degrees [wx wy])]
      (l/assoc-waypoint this wx-rotated wy-rotated))))

(defn rotate-clockwise
  [x y]
  [y (- x)])

(defn rotate-counterclockwise
  [x y]
  [(- y) x])

(defn rotate-times
  [f degrees coordinates]
  (if (<= degrees 0)
    coordinates
    (recur f (- degrees 90) (apply f coordinates))))

(def rotate-clockwise-degrees (partial rotate-times rotate-clockwise))
(def rotate-counterclockwise-degrees (partial rotate-times rotate-counterclockwise))