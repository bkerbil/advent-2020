(ns advent.day-03.solution
  (:use [criterium.core])
  (:require [support.reader :as r]
            [advent.day-03.transform :as t]))

(def value->visited {:open :open-visited
                     :tree :tree-visited})

(defn movement-point
  [[x1 y1] [x2 y2]]
  [(+ x1 x2) (+ y1 y2)])

(defn move
  [chart point movement]
  (let [movement-point (movement-point point movement)
        value (get-in chart (reverse movement-point))
        updated (assoc-in chart (reverse movement-point) (value->visited value))]
    updated))

(defn zip
  [a b]
  (loop [a a
         b b
         result []]
    (if (empty? a)
      result
      (let [zipped (vec (concat (first a) (first b)))]
        (recur (rest a) (rest b) (conj result zipped))))))

(defn repeat-chart
  [chart times]
  (loop [t times
         result chart]
    (if (<= t 0)
      result
      (recur (dec t) (zip result chart)))))

(defn repeat-how-many-times
  [chart movement]
  (let [chart-width (count (first chart))
        chart-height (count chart)
        [x _] movement
        required-width (* (inc chart-height) x)
        repeat-times (int (Math/ceil (/ required-width chart-width)))]
    repeat-times))

(defn outside-chart?
  [width height [x y]]
  (not (and (>= (dec width) x 0) (>= (dec height) y 0))))

(defn amount-of-trees
  [chart]
  (reduce (fn [acc v]
            (let [freq (frequencies v)
                  trees-visited (:tree-visited freq 0)
                  result (+ acc trees-visited)]
              result)) 0 chart))

(defn solve
  [starting-point movement chart]
  (let [repeat-chart-times (repeat-how-many-times chart movement)
        repeated-chart (repeat-chart chart repeat-chart-times)
        width (count (first repeated-chart))
        height (count repeated-chart)]
    (loop [point starting-point
           current-chart repeated-chart]
      (let [updated-point (movement-point point movement)
            outside? (outside-chart? width height updated-point)]
        (if outside?
          (amount-of-trees current-chart)
          (let [updated-chart (move current-chart point movement)]
            (recur updated-point updated-chart)))))))

(defn solve-first
  [starting-point movement chart]
  (solve starting-point movement chart))

(defn solve-second
  [starting-point movements chart]
  (reduce (fn [acc movement]
            (let [b (solve starting-point movement chart)]
              (* acc b))) 1 movements))

;(def chart (->> (r/read-file-as-vec "input.txt") (t/transform)))

;(println (solve-first [0 0] [3 1] chart))                   ; 156
;(println (solve-second [0 0] [[1 1] [3 1] [5 1] [7 1] [1 2]] chart)) ; 3521829480

;(bench (solve-first [0 0] [3 1] chart))                     ; Execution time mean : 122 ms
;(bench (solve-second [0 0] [[1 1] [3 1] [5 1] [7 1] [1 2]] chart)) ; Execution time mean : 1 sec

