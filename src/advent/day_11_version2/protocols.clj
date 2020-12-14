(ns advent.day-11-version2.protocols
  (:gen-class))

(defprotocol IChart
  (neighbours [this x y])
  (update-chart [this])
  (final-state [this]))