(ns advent.day-11.chart
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

(defn neighbours-adjacent
  [chart coordinates a b width height]
  (->> coordinates
       (map (fn [[x y]]
              (let [dx (+ a x)
                    dy (+ b y)
                    pointer (coordinate->pointer dx dy width)
                    value (nth chart pointer nil)]
                (if (or (neg? dx) (neg? dy) (>= dx width) (>= dy height))
                  nil
                  value))))
       (remove nil?)))

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