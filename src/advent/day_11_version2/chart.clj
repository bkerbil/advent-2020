(ns advent.day-11-version2.chart)

;(declare update-chart-fn)

(defn update-chart-fn
  ([chart coordinates neighbours-fn rule-fn]
   (update-chart-fn chart coordinates (transient []) neighbours-fn rule-fn))
  ([chart coordinates result neighbours-fn rule-fn]
   (if (empty? coordinates)
     (->> (persistent! result) (partition (count (first chart))) (map vec) vec)
     (let [[x y] (first coordinates)
           point (get-in chart [y x] :out-of-limits)
           directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]]
           neighbours (neighbours-fn chart x y directions)
           freqs (frequencies neighbours)
           empty (get freqs 0 0)
           occupied (get freqs 1 0)
           value (rule-fn {:point point :empty empty :occupied occupied})]
       (recur chart (rest coordinates) (conj! result value) neighbours-fn rule-fn)))))

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