(ns advent.day-10.solution
  (:use [criterium.core])
  (:require [advent.day-10.parser :as parser]
            [advent.day-10.path :as path]
            [clojure.core.match :refer [match]]))

(defn solve-first
  [numbers]
  (->> numbers
       sort
       (partition 2 1)
       (map (fn [[a b]] (- b a)))
       (frequencies)
       (#(* (% 1) (% 3)))))

(defn within-reach
  [[point & nodes]]
  (vec (filter #(<= (- % point) 3) nodes)))

(defn add-within-reach
  [numbers]
  (concat numbers (within-reach numbers)))

(defn point-in?
  [point tails]
  (if (empty? tails)
    false
    (let [tail (first tails)]
      (if (some #{point} tail)
        true
        (recur point (rest tails))))))

(defn concat-to-tails
  [point connections tails]
  (reduce (fn [acc v]
            (let [tail (last v)
                  new-connections (map (fn [x] (conj v x)) connections)]
              (if (= point tail)
                (concat acc new-connections)
                acc))) [] tails))

(defn distinct-tails
  ([tails]
   (let [sorted (sort-by count > tails)]
     (distinct-tails sorted [])))
  ([tails results]
   (if (empty? tails)
     results
     (let [head (first tails)
           tail (rest tails)
           temp (remove #(path/same-path? % head) tail)]
       (recur temp (conj results head))))))

(defn to-multiplications
  [{:keys [tails] :as acc} [point _ _ _ & connections]]
  (let [point-in? (point-in? point tails)
        count-connections (count connections)]
    (match [point-in? (> count-connections 1)]
           [false false] acc
           [false true] (let [combined (map (fn [p] [point p]) connections)]
                          (assoc acc :tails combined))
           [true true] (let [tail-concat (concat-to-tails point connections tails)]
                         (assoc acc :tails (concat tails tail-concat)))
           [_ _] (let [distinct-tails (distinct-tails tails)]
                   (-> acc
                       (assoc :tails [])
                       (update :multiplications conj (count distinct-tails)))))))

(defn finalize-tails
  [{:keys [tails multiplications] :as acc}]
  (assoc acc :multiplications (conj multiplications (max (count (distinct-tails tails)) 1))))

(defn solve-second
  [numbers]
  (->> numbers
       sort
       (partition 4 1)
       (map add-within-reach)
       (reduce to-multiplications {:tails [], :multiplications []})
       finalize-tails
       (:multiplications)
       (reduce *)))

;(def numbers (->> "input.txt" slurp parser/data->numbers (apply conj [0 157])))

;(println (solve-first numbers))                             ; 1984
;(println (solve-second numbers))                            ; 3543369523456

;(bench (solve-first numbers))                               ; Execution time mean : 70 µs
;(bench (solve-second numbers))                              ; Execution time mean : 570 µs