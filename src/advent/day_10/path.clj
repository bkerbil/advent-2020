(ns advent.day-10.path
  (:require [clojure.core.match :refer [match]]))

(defn same-path?
  [a b]
  (loop [a (sort a)
         b (sort b)]
    (if (or (empty? a) (empty? b))
      true
      (if (= (first a) (first b))
        (recur (rest a) (rest b))
        false))))

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
           temp (remove #(same-path? % head) tail)]
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