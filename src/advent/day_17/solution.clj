(ns advent.day-17.solution
  (:use [criterium.core])
  (:require [advent.day-17.parser :as parser]
            [advent.day-17.protocols :as proto]
            [advent.day-17.space :as space]
            [advent.day-17.space-3d :refer [->Space-3D]]))

(defn solve-first
  [cycles space]
  (let [width (space/width space)
        height (space/height space)
        depth (space/depth space)
        initialized (space/init width height depth cycles)
        initial-space (space/seed-space initialized space)
        space-3d (->Space-3D initial-space)
        cycled (proto/cycle-times space-3d cycles)
        cubes (proto/cubes cycled)]
    cubes))

;(def space-2d (->> "input.txt" slurp parser/string->2d-space))

;(println (solve-first 6 space-2d))                          ; 273

;(bench (solve-first 6 space-2d))                            ; Execution time mean : 300 ms