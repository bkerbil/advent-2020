(ns advent.day-03.solution
  (:use [criterium.core]
        [clojure.pprint])
  (:require [support.reader :as r]
            [advent.day-03.transform :as t]))

(defn movement-point
  [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn solve-one
  [starting-point movement chart]
  (let [width (count (first chart))
        height (count chart)]
    (loop [point starting-point
           result 0]
      (let [next-point (movement-point point movement)
            [x y] next-point
            x (mod x width)
            continue? (>= (dec height) y 0)]
        (if continue?
          (let [value (get-in chart [y x])]
            (recur next-point (+ result value)))
          result)))))

(defn solve-multiple
  [starting-point chart movements]
  (reduce (fn [acc movement]
            (let [b (solve-one starting-point movement chart)]
              (* acc b))) 1 movements))

;(def chart (->> (r/read-file-as-vec "input.txt") (t/transform)))
;(def starting-point [0 0])

;(def solve (partial solve-multiple starting-point chart))

;(println (solve [[3 1]]))                                   ; 156
;(println (solve [[1 1] [3 1] [5 1] [7 1] [1 2]]))           ; 3521829480

;(bench (solve [[3 1]]))                                     ; Execution time mean : 81 µs
;(bench (solve [[1 1] [3 1] [5 1] [7 1] [1 2]]))             ; Execution time mean : 366 µs