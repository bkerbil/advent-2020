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

(defn filter-valid
  [tickets]
  (filter (fn [{:keys [valid?]}] valid?) tickets))

(defn rules-match-value
  [rules rules-indexes value]
  (loop [indexes rules-indexes
         results []]
    (if (empty? indexes)
      results
      (let [index (first indexes)
            rule (nth rules index)
            applies? (valid-value? rule value)]
        (if applies?
          (recur (rest indexes) (conj results index))
          (recur (rest indexes) results))))))

(defn whittler
  [input]
  (let [length (count input)]
    (loop [index 0
           d input
           found #{}
           result {}]
      (if (every? empty? d)
        result
        (let [item (first d)
              removed (vec (remove found item))
              finished? (= (count removed) 1)
              value (first removed)]
          (cond
            (>= index length) (recur 0 d found result)
            finished? (recur (inc index) (conj (vec (rest d)) []) (conj found value) (assoc result value index))
            :default (recur (inc index) (conj (vec (rest d)) item) found result)))))))

(defn matching
  [rules rules-indexes ticket]
  (loop [indexes rules-indexes
         t ticket
         results []]
    (if (empty? indexes)
      results
      (let [idx (first indexes)
            value (first t)
            matches (rules-match-value rules idx value)]
        (recur (rest indexes) (rest t) (conj results matches))))))

(defn possibilities
  [rules tickets]
  (let [width (->> tickets first :value count)
        rules-indexes (vec (for [index (range width)]
                             index))]
    (loop [t tickets
           results (for [_ (range width)]
                     rules-indexes)]
      (if (empty? t)
        results
        (let [ticket (first t)
              values (:value ticket)
              matches (matching rules results values)]
          (recur (rest t) matches))))))