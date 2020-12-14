(ns advent.day-11.vision)

(defn neighbours-adjacent-fn
  ([chart x y directions]
   (neighbours-adjacent-fn chart x y directions (transient [])))
  ([chart x y directions result]
   (if (empty? directions)
     (persistent! result)
     (let [[a b] (first directions)
           dx (+ x a)
           dy (+ y b)
           value (get-in chart [dy dx] :out-of-limits)]
       (cond
         (= value :out-of-limits) (recur chart x y (rest directions) result)
         (= value nil) (recur chart x y (rest directions) result)
         :else (recur chart x y (rest directions) (conj! result value)))))))

(defn line-of-sight-fn
  [chart x y direction]
  (let [[a b] direction
        dx (+ x a)
        dy (+ y b)
        value (get-in chart [dy dx] :out-of-limits)]
    (cond
      (= value :out-of-limits) nil
      (number? value) value
      :else (recur chart dx dy direction))))

(defn neighbours-line-of-sight-fn
  [chart x y directions]
  (->> directions
       (map #(line-of-sight-fn chart x y %))
       (remove nil?)))