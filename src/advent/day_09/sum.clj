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
   (let [parts (take 1 numbers)
         amount 1
         sum (reduce + parts)]
     (first-sum-matches target numbers parts amount sum)))
  ([target numbers parts amount sum]
   (if (= target sum)
     (+ (apply min parts) (apply max parts))
     (if (> target sum)
       (let [inc-amount (inc amount)
             inc-parts (take inc-amount numbers)
             inc-sum (reduce + inc-parts)]
         (recur target numbers inc-parts inc-amount inc-sum))
       (let [dec-amount (dec amount)
             dec-numbers (drop 1 numbers)
             dec-parts (take dec-amount dec-numbers)
             dec-sum (reduce + dec-parts)]
         (recur target dec-numbers dec-parts dec-amount dec-sum))))))