(ns advent.day-05.binary-search)

(defn find-seat
  [line boarding-pass]
  (loop [l line
         b boarding-pass]
    (let [step (first b)
          middle (int (Math/ceil (/ (count l) 2)))]
      (cond
        (empty? b) (first l)
        (= step :right) (recur (drop middle l) (rest b))
        (= step :left) (recur (take middle l) (rest b))))))

(defn seat-id
  [seats row column]
  (+ column (* seats row)))

(defn binary-search-for-seat
  [row-size column-size boarding-pass]
  (let [rows (range row-size)
        columns (range column-size)
        row-seat (find-seat rows (:row boarding-pass))
        column-seat (find-seat columns (:column boarding-pass))
        seat-id (seat-id column-size row-seat column-seat)]
    seat-id))

(def find-seat-id (partial binary-search-for-seat 128 8))