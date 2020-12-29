(ns advent.day-17.space-3d
  (:use [criterium.core])
  (:require [advent.day-17.protocols :as proto]))

(defn coordinates
  [width height depth]
  (for [z (range depth)
        y (range height)
        x (range width)]
    [x y z]))

(defn neighbours
  []
  (for [z (range -1 2)
        y (range -1 2)
        x (range -1 2)
        :when (not= x y z 0)]
    [x y z]))

(defn- update-coordinates
  [[x y z] [a b c]]
  [(+ x a) (+ y b) (+ z c)])

(defn neighbours-active
  [space coordinate neighbours]
  (->> neighbours
       (map #(update-coordinates coordinate %))
       (map (fn [[a b c]] (get-in space [c b a] 0)))
       (reduce +)))

(defn one-cycle
  ([space neighbours coordinates result]
   (if (empty? coordinates)
     result
     (let [coordinate (first coordinates)
           [x y z] coordinate
           state (get-in space [z y x] 0)
           neighbours-active (neighbours-active space coordinate neighbours)
           state-active? (= state 1)
           neighbours-active-3-or-2? (>= 3 neighbours-active 2)
           neighbours-active-3? (= neighbours-active 3)]
       (cond
         (and state-active? neighbours-active-3-or-2?) (recur space neighbours (rest coordinates) (assoc-in result [z y x] 1))
         state-active? (recur space neighbours (rest coordinates) (assoc-in result [z y x] 0))
         (and (not state-active?) neighbours-active-3?) (recur space neighbours (rest coordinates) (assoc-in result [z y x] 1))
         :default (recur space neighbours (rest coordinates) (assoc-in result [z y x] 0)))))))

(def coordinates-memoized (memoize coordinates))
(def neighbours-memoized (memoize neighbours))

(defrecord Space-3D
  [space]
  proto/Information
  (cycle-times [_ times]
    (let [width (count (first (first space)))
          height (count (first space))
          depth (count space)
          neighbours (neighbours-memoized)
          coordinates (coordinates-memoized width height depth)]
      (loop [t times
             result space]
        (if (<= t 0)
          (->Space-3D result)
          (recur (dec t) (one-cycle result neighbours coordinates result))))))
  (cubes [_]
    (->> space
         flatten
         (reduce +))))