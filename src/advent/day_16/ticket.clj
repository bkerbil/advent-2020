(ns advent.day-16.ticket)

(defn valid-value?
  [{:keys [lmin lmax hmin hmax]} value]
  (or (>= lmax value lmin) (>= hmax value hmin)))

(defn map-ticket
  [rules value]
  (map (fn [rule]
         (let [is-valid? (valid-value? rule value)]
           {:value  value
            :rule   (:name rule)
            :valid? is-valid?})) rules))

(defn tickets->mapped
  [rules item]
  (->> item
       (map #(map-ticket rules %))
       (map (fn [item]
              (reduce (fn [acc {:keys [value _rule valid?]}]
                        (if valid?
                          (conj acc true)
                          (conj acc value))) [] item)))
       (map distinct)))

(defn mapped->errors
  [items]
  (->> items
       (map (fn [m] (if (some true? m)
                      0
                      (->> m flatten (reduce +)))))
       (remove #(= % 0))
       vec))

(defn transform-tickets
  [rules values]
  (loop [v values
         results []]
    (if (empty? v)
      results
      (let [item (first v)
            mapped (tickets->mapped rules item)
            valid-in-every-field? (every? #(some true? %) mapped)
            errors (mapped->errors mapped)]
        (if valid-in-every-field?
          (recur (rest v) (conj results {:value item, :valid? valid-in-every-field?}))
          (recur (rest v) (conj results {:value item, :valid? valid-in-every-field?, :errors errors})))))))

(defn error-rate
  [tickets]
  (reduce (fn [acc {:keys [errors]}]
            (if (nil? errors)
              acc
              (+ acc (reduce + errors)))) 0 tickets))