(ns advent.day-04.validator)

(defmacro return-false-on-exception
  [f]
  `(try
     ~f
     (catch Exception _#
       false)))

(defn four-digits?
  [number]
  (return-false-on-exception
    (= 4 (count (str number)))))

(defn matches-regex
  [regex text]
  (return-false-on-exception
    (re-find regex text)))

(defn nine-digit-number?
  [number]
  (return-false-on-exception
    (let [_ (Integer/parseInt number)
          nine-digits? (= 9 (count number))]
      nine-digits?)))