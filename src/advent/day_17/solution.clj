(ns advent.day-17.solution
  (:use [criterium.core])
  (:require [advent.day-17.parser :as parser]
            [advent.day-17.space-3d :as space]))

(defn solve-first
  [cycles space]
  (let [width (space/width space)
        height (space/height space)
        depth (space/depth space)
        initialized (space/init width height depth cycles)
        initial-space (space/seed-space initialized space)
        cycled (space/cycle-times cycles initial-space)
        cubes (space/cubes cycled)]
    cubes))

;(def space-2d (->> "input.txt" slurp parser/string->2d-space))

;(println (solve-first 6 space-2d))                          ; 273

;(bench (solve-first 6 space-2d))                            ; Execution time mean : 300 ms