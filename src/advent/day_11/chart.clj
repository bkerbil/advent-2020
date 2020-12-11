(ns advent.day-11.chart
  (:require [advent.day-11.rule :as rule]
            [clojure.spec.alpha :as s]
            [clojure.string :as str]))

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

(def adjacent [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]])

(defn update-chart
  ([chart width height]
   (update-chart chart chart 0 0 width height []))
  ([original chart x y width height result]
   (cond
     (empty? chart) result
     (>= x width) (recur original chart 0 (inc y) width height result)
     :default (let [adjacent adjacent
                    status (neighbours-adjacent original adjacent x y width height)
                    value (first chart)
                    new-point (rule/action value status)]
                (recur original (rest chart) (inc x) y width height (conj result new-point))))))

(defn update-chart-until-stable
  ([chart width height]
   (update-chart-until-stable chart width height (hash chart)))
  ([chart width height hash-value]
   (let [updated (update-chart chart width height)
         new-hash (hash updated)]
     (if (= hash-value new-hash)
       updated
       (recur updated width height new-hash)))))