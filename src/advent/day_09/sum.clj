(ns advent.day-09.sum)

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

(defn first-sum-matches
  ([target numbers]
   (first-sum-matches target numbers 1))
  ([target numbers amount]
   (let [parts (take amount numbers)
         sum (reduce + parts)]
     (if (= target sum)
       (+ (apply min parts) (apply max parts))
       (if (> target sum)
         (recur target numbers (inc amount))
         (recur target (drop 1 numbers) (dec amount)))))))