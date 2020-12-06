(ns advent.day-06.solution
  (:use [criterium.core])
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn solve
  [f input]
  (->> (str/split input #"\n\n")
       (map #(map set (str/split-lines %)))
       (map #(apply f %))
       (map count)
       (reduce +)))

(def solve-first (partial solve set/union))
(def solve-second (partial solve set/intersection))

;(def input (slurp "input.txt"))

;(println (solve-first input))                               ; 6748
;(println (solve-second input))                              ; 3445

;(bench (solve-first input))                                 ; Execution time mean : 4 ms
;(bench (solve-second input))                                ; Execution time mean : 5 ms