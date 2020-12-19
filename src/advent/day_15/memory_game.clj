(ns advent.day-15.memory-game)

(defn first-spoken?
  [last-spoken history]
  (<= (count (get history last-spoken [])) 1))

(defn update-history
  [last-spoken last-turn history]
  (let [spoken-history (get history last-spoken [])
        updated-history (->> (conj spoken-history last-turn) (take-last 2) vec)]
    (assoc history last-spoken updated-history)))

(defn speak
  [{:keys [last-spoken last-turn history] :as state}]
  (let [first-time-spoken? (first-spoken? last-spoken history)
        current-turn (inc last-turn)]
    (if first-time-spoken?
      (let [last-spoken 0
            updated-history (update-history last-spoken current-turn history)]
        (-> state
            (assoc :last-spoken last-spoken
                   :last-turn current-turn
                   :history updated-history)))
      (let [seen (get history last-spoken [])
            last-spoken (->> seen reverse (reduce -))
            updated-history (update-history last-spoken current-turn history)]
        (-> state
            (assoc :last-spoken last-spoken
                   :last-turn current-turn
                   :history updated-history))))))

(defn initialize
  [numbers]
  (loop [n numbers
         turn 1
         result {:last-spoken 0
                 :last-turn   1
                 :history     {}}]
    (if (empty? n)
      result
      (let [number (first n)]
        (recur (rest n) (inc turn) (-> result
                                       (assoc :last-spoken number)
                                       (assoc :last-turn turn)
                                       (update :history assoc number [turn])))))))