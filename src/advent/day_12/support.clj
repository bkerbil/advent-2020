(ns advent.day-12.support
  (:require [advent.day-12.protocols :as proto]))

(defn manhattan-distance
  ([ferry]
   (let [x (Math/abs ^int (:x ferry))
         y (Math/abs ^int (:y ferry))]
     (+ x y))))

(def action-parser {:N proto/north
                    :E proto/east
                    :S proto/south
                    :W proto/west
                    :F proto/forward
                    :R proto/right
                    :L proto/left})