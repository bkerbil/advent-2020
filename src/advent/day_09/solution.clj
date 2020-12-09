(ns advent.day-09.solution
  (:use [criterium.core])
  (:require [advent.day-09.parser :as parser]
            [advent.day-09.support :as support]
            [clojure.spec.test.alpha :as stest]))

(defn solve-first
  [amount numbers]
  (support/first-non-sum amount numbers))

(defn solve-second
  [target numbers]
  (support/first-cumulative-sum-matches target numbers))

;(stest/instrument 'advent.day-09.parser/data->numbers)
;(def numbers (parser/data->numbers (slurp "input.txt")))

;(println (solve-first 25 numbers))                          ; 556543474
;(println (solve-second 556543474 numbers))                  ; 76096372

;(bench (solve-first 25 numbers))                            ; 4 ms
;(bench (solve-second 556543474 numbers))                    ; 140 ms