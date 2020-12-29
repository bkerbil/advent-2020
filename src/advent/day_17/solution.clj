(ns advent.day-17.solution
  (:use [criterium.core])
  (:require [advent.day-17.parser :as parser]
            [advent.day-17.protocols :as proto]
            [advent.day-17.space-3d :refer [->Space-3D] :as space-3d]
            [advent.day-17.space-4d :refer [->Space-4D] :as space-4d]))

(defn solve-first
  [cycles space]
  (let [space-3d (space-3d/prepare-for cycles space)
        cycled (proto/cycle-times space-3d cycles)
        cubes (proto/cubes cycled)]
    cubes))

(defn solve-second
  [cycles space]
  (let [space-4d (space-4d/prepare-for cycles space)
        cycled (proto/cycle-times space-4d cycles)
        cubes (proto/cubes cycled)]
    cubes))

;(def space-2d (->> "input.txt" slurp parser/string->2d-space))

;(println (solve-first 6 space-2d))                          ; 273
;(println (solve-second 6 space-2d))                         ; 1504

;(bench (solve-first 6 space-2d))                            ; Execution time mean : 300 ms
;(bench (solve-second 6 space-2d))                           ; Execution time mean : 13 sec