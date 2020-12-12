(ns advent.day-11.rule)

(defn rule?
  [f1 f2 point status]
  (and (f1 point) (f2 status)))

;(def is-zero? #(= % 0))
;(def is-one? #(= % 1))
;(def is-zero? #(= (reduce + %) 0))

(def seat-is-empty-and-no-occupied-seats? (partial rule? #(= % 0) #(= (reduce + %) 0)))
(def seat-is-occupied-and-four-or-more-also-occupied? (partial rule? #(= % 1) #(>= (reduce + %) 4)))

(defn action-adjacent
  [point status]
  (cond
    (seat-is-empty-and-no-occupied-seats? point status) 1
    (seat-is-occupied-and-four-or-more-also-occupied? point status) 0
    :default point))

(def seat-is-in-line-of-sight-and-no-occupied-seats? (partial rule? #(= % 0) #(= (reduce + %) 0)))
(def seat-is-occupied-and-five-or-more-also-occupied? (partial rule? #(= % 1) #(>= (reduce + %) 5)))

(defn action-line-of-sight
  [point status]
  (cond
    (seat-is-in-line-of-sight-and-no-occupied-seats? point status) 1
    (seat-is-occupied-and-five-or-more-also-occupied? point status) 0
    :default point))