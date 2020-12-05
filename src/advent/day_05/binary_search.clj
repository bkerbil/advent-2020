(ns advent.day-05.binary-search)

(defn find-seat
  [truths halves]
  (->> (interleave truths halves)
       (partition 2)
       (map #(apply * %))
       (reduce +)))

(defn seat-id
  [seats row column]
  (+ column (* seats row)))

(defn binary-search-for-seat
  [seats row-halves column-halves {:keys [row column]}]
  (let [row-seat (find-seat row row-halves)
        column-seat (find-seat column column-halves)
        seat-id (seat-id seats row-seat column-seat)]
    seat-id))

(defn half
  [value]
  (int (Math/ceil (/ value 2))))

(defn halves
  [start]
  (let [h (half start)]
    (lazy-seq (cons h (halves h)))))

(def row-halves (take 7 (halves 127)))
(def column-halves (take 3 (halves 7)))
(def seats 8)

(def find-seat-id (partial binary-search-for-seat seats row-halves column-halves))