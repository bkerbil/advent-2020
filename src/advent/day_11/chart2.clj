(ns advent.day-11.chart2
  (:require [clojure.spec.alpha :as s]))

(declare coordinate->pointer)

(s/def ::int int?)
(s/def ::coordinate->pointer-args (s/cat :x ::int :y ::int :width ::int))
(s/def ::coordinate->pointer-ret ::int)

(s/fdef coordinate->pointer
        :args ::coordinate->pointer-args
        :ret ::coordinate->pointer-ret)

(defn coordinate->pointer
  [x y width]
  {:pre  [(s/valid? ::int x) (s/valid? ::int y) (s/valid? ::int width)]
   :post [(s/valid? ::int %)]}
  (+ x (* y width)))

(defn direction
  [a b x y]
  (cond
    (and (= x a) (> y b)) :north
    (and (> x a) (> y b)) :north-east
    (and (> x a) (= y b)) :east
    (and (> x a) (< y b)) :south-east
    (and (= x a) (< y b)) :south
    (and (< x a) (< y b)) :south-west
    (and (< x a) (= y b)) :west
    (and (< x a) (> y b)) :north-west
    :other :origin))

(defn distance
  [a b x y]
  (let [x-axis (+ (* a a) (* x x))
        y-axis (+ (* b b) (* y y))
        result (Math/sqrt (+ x-axis y-axis))]
    result))

(defn neighbours-adjacent
  [chart coordinates a b width height]
  (->> coordinates
       (map (fn [[x y]]
              (let [dx (+ a x)
                    dy (+ b y)
                    pointer (coordinate->pointer dx dy width)
                    value (nth chart pointer nil)]
                (if (or (neg? dx) (neg? dy) (>= dx width) (>= dy height) (nil? value))
                  nil
                  (let [direction (direction a b dx dy)
                        distance (distance a b x y)]
                    {:direction direction
                     :distance  distance
                     :value     value})))))
       (remove nil?)
       (sort-by :direction)
       (group-by :direction)
       (map (fn [[_ directions]]
              (let [sorted (sort-by :distance directions)
                    result (first sorted)]
                (:value result))))))

(defn update-chart
  ([chart width height action adjacent]
   (update-chart chart chart 0 0 width height (transient []) action adjacent))
  ([original chart x y width height result action adjacent]
   (cond
     (empty? chart) (persistent! result)
     (>= x width) (recur original chart 0 (inc y) width height result action adjacent)
     :default (let [status (neighbours-adjacent original adjacent x y width height)
                    value (first chart)
                    new-point (action value status)]
                (recur original (rest chart) (inc x) y width height (conj! result new-point) action adjacent)))))

(defn update-chart-until-stable
  ([chart width height action adjacent]
   (update-chart-until-stable chart width height (hash chart) action adjacent))
  ([chart width height hash-value action adjacent]
   (let [updated (update-chart chart width height action adjacent)
         new-hash (hash updated)]
     (if (= chart updated)
       updated
       (recur updated width height new-hash action adjacent)))))