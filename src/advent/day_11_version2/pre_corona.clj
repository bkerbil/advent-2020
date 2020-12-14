(ns advent.day-11-version2.pre-corona
  (:require [advent.day-11-version2.protocols :as proto]
            [advent.day-11-version2.parser :as parser])
  (:gen-class))

(declare neighbours-adjacent-fn update-chart-fn ->Chart rule)

(defn neighbours-adjacent-fn
  ([state x y directions]
   (neighbours-adjacent-fn state x y directions []))
  ([state x y directions result]
   (if (empty? directions)
     result
     (let [[a b] (first directions)
           dx (+ x a)
           dy (+ y b)
           value (get-in state [dy dx] :out-of-limits)]
       (cond
         (= value :out-of-limits) (recur state x y (rest directions) result)
         (= value nil) (recur state x y (rest directions) result)
         :else (recur state x y (rest directions) (conj result value)))))))

(defn update-chart-fn
  ([{:keys [state] :as chart} width _height coordinates]
   (update-chart-fn chart width _height coordinates []))
  ([{:keys [state] :as chart} width _height coordinates result]
   (if (empty? coordinates)
     (let [new-state (->> result (partition width) (map vec) vec)
           chart-record (->Chart new-state)]
       chart-record)
     (let [[x y] (first coordinates)
           point (get-in state [y x] :out-of-limits)
           neighbours (proto/neighbours chart x y)
           freqs (frequencies neighbours)
           empty (get freqs 0 0)
           occupied (get freqs 1 0)
           value (rule {:point point :empty empty :occupied occupied})]
       (recur chart width _height (rest coordinates) (conj result value))))))

(defn final-state-fn
  [chart]
  (loop [current chart]
    (let [updated (proto/update-chart current)]
      (if (= current updated)
        current
        (recur updated)))))

(defrecord Chart
  [state]
  proto/IChart
  (neighbours [_ x y]
    (let [directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]]]
      (neighbours-adjacent-fn state x y directions)))
  (update-chart [chart]
    (let [width (count (first state))
          height (count state)
          coordinates (for [y (range height)
                            x (range width)]
                        [x y])]
      (update-chart-fn chart width height coordinates)))
  (final-state [chart]
    (final-state-fn chart)))

(defn rule
  [{:keys [point occupied]}]
  (cond
    (nil? point) point
    (= 0 point) (if (zero? occupied) 1 0)
    (= 1 point) (if (>= occupied 4) 0 1)))