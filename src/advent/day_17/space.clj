(ns advent.day-17.space)

(defn increase-space
  [value repetitions]
  (+ value (* repetitions 2)))

(defn width
  [space]
  (count (first (first space))))

(defn height
  [space]
  (count (first space)))

(defn depth
  [space]
  (count space))

(defn init
  [width height depth repetitions]
  (let [final-width (increase-space width repetitions)
        final-height (increase-space height repetitions)
        final-depth (increase-space depth repetitions)
        row (vec (take final-width (repeat 0)))
        space-2d (vec (take final-height (repeat row)))
        space-3d (vec (take final-depth (repeat space-2d)))]
    space-3d))

(defn seed-space
  [space seed]
  (let [space-width (count (first (first space)))
        space-height (count (first space))
        space-middle (int (Math/floor (/ (count space) 2)))
        seed-width (count (first (first seed)))
        seed-height (count (first seed))
        dx (int (Math/floor (/ (- space-width seed-width) 2)))
        dy (int (Math/floor (/ (- space-height seed-height) 2)))
        seed-coordinates (for [y (range seed-height) x (range seed-width)] [x y])
        space-coordinates (map (fn [[x y]] [(+ x dx) (+ y dy) (get-in seed [0 y x] 0)]) seed-coordinates)
        updated-space (reduce (fn [acc [x y value]] (assoc-in acc [space-middle y x] value)) space space-coordinates)]
    updated-space))