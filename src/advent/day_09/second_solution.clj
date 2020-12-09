(ns advent.day-09.second_solution)

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