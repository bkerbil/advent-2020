(ns advent.day-12.protocols)

(defprotocol Move
  "Interface for moving objects."
  (north [this value])
  (east [this value])
  (south [this value])
  (west [this value])
  (forward [this value])
  (right [this degrees])
  (left [this degrees]))