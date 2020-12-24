(ns advent.day-16.parser
  (:require [clojure.string :as str]))

(defn rules-parser
  [input]
  (->> input
       str/split-lines
       (map #(rest (re-find #"^(.*): (\d+)-(\d+) or (\d+)-(\d+)$" %)))
       (map (fn [[name lmin lmax hmin hmax]]
              (let [name (as-> name $ (str/replace $ " " "-") (keyword $))
                    lmin (Integer/parseInt lmin)
                    lmax (Integer/parseInt lmax)
                    hmin (Integer/parseInt hmin)
                    hmax (Integer/parseInt hmax)]
                {:name name
                 :lmin lmin
                 :lmax lmax
                 :hmin hmin
                 :hmax hmax})))))

(defn tickets-parser
  [input]
  (->> input
       str/split-lines
       (map (fn [values]
              (let [split (str/split values #",")
                    converted (map #(Integer/parseInt %) split)]
                (vec converted))))))

(defn your-ticket
  [input]
  (as-> input $
        (str/split $ #",")
        (map #(Integer/parseInt %) $)))