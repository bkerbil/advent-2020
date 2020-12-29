(ns advent.day-17.space-4d
  (:use [clojure.pprint])
  (:require [advent.day-17.protocols :as proto]))

(defn increase-space
  [value repetitions]
  (+ value (* repetitions 2)))

(defn width
  [space]
  (count (first (first space))))

(defn height
  [space]
  (count (first space)))

(defn depth
  [space]
  (count space))

(defn init
  [width height depth repetitions]
  (let [final-width (increase-space width repetitions)
        final-height (increase-space height repetitions)
        final-depth (increase-space depth repetitions)
        final-unseen (increase-space depth repetitions)
        row (vec (take final-width (repeat 0)))
        space-2d (vec (take final-height (repeat row)))
        space-3d (vec (take final-depth (repeat space-2d)))
        space-4d (vec (take final-unseen (repeat space-3d)))]
    space-4d))

(defn seed-space
  [space seed]
  (let [space-width (count (first (first (first space))))
        space-height (count (first (first space)))
        space-middle (int (Math/floor (/ (count (first space)) 2)))
        seed-width (count (first (first seed)))
        seed-height (count (first seed))
        dx (int (Math/floor (/ (- space-width seed-width) 2)))
        dy (int (Math/floor (/ (- space-height seed-height) 2)))
        seed-coordinates (for [y (range seed-height) x (range seed-width)] [x y])
        space-coordinates (map (fn [[x y]] [(+ x dx) (+ y dy) (get-in seed [0 y x] 0)]) seed-coordinates)
        updated-space (reduce (fn [acc [x y value]] (assoc-in acc [space-middle space-middle y x] value)) space space-coordinates)]
    updated-space))

(defn coordinates
  [width height depth unseen]
  (for [w (range unseen)
        z (range depth)
        y (range height)
        x (range width)]
    [x y z w]))

(defn neighbours
  []
  (for [w (range -1 2)
        z (range -1 2)
        y (range -1 2)
        x (range -1 2)
        :when (not= w x y z 0)]
    [x y z w]))

(defn- update-coordinates
  [[x y z w] [a b c d]]
  [(+ x a) (+ y b) (+ z c) (+ w d)])

(defn neighbours-active
  [space coordinate neighbours]
  (->> neighbours
       (map #(update-coordinates coordinate %))
       (map (fn [[a b c d]] (get-in space [d c b a] 0)))
       (reduce +)))

(defn one-cycle
  ([space neighbours coordinates result]
   (if (empty? coordinates)
     result
     (let [coordinate (first coordinates)
           [x y z w] coordinate
           state (get-in space [w z y x] 0)
           neighbours-active (neighbours-active space coordinate neighbours)
           state-active? (= state 1)
           neighbours-active-3-or-2? (>= 3 neighbours-active 2)
           neighbours-active-3? (= neighbours-active 3)]
       (cond
         (and state-active? neighbours-active-3-or-2?) (recur space neighbours (rest coordinates) (assoc-in result [w z y x] 1))
         state-active? (recur space neighbours (rest coordinates) (assoc-in result [w z y x] 0))
         (and (not state-active?) neighbours-active-3?) (recur space neighbours (rest coordinates) (assoc-in result [w z y x] 1))
         :default (recur space neighbours (rest coordinates) (assoc-in result [w z y x] 0)))))))

(def coordinates-memoized (memoize coordinates))
(def neighbours-memoized (memoize neighbours))

(defrecord Space-4D
  [space]
  proto/Information
  (cycle-times [_ times]
    (let [width (count (first (first (first space))))
          height (count (first (first space)))
          depth (count (first space))
          unseen (count space)
          neighbours (neighbours-memoized)
          coordinates (coordinates-memoized width height depth unseen)]
      (loop [t times
             result space]
        (if (<= t 0)
          (->Space-4D result)
          (recur (dec t) (one-cycle result neighbours coordinates result))))))
  (cubes [_]
    (->> space
         flatten
         (reduce +))))

(defn prepare-for
  [cycles space]
  (let [width (width space)
        height (height space)
        depth (depth space)
        initialized (init width height depth cycles)
        initial-space (seed-space initialized space)
        space-4d (->Space-4D initial-space)]
    space-4d))