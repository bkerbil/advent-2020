(ns advent.day-18.parser
  (:require [clojure.string :as str]))

(defn string->seq
  [text]
  (str/split text #" "))

(defn string->number
  [s]
  (try
    (Integer/parseInt s)
    (catch Exception _
      s)))

(defn string->parentheses
  [s]
  (cond
    (= "(" s) :parentheses-open
    (= ")" s) :parentheses-close
    :default s))

(defn string->operator
  [s]
  (cond
    (= "+" s) :plus
    (= "*" s) :multiply
    :number s))

(defn add-space-to-parentheses
  [s]
  (-> s
      (str/replace "(" " ( ")
      (str/replace ")" " ) ")))

(defn parse
  [s]
  (->> s
       add-space-to-parentheses
       string->seq
       (remove empty?)
       (map string->number)
       (map string->operator)
       (map string->parentheses)))

(defn parse-file
  [s]
  (->> s
       (str/split-lines)
       (map parse)))