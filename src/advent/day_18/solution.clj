(ns advent.day-18.solution
  (:use [clojure.pprint]
        [criterium.core])
  (:require [advent.day-18.parser :as parser]
            [advent.day-18.strange-math :as math]))

(defn solve
  [f equations]
  (reduce (fn [acc equation]
            (let [converted (math/convert equation)
                  solved (f converted)]
              (+ acc solved))) 0 equations))

(def solve-first (partial solve math/solve-equations-left-to-right))
(def solve-second (partial solve math/solve-equations-left-to-right-multiplication-before-addition))

;(def equations (->> "input.txt" slurp parser/parse-file))

;(println (solve-first equations))                           ; 31142189909908
;(println (solve-second equations))                          ; 323912478287549

;(bench (solve-first equations))                             ; Execution time mean : 15 ms
;(bench (solve-second equations))                            ; Execution time mean : 25 ms