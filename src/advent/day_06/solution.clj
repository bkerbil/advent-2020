(ns advent.day-06.solution
  (:use [clojure.pprint]
        [criterium.core])
  (:require [clojure.string :as str]
            [clojure.set :as set]))

(defn solve-first
  [input]
  (->> (str/split input #"\n\n")
       (map #(str/replace % "\n" ""))
       (map set)
       (map count)
       (reduce +)))

(defn solve-second
  [input]
  (->> (str/split input #"\n\n")
       (map (fn [v]
              (let [items (str/split-lines v)
                    into-set (map #(set %) items)
                    intersection (reduce (fn [acc value] (set/intersection acc value)) into-set)]
                intersection)))
       (map count)
       (reduce +)))

;(def input (slurp "input.txt"))

;(println (solve-first input))                               ; 6748
;(println (solve-second input))                              ; 3445

;(bench (solve-first input))                                 ; Execution time mean : 2 ms
;(bench (solve-second input))                                ; Execution time mean : 4 ms