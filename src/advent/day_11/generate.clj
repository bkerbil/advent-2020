(ns advent.day-11.generate)

(defn opposite
  [value]
  (* -1 value))

(defn abs
  [^long value]
  (Math/abs value))

(defn generate-x-axis
  [width]
  (let [start (opposite width)
        end (inc width)
        step 1]
    (for [x (range start end step)]
      [x 0])))

(defn generate-y-axis
  [height]
  (let [start (opposite height)
        end (inc height)
        step 1]
    (for [y (range start end step)]
      [0 y])))

(defn generate-diagonal
  [width height]
  (let [start-x (opposite width)
        end-x (inc width)
        start-y (opposite height)
        end-y (inc height)
        step 1]
    (for [y (range start-y end-y step)
          x (range start-x end-x step)
          :when (or (= (abs x) (abs y)))]
      [x y])))

(defn generate-line-of-sight
  [width height]
  (let [x-axis (generate-x-axis width)
        y-axis (generate-y-axis height)
        diagonal (generate-diagonal width height)
        result (->> (concat x-axis y-axis diagonal) distinct (remove #{[0 0]}))]
    result))