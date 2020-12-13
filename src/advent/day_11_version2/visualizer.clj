(ns advent.day-11-version2.visualizer)

(defn print
  [{:keys [state]}]
  (let [conversion {nil \. 0 \L 1 \#}]
    (doseq [row (map (fn [row] (map conversion row)) state)]
      (println row))))