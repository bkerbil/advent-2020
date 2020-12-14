(ns advent.day-11-version2.chart)

(defn result->2d-vector
  [result width]
  (->> (persistent! result)
       (partition width)
       (map vec)
       vec))

(defn update-chart-fn
  [chart neighbours-fn rule-fn]
  (let [width (count (first chart))]
    (fn [coordinates result]
      (if (empty? coordinates)
        (result->2d-vector result width)
        (let [[x y] (first coordinates)
              point (get-in chart [y x] :out-of-limits)
              directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]]
              neighbours (neighbours-fn chart x y directions)
              freqs (frequencies neighbours)
              empty (get freqs 0 0)
              occupied (get freqs 1 0)
              value (rule-fn {:point point :empty empty :occupied occupied})]
          (recur (rest coordinates) (conj! result value)))))))

(defn stabilize-fn
  [chart neighbours-fn rule-fn]
  (let [width (count (first chart))
        height (count chart)
        coordinates (for [y (range height)
                          x (range width)]
                      [x y])]
    (loop [current-chart chart]
      (let [updated-chart ((update-chart-fn current-chart neighbours-fn rule-fn) coordinates (transient []))]
        (if (= current-chart updated-chart)
          current-chart
          (recur updated-chart))))))