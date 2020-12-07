(ns advent.day-07.solution
  (:use [clojure.pprint]
        [criterium.core])
  (:require [clojure.string :as str]
            [advent.day-07.parser :as parser]))

(defn exists-in-sub-bags?
  [all-bags target-bag mystery-bag]
  (loop [bags (get all-bags mystery-bag)]
    (if (empty? bags)
      false
      (let [exists? (some #(= target-bag %) bags)]
        (if exists?
          true
          (let [other-bags (reduce (fn [acc v] (concat acc (get all-bags v))) [] bags)]
            (recur other-bags)))))))

(defn total-bags
  [all-bags to-be-searched searched]
  (if (empty? to-be-searched)
    (reduce (fn [acc [_ v]] (+ acc v)) -1 searched)
    (let [current-bag (first to-be-searched)
          [bag amount] (first to-be-searched)
          bags-in-bag (get all-bags bag)
          multiplied (map #(if (number? %) (* amount %) %) bags-in-bag)
          partitioned (partition 2 multiplied)
          new-to-be-searched (concat (rest to-be-searched) partitioned)]
      (recur all-bags new-to-be-searched (conj searched current-bag)))))

(defn solve-first
  [bag bags]
  (->> (keys bags)
       (filter #(exists-in-sub-bags? bags bag %))
       count))

(defn solve-second
  [bag bags]
  (total-bags bags [[bag 1]] []))

;(def bags (->> (slurp "input.txt")
;               str/split-lines
;               (map parser/parse)
;               (reduce merge)))

;(println (solve-first :shiny-gold bags))                    ; 101
;(println (solve-second :shiny-gold bags))                   ; 108636

;(bench (solve-first :shiny-gold bags))                      ; Execution time mean : 480 ms
;(bench (solve-second :shiny-gold bags))                     ; Execution time mean : 360 µs