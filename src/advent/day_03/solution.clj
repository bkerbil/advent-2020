(ns advent.day-03.solution
  (:use [criterium.core])
  (:require [advent.day-03.transform :as transform]
            [clojure.string :as str]))

(declare width height)

(defn movement-point
  [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn solve-one
  ([starting-point movement chart]
   (let [width (count (first chart))
         height (count chart)]
     (solve-one starting-point movement chart 0 width height)))
  ([point movement chart result width height]
   (let [next-point (movement-point point movement)
         [x y] next-point
         x (mod x width)]
     (if (>= (dec height) y 0)
       (let [value (get-in chart [y x])]
         (recur next-point movement chart (+ result value) width height))
       result))))

(defn solve-multiple
  [starting-point chart movements]
  (reduce (fn [acc movement]
            (let [b (solve-one starting-point movement chart)]
              (* acc b))) 1 movements))

(def solve (partial solve-multiple [0 0]))

;(def chart (->> (slurp "input.txt")
;                (str/split-lines)
;                (transform/transform)))

;(println (solve chart [[3 1]]))                             ; 156
;(println (solve chart [[1 1] [3 1] [5 1] [7 1] [1 2]]))     ; 3521829480

;(bench (solve chart [[3 1]]))                               ; Execution time mean : 76 µs
;(bench (solve chart [[1 1] [3 1] [5 1] [7 1] [1 2]]))       ; Execution time mean : 344 µs