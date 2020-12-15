(ns advent.day-12.support
  (:require [advent.day-12.protocols :as proto]))

(def actions {:N proto/north
              :E proto/east
              :S proto/south
              :W proto/west
              :F proto/forward
              :R proto/right
              :L proto/left})

(defn reduce-instructions
  [instructions instruction]
  (reduce (fn [result instruction]
            (let [action (get actions (:action instruction))
                  value (:value instruction)
                  ferry-moved (action result value)]
              ferry-moved)) instruction instructions))

(defn manhattan-distance
  ([instruction]
   (let [x (Math/abs ^int (:x instruction))
         y (Math/abs ^int (:y instruction))]
     (+ x y))))