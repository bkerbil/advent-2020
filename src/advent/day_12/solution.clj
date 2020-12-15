(ns advent.day-12.solution
  (:use [criterium.core])
  (:require [advent.day-12.parser :as parser]))

(defprotocol IMove
  "Interface for moving ferries."
  (north [this value] "Move north by the given value.")
  (east [this value] "Move east by the given value.")
  (south [this value] "Move south by the given value.")
  (west [this value] "Move west by the given value.")
  (forward [this value] "Move forward by given value in the direction the ship is currently facing.")
  (right [this degrees] "Turn right the given number of degrees.")
  (left [this degrees] "Turn left the given number of degrees."))

(defn direction->value
  [direction value]
  (case direction
    :north [0 value]
    :east [value 0]
    :south [0 (* -1 value)]
    :west [(* -1 value) 0]))

(def facing-direction {:north 0
                       :east  1
                       :south 2
                       :west  3})

(def directions {0 :north
                 1 :east
                 2 :south
                 3 :west})

(defn update-location!
  [this x y]
  (-> this
      (update-in [:x] + x)
      (update-in [:y] + y)))

(defn turn-right
  [direction degrees]
  (let [old-direction (get facing-direction direction)
        value (quot degrees 90)
        new-direction (get directions (mod (+ old-direction value) 4))]
    new-direction))

(defn turn-left
  [direction degrees]
  (let [old-direction (get facing-direction direction)
        value (quot degrees 90)
        new-direction (get directions (mod (- old-direction value) 4))]
    new-direction))

(defn update-direction!
  [this direction]
  (assoc-in this [:direction] direction))

(defrecord Ferry [x y direction]
  IMove
  (north [this value]
    (let [[x y] (direction->value :north value)]
      (update-location! this x y)))
  (east [this value]
    (let [[x y] (direction->value :east value)]
      (update-location! this x y)))
  (south [this value]
    (let [[x y] (direction->value :south value)]
      (update-location! this x y)))
  (west [this value]
    (let [[x y] (direction->value :west value)]
      (update-location! this x y)))
  (forward [this value]
    (let [[x y] (direction->value direction value)]
      (update-location! this x y)))
  (right [this degrees]
    (let [new-direction (turn-right direction degrees)]
      (update-direction! this new-direction)))
  (left [this degrees]
    (let [new-direction (turn-left direction degrees)]
      (update-direction! this new-direction))))

(defn manhattan-distance
  ([ferry]
   (let [x (Math/abs ^int (:x ferry))
         y (Math/abs ^int (:y ferry))]
     (+ x y))))

(def action-parser {:N north
                    :E east
                    :S south
                    :W west
                    :F forward
                    :R right
                    :L left})

(defn solve-first
  [instructions]
  (loop [i instructions
         ferry (->Ferry 0 0 :east)]
    (if (empty? i)
      (manhattan-distance ferry)
      (let [instruction (first i)
            action (get action-parser (:action instruction))
            value (:value instruction)
            ferry-moved (action ferry value)]
        (recur (rest i) ferry-moved)))))

;(def instructions (->> "input.txt" slurp parser/data->instructions))

;(println (solve-first instructions))                        ; 508

;(bench (solve-first instructions))                          ; Execution time mean : 365 µs