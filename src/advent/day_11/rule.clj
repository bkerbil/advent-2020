(ns advent.day-11.rule)

(defn rule?
  [f1 f2 point status]
  (and (f1 point) (f2 status)))

(def seat-is-empty-and-no-occupied-seats? (partial rule? #(= % 0) #(= (reduce + %) 0)))
(def seat-is-occupied-and-four-or-more-also-occupied? (partial rule? #(= % 1) #(>= (reduce + %) 4)))

(defn action
  [point status]
  (cond
    (seat-is-empty-and-no-occupied-seats? point status) 1
    (seat-is-occupied-and-four-or-more-also-occupied? point status) 0
    :default point))