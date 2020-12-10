(ns advent.day-10.path)

(defn same-path?
  [a b]
  (loop [a a
         b b]
    (if (or (empty? a) (empty? b))
      true
      (if (= (first a) (first b))
        (recur (rest a) (rest b))
        false))))