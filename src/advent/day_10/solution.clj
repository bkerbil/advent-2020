(ns advent.day-10.solution
  (:use [criterium.core])
  (:require [advent.day-10.parser :as parser]
            [advent.day-10.path :as path]))

(defn solve-first
  [numbers]
  (->> numbers
       sort
       (partition 2 1)
       (map (fn [[a b]] (- b a)))
       (frequencies)
       (#(* (% 1) (% 3)))))

(defn solve-second
  [numbers]
  (->> numbers
       sort
       (partition 4 1)
       (map path/add-within-reach)
       (reduce path/to-multiplications {:tails [], :multiplications []})
       path/finalize-tails
       (:multiplications)
       (reduce *)))

;(def numbers (->> "input.txt" slurp parser/data->numbers (apply conj [0 157])))

;(println (solve-first numbers))                             ; 1984
;(println (solve-second numbers))                            ; 3543369523456

;(bench (solve-first numbers))                               ; Execution time mean : 70 µs
;(bench (solve-second numbers))                              ; Execution time mean : 570 µs