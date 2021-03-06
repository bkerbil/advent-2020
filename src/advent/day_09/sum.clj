(ns advent.day-09.sum)

(defn numbers-summable-to?
  ([numbers sum]
   (numbers-summable-to? numbers sum numbers))
  ([numbers sum knapsack]
   (cond
     (empty? knapsack) false
     (some #(= sum (+ (first knapsack) %)) numbers) true
     :else (recur numbers sum (rest knapsack)))))

(defn first-non-sum
  [amount numbers]
  (let [sum (nth numbers amount)
        part (take amount numbers)]
    (if (numbers-summable-to? part sum)
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