(ns advent.day-11-version2.pre-corona
  (:require [advent.day-11-version2.protocols :as proto])
  (:gen-class))

(declare update-chart-fn ->Chart)

(defn neighbours-fn
  [state x y directions]
  (loop [d directions
         result []]
    (if (empty? d)
      result
      (let [[a b] (first d)
            dx (+ x a)
            dy (+ y b)
            value (get-in state [dy dx] :out-of-limits)]
        (cond
          (= value :out-of-limits) (recur (rest d) result)
          (= value nil) (recur (rest d) result)
          :else (recur (rest d) (conj result value)))))))

(defn update-chart-fn
  [{:keys [state] :as chart} width _height coordinates]
  (loop [c coordinates
         result []]
    (if (empty? c)
      (let [new-state (->> result (partition width) (map vec) vec)
            chart-record (->Chart new-state)]
        chart-record)
      (let [[x y] (first c)
            point (get-in state [y x] :out-of-limits)
            neighbours (proto/neighbours chart x y)
            freqs (frequencies neighbours)
            empty (get freqs 0 0)
            occupied (get freqs 1 0)
            value (proto/rule {:point point :empty empty :occupied occupied})]
        (recur (rest c) (conj result value))))))

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
      (neighbours-fn state x y directions)))
  (update-chart [chart]
    (let [width (count (first state))
          height (count state)
          coordinates (for [y (range height)
                            x (range width)]
                        [x y])]
      (update-chart-fn chart width height coordinates)))
  (final-state [chart]
    (final-state-fn chart)))

(defmethod proto/rule nil
  [{:keys [point]}]
  point)

(defmethod proto/rule 0
  [{:keys [occupied]}]
  (if (zero? occupied) 1 0))

(defmethod proto/rule 1
  [{:keys [occupied]}]
  (if (>= occupied 4) 0 1))