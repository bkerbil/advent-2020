(ns advent.day-02.solution
  (:use [criterium.core])
  (:require [support.reader :as r]))

(defn string->data
  [text]
  (rest (re-find #"^(\d+)-(\d+) ([a-z]){1}: (\w+)$" text)))

(defn normalize
  [[min max required-letter password]]
  {:min             (Integer/parseInt min)
   :max             (Integer/parseInt max)
   :required-letter (->> required-letter seq first)
   :password        password})

(defn contains-required-letters?
  [{:keys [min max required-letter password]}]
  (let [matches (->> password seq (filter #(= required-letter %)) count)]
    (>= max matches min)))

(defn get-letter
  [password position]
  (nth (seq password) (dec position)))

(defmacro xor
  [a b]
  `(not= ~a ~b))

(defn valid-letter-in-xor-positions?
  [{:keys [min max required-letter password]}]
  (let [first-letter (get-letter password min)
        second-letter (get-letter password max)
        first-letter-matches? (= first-letter required-letter)
        second-letter-matches? (= second-letter required-letter)
        result (xor first-letter-matches? second-letter-matches?)]
    result))

(defn solve
  [f passwords]
  (->> passwords
       (map string->data)
       (map normalize)
       (map f)
       (filter true?)
       count))

(def solve-first (partial solve contains-required-letters?))
(def solve-second (partial solve valid-letter-in-xor-positions?))

(def passwords (r/read-file-as-vec "input.txt"))

;(println (solve-first passwords))                           ; => 410
;(println (solve-second passwords))                          ; => 694

;(bench (solve-first passwords))                             ; Execution time mean : 3 ms
;(bench (solve-second passwords))                            ; Execution time mean : 2 ms