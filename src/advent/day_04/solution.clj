(ns advent.day-04.solution
  (:use [clojure.pprint]
        [criterium.core])
  (:require [advent.day-04.parser :as parser]
            [advent.day-04.unqualified :as unqualified]
            [advent.day-04.passport :as passport]))

(defn solve-first
  [passports]
  (->> passports
       parser/parse-data
       (filter unqualified/valid?)
       count))

(defn solve-second
  [passports]
  (->> passports
       parser/parse-data
       (map passport/map->unqualified)
       (map passport/unqualified->passport)
       (filter passport/valid?)
       count))

;(def passports (slurp "input.txt"))
;(println (solve-first passports))                           ; 206
;(println (solve-second passports))                          ; 123

;(bench (solve-first passports))                             ; Execution time mean : 2 ms
;(bench (solve-second passports))                            ; Execution time mean : 4 ms