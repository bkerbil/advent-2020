(ns advent.day-11-version2.protocols)

(defprotocol IChart
  (neighbours [this x y])
  (update-chart [this])
  (final-state [this]))

(defmulti rule :point)