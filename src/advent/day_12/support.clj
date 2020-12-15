(ns advent.day-12.support
  (:require [advent.day-12.protocols :as proto]
            [advent.day-12.ferry :refer [->Ferry]]
            [advent.day-12.support :as support]))

(def action-parser {:N proto/north
                    :E proto/east
                    :S proto/south
                    :W proto/west
                    :F proto/forward
                    :R proto/right
                    :L proto/left})

(defn iterate-instructions
  [instructions ferry]
  (if (empty? instructions)
    ferry
    (let [instruction (first instructions)
          action (get support/action-parser (:action instruction))
          value (:value instruction)
          ferry-moved (action ferry value)]
      (recur (rest instructions) ferry-moved))))

(defn manhattan-distance
  ([ferry]
   (let [x (Math/abs ^int (:x ferry))
         y (Math/abs ^int (:y ferry))]
     (+ x y))))