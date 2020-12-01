(ns support.reader
  (:require [clojure.java.io :as io]))

(defn read-file-as-vec
  [filename]
  (with-open [rdr (io/reader filename)]
    (->> rdr
         line-seq
         (into []))))

(defn string->integer
  [value]
  (Integer/parseInt value))