(ns advent.day-09.first_solution)

(defn can-sum-to?
  [numbers to sum]
  (some #(= sum (+ to %)) numbers))

(defn numbers-summable-to?
  ([numbers target]
   (numbers-summable-to? numbers target numbers))
  ([numbers sum knapsack]
   (cond (empty? knapsack) false
         (can-sum-to? numbers (first knapsack) sum) true
         :else (recur numbers sum (rest knapsack)))))

(defn first-non-sum
  [amount numbers]
  (let [sum (nth numbers amount)]
    (if (numbers-summable-to? (take amount numbers) sum)
      (recur amount (rest numbers))
      sum)))