(ns advent.day-11.solution
  (:use [criterium.core])
  (:require [advent.day-11.parser :as parser]
            [advent.day-11.chart :as chart]
            [advent.day-11.chart2 :as chart2]
            [advent.day-11.rule :as rule]
            [advent.day-11.generate :as generate]))

(defn solve-first
  [width height chart adjacent]
  (let [stable-chart (chart/update-chart-until-stable chart width height rule/action-adjacent adjacent)
        result (->> stable-chart frequencies)]
    (get result 1)))

(defn solve-second
  [width height chart adjacent]
  (let [stable-chart (chart2/update-chart-until-stable chart width height rule/action-line-of-sight adjacent)
        result (->> stable-chart frequencies)]
    (get result 1)))

;(def width 98)
;(def height 93)
;(def adjacent [[-1 -1] [0 -1] [1 -1] [-1 0] [1 0] [-1 1] [0 1] [1 1]])
;(def adjacent-line-of-sight (generate/generate-line-of-sight width height))
;(def chart (->> "input.txt" slurp parser/data->chart))

;(println (solve-first width height chart adjacent))         ; 2338
;(println (solve-second width height chart adjacent-line-of-sight)) ; 2134

;(bench (solve-first width height chart adjacent))           ; 2 sec
;(time (solve-second width height chart adjacent-line-of-sight)) ; !!! 5 minutes !!!