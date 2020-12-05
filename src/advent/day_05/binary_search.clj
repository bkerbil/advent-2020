(ns advent.day-05.binary-search)

(defn half
  [value]
  (int (Math/ceil (/ value 2))))

(defn find-seat
  [truths value]
  (loop [t truths
         value value
         result 0]
    (let [h (half value)]
      (if (empty? t)
        result
        (if (true? (first t))
          (recur (rest t) h (+ result h))
          (recur (rest t) h result))))))

(defn seat-id
  [seats row column]
  (+ column (* seats row)))

(defn binary-search-for-seat
  [rows columns boarding-pass]
  (let [row-seat (find-seat (:row boarding-pass) (dec rows))
        coTransform lumn-seat (find-seat (:column boarding-pass) (dec columns))
        seat-id (seat-id columns row-seat column-seat)]
    seat-id))

(def find-seat-id (partial binary-search-for-seat 128 8))