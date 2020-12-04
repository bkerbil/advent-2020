(ns advent.day-04.convert)

(defmacro return-nil-on-exception
  [f]
  `(try
     ~f
     (catch Exception _#
       nil)))

(defn string->integer
  [text]
  (return-nil-on-exception
    (Integer/parseInt text)))

(defn string->height
  [text]
  (return-nil-on-exception
    (let [found (rest (re-find #"^(\d+)(cm|in)$" text))
          number (Integer/parseInt (first found))
          unit (keyword (second found))]
      [number unit])))

(defn string->color-hex
  [text]
  (return-nil-on-exception
    (first (rest (re-find #"^(\#[a-f0-9]{6})$" text)))))

(defn string->keyword
  [text]
  (return-nil-on-exception
    (keyword text)))