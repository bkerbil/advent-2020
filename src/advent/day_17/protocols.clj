(ns advent.day-17.protocols)

(defprotocol Information
  (cycle-times [this times])
  (cubes [this]))