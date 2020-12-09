(ns advent.day-09.second_solution
  (:require [clojure.core.match :refer [match]]))

(defn cumulative-sum-matches
  [target values]
  (reduce (fn [numbers number]
            (let [cumulative (reduce + numbers)
                  sum (+ cumulative number)]
              (cond
                (< target sum) (reduced nil)
                (> target sum) (conj numbers number)
                :else (let [min (apply min numbers)
                            max (apply max numbers)
                            result (+ min max)]
                        (reduced result))))) [] values))

(defn first-cumulative-sum-matches
  [target numbers]
  (let [matches (cumulative-sum-matches target numbers)]
    (if matches
      matches
      (recur target (rest numbers)))))

(defn first-snake-sum-matches
  ([target numbers]
   (first-snake-sum-matches target numbers (take 1 numbers) 1 (count numbers) (reduce + (take 1 numbers))))
  ([target numbers parts amount limit sum]
   (match [(= target sum) (> target sum)]
          [true false] (let [min (apply min parts)
                             max (apply max parts)
                             result (+ min max)]
                         result)
          [false true] (let [next-amount (inc amount)
                             next-parts (take next-amount numbers)
                             next-sum (reduce + next-parts)]
                         (recur target numbers next-parts next-amount limit next-sum))
          :else (let [next-amount (dec amount)
                      next-parts (drop 1 parts)
                      next-sum (reduce + next-parts)
                      next-numbers (drop 1 numbers)]
                  (recur target next-numbers next-parts next-amount limit next-sum)))))