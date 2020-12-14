(ns advent.day-11.rule)

(defn pre-corona
  [{:keys [point occupied]}]
  (cond
    (= 0 point) (if (zero? occupied) 1 0)
    (= 1 point) (if (>= occupied 4) 0 1)
    :default point))

(defn post-corona
  [{:keys [point occupied]}]
  (cond
    (= 0 point) (if (zero? occupied) 1 0)
    (= 1 point) (if (>= occupied 5) 0 1)
    :default point))