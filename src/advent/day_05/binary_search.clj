(ns advent.day-05.binary-search
  (:require [clojure.string :as str]))

(defn find-seat
  [seats binary]
  (let [row (Integer/parseInt (str/join (take 7 binary)) 2)
        column (Integer/parseInt (str/join (drop 7 binary)) 2)]
    (+ column (* seats row))))

(def find-seat-id (partial find-seat 8))