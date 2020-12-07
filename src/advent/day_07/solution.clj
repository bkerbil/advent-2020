(ns advent.day-07.solution
  (:use [criterium.core])
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
                 updated-queue (set/union queue bag)
                 updated-seen (conj seen current)
                 next-queue (set/difference updated-queue updated-seen)]
             (recur bags target next-queue updated-seen)))))

(defn total-bags
  [all-bags to-be-searched searched]
  (if (empty? to-be-searched)
    (reduce (fn [acc [_ v]] (+ acc v)) -1 searched)
    (let [current (first to-be-searched)
          [bag amount] current
          bags-in-bag (->> (get all-bags bag) vec flatten)
          multiplied (map #(if (number? %) (* amount %) %) bags-in-bag)
          partitioned (partition 2 multiplied)
          next-to-be-searched (concat (rest to-be-searched) partitioned)
          updated-searched (conj searched current)]
      (recur all-bags next-to-be-searched updated-searched))))

(defn solve-first
  [bag bags]
  (->> (keys bags)
       (filter #(contains-bag? bags % bag))
       count))

(defn solve-second
  [bag bags]
  (total-bags bags [[bag 1]] []))

;(def bags-with-map (->> (slurp "input.txt")
;                        str/split-lines
;                        (map parser/string->bag-with-map)
;                        (reduce merge)))

;(def bags-with-set (->> (slurp "input.txt")
;                        str/split-lines
;                        (map parser/string->bags-with-set)
;                        (reduce merge)))

;(println (solve-first :shiny-gold bags-with-set))           ; 101
;(println (solve-second :shiny-gold bags-with-map))          ; 108636

;(bench (solve-first :shiny-gold bags-with-set))             ; Execution time mean : 45 ms
;(bench (solve-second :shiny-gold bags-with-map))            ; Execution time mean : 640 µs