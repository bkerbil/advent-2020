(ns advent.day-04.parser)

(defn split-by
  [regex s]
  (clojure.string/split s regex))

(defn line->map
  [text]
  (let [regex #"(\S+):(\S+)"
        pairs (re-seq regex text)
        into-map (map (fn [[_ k v]] (hash-map (keyword k) v)) pairs)
        merged (reduce merge into-map)]
    merged))

(defn parse-data
  [passports]
  (->> passports
       (split-by #"\n\n")
       (map line->map)))