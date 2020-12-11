(ns advent.day-11.rule)

(defn seat-is-empty-and-no-occupied-seats?
  [point status]
  (let [result (filter (fn [s] (= s :occupied)) status)]
    (and (= point :empty) (empty? result))))

(defn seat-is-occupied-and-four-or-more-also-occupied?
  [point status]
  (let [result (filter (fn [s] (= s :occupied)) status)]
    (and (= point :occupied) (>= (count result) 4))))

(defn action
  [point status]
  (cond
    (seat-is-empty-and-no-occupied-seats? point status) :occupied
    (seat-is-occupied-and-four-or-more-also-occupied? point status) :empty
    :default point))