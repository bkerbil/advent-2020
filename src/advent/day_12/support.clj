(ns advent.day-12.support
  (:require [advent.day-12.protocols :as proto]
            [advent.day-12.instruction :refer [->Instruction]]
            [advent.day-12.support :as support]))

(def action-parser {:N proto/north
                    :E proto/east
                    :S proto/south
                    :W proto/west
                    :F proto/forward
                    :R proto/right
                    :L proto/left})

(defn reduce-instructions
  [instructions instruction]
  (reduce (fn [result instruction]
            (let [action (get support/action-parser (:action instruction))
                  value (:value instruction)
                  ferry-moved (action result value)]
              ferry-moved)) instruction instructions))

(defn manhattan-distance
  ([instruction]
   (let [x (Math/abs ^int (:x instruction))
         y (Math/abs ^int (:y instruction))]
     (+ x y))))