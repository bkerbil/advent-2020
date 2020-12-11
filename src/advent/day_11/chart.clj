(ns advent.day-11.chart
  (:require [advent.day-11.rule :as rule]))

(defn- create-coordinates
  [x y]
  (vec (for [y-range (range (dec y) (+ y 2))
             x-range (range (dec x) (+ x 2))]
         [x-range y-range])))

(defn- remove-origin
  [coordinates]
  (vec (concat (subvec coordinates 0 4) (subvec coordinates 5))))

(defn adjacent
  [x y]
  (->> (create-coordinates x y)
       remove-origin))

(defn status
  [chart coordinates]
  (->> coordinates
       (map (fn [[x y]] (let [status (get-in chart [y x])] status)))
       (remove nil?)))

(defn update-chart
  [chart coordinates]
  (loop [c coordinates
         result []]
    (if (empty? c)
      result
      (let [[x y] (first c)
            adjacent (adjacent x y)
            status (status chart adjacent)
            point (get-in chart [y x])
            new-point (rule/action point status)]
        (recur (rest c) (conj result new-point))))))

(defn chart-seq->chart
  [width chart]
  (->> (partition width chart)
       (map vec)
       vec))

(defn update-chart-until-stable
  [chart coordinates]
  (let [width (count (first chart))]
    (loop [c chart
           hash-value (hash c)]
      (let [updated-chart (->> (update-chart c coordinates) (chart-seq->chart width))
            new-hash (hash updated-chart)]
        (if (= hash-value new-hash)
          updated-chart
          (recur updated-chart new-hash))))))