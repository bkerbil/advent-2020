(ns advent.day-09.support)

(defn numbers-summable-to?
  [numbers sum]
  (->> (for [a numbers
             b numbers
             :when (and (= sum (+ a b)) (not= a b))]
         [a b])
       (map #(reduce + %))
       empty?
       not))

(defn first-non-sum
  [amount numbers]
  (let [candidates (take amount numbers)
        sum (nth numbers amount)
        valid? (numbers-summable-to? candidates sum)]
    (if valid?
      (recur amount (rest numbers))
      sum)))

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
  (loop [numbers numbers]
    (let [matches (cumulative-sum-matches target numbers)]
      (if matches
        matches
        (recur (rest numbers))))))