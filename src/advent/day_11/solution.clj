(ns advent.day-11.solution
  (:use [criterium.core])
  (:require [advent.day-11.parser :as parser]
            [advent.day-11.chart :as chart]))

;(stest/enumerate-namespace 'advent.day-11.chart)

(defn solve-first
  [width height chart]
  (let [stable-chart (chart/update-chart-until-stable chart width height)
        result (->> stable-chart frequencies)]
    (get result 1)))

(def width 98)
(def height 93)

;(def chart (->> "input.txt" slurp parser/data->chart))

;(println (solve-first width height chart))                  ; 2338

;(bench (solve-first width height chart))                    ; 2 sec