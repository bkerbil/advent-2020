(ns advent.day-11.todo
  (:use [clojure.pprint]))

(def chart [[0 nil nil nil 0]
            [nil nil nil nil nil]
            [nil nil nil nil nil]
            [nil nil nil nil nil]
            [0 nil 1 nil 0]])

(def directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]])

(defn neighbour
  [chart direction x y]
  (let [value (get-in chart [y x] :out-of-limits)
        [a b] direction]
    (cond
      (= value :out-of-limits) nil
      (number? value) value
      :else (recur chart direction (+ x a) (+ y b)))))

(defn neighbours
  [chart directions x y]
  (->> directions
       (map #(neighbour chart % x y))
       (remove nil?)))

;(pprint (neighbours chart directions 2 2))