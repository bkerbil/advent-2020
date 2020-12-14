(ns advent.day-11-version2.chart)

(declare neighbours-adjacent-fn update-chart-fn rule)

(defn neighbours-adjacent-fn
  ([chart x y directions]
   (neighbours-adjacent-fn chart x y directions []))
  ([chart x y directions result]
   (if (empty? directions)
     result
     (let [[a b] (first directions)
           dx (+ x a)
           dy (+ y b)
           value (get-in chart [dy dx] :out-of-limits)]
       (cond
         (= value :out-of-limits) (recur chart x y (rest directions) result)
         (= value nil) (recur chart x y (rest directions) result)
         :else (recur chart x y (rest directions) (conj result value)))))))

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

(defn update-chart-fn
  ([chart coordinates neighbours-fn rule-fn]
   (update-chart-fn chart coordinates [] neighbours-fn rule-fn))
  ([chart coordinates result neighbours-fn rule-fn]
   (if (empty? coordinates)
     (->> result (partition (count (first chart))) (map vec) vec)
     (let [[x y] (first coordinates)
           point (get-in chart [y x] :out-of-limits)
           ; todo fix this
           directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]]
           neighbours (neighbours-fn chart x y directions)
           freqs (frequencies neighbours)
           empty (get freqs 0 0)
           occupied (get freqs 1 0)
           value (rule-fn {:point point :empty empty :occupied occupied})]
       (recur chart (rest coordinates) (conj result value) neighbours-fn rule-fn)
       ))))

(defn stabilize-fn
  [chart neighbours-fn rule-fn]
  (let [width (count (first chart))
        height (count chart)
        coordinates (for [y (range height)
                          x (range width)]
                      [x y])]
    (loop [current-chart chart]
      (let [updated-chart (update-chart-fn current-chart coordinates neighbours-fn rule-fn)]
        (if (= current-chart updated-chart)
          current-chart
          (recur updated-chart))))))

(defn rule-pre-corona
  [{:keys [point occupied]}]
  (cond
    (nil? point) point
    (= 0 point) (if (zero? occupied) 1 0)
    (= 1 point) (if (>= occupied 4) 0 1)))

(defn rule-post-corona
  [{:keys [point occupied]}]
  (cond
    (nil? point) point
    (= 0 point) (if (zero? occupied) 1 0)
    (= 1 point) (if (>= occupied 5) 0 1)))