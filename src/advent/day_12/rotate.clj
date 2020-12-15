(ns advent.day-12.rotate)

(defn rotate-clockwise
  [x y]
  [y (- x)])

(defn rotate-counterclockwise
  [x y]
  [(- y) x])

(defn rotate-times
  [f degrees coordinates]
  (if (<= degrees 0)
    coordinates
    (recur f (- degrees 90) (apply f coordinates))))

(def rotate-clockwise-degrees (partial rotate-times rotate-clockwise))
(def rotate-counterclockwise-degrees (partial rotate-times rotate-counterclockwise))