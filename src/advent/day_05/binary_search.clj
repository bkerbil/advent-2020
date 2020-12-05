(ns advent.day-05.binary-search)

(defn half
  [value]
  (int (Math/ceil (/ value 2))))

(defn find-seat
  ([truths value]
   (find-seat truths value 0))
  ([truths middle result]
   (let [difference (half middle)]
     (if (empty? truths)
       result
       (if (true? (first truths))
         (recur (rest truths) difference (+ result difference))
         (recur (rest truths) difference result))))))

(defn seat-id
  [seats row column]
  (+ column (* seats row)))

(defn binary-search-for-seat
  [rows columns boarding-pass]
  (let [row-seat (find-seat (:row boarding-pass) (dec rows))
        column-seat (find-seat (:column boarding-pass) (dec columns))
        seat-id (seat-id columns row-seat column-seat)]
    seat-id))

(def find-seat-id (partial binary-search-for-seat 128 8))