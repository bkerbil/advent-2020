(ns advent.day-11.solution
  (:use [criterium.core])
  (:require [advent.day-11.parser :as parser]
            [advent.day-11.chart :as chart]))

(defn solve-first
  [chart]
  (let [width (count (first chart))
        height (count chart)
        coordinates (for [y (range height)
                          x (range width)]
                      [x y])
        stable-chart (chart/update-chart-until-stable chart coordinates)
        result (->> stable-chart flatten frequencies (:occupied))]
    result))

;(def chart (->> "input.txt" slurp parser/data->chart))

;(println (solve-first chart))                               ; 2338

;(bench (solve-first chart))                                 ; 4 seconds