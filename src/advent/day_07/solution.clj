(ns advent.day-07.solution
  (:use [clojure.pprint]
        [criterium.core])
  (:require [clojure.string :as str]
            [clojure.set :as set]
            [advent.day-07.parser :as parser]))

(defn contains-bag?
  ([bags from to]
   (contains-bag? bags to (conj #{} from) #{}))
  ([bags target queue seen]
   (cond
     (empty? queue) false
     (some #(= target %) (get bags (first queue))) true
     :else (let [current (first queue)
                 bag (get bags current)
                 updated-seen (conj seen current)
                 updated-queue (set/union queue bag)
                 next-queue (set/difference updated-queue updated-seen)]
             (recur bags target next-queue updated-seen)))))

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
       (filter #(contains-bag? bags % bag))
       count))

(defn solve-second
  [bag bags]
  (total-bags bags [[bag 1]] []))

;(def bags (->> (slurp "input.txt")
;               str/split-lines
;               (map parser/parse)
;               (reduce merge)))

;(def bags-simpler (->> bags
;                       (map (fn [[k v]] (hash-map k (->> v (remove number?) set))))
;                       (reduce merge)))

;(println (solve-first :shiny-gold bags-simpler))            ; 101
;(println (solve-second :shiny-gold bags))                   ; 108636

;(bench (solve-first :shiny-gold bags-simpler))              ; Execution time mean : 45 ms
;(bench (solve-second :shiny-gold bags))                     ; Execution time mean : 360 µs