(ns advent.day-18.strange-math)

(defn solve-simple-equation
  ([math-seq]
   (let [numbers (filter number? math-seq)
         operations (remove number? math-seq)]
     (solve-simple-equation (rest numbers) operations (first numbers))))
  ([numbers operations result]
   (if (empty? numbers)
     result
     (let [number (first numbers)
           operation (first operations)]
       (recur (rest numbers) (rest operations) (operation result number))))))

(defn solve-equation
  ([math-seq]
   (loop [m math-seq]
     (cond
       (some #{+} m) (let [index (.indexOf m +)
                           [a b c] (subvec (vec m) (dec index) (+ index 2))
                           result (b a c)
                           head (subvec (vec m) 0 (dec index))
                           tail (subvec (vec m) (+ index 2))
                           new-math-seq (concat head [result] tail)]
                       (recur new-math-seq))
       (= (count m) 1) (first m)
       :else (reduce * (filter number? m))))))

(defn convert
  [value]
  (cond
    (= value :plus) +
    (= value :multiply) *
    :default value))

(defn solve-equation-with-parentheses
  [f math-seq]
  (let [a (.lastIndexOf math-seq :parentheses-open)
        b (.indexOf (drop a math-seq) :parentheses-close)]
    (cond
      (> a -1) (let [snippet (->> math-seq (drop (inc a)) (take (dec b)))
                     converted (map convert snippet)
                     solution (f converted)
                     equation (concat (conj (vec (take a math-seq)) solution) (drop (+ a b 1) math-seq))]
                 (recur f equation))
      :else (f (map convert math-seq)))))

(def solve-equations-left-to-right (partial solve-equation-with-parentheses solve-simple-equation))
(def solve-equations-left-to-right-multiplication-before-addition (partial solve-equation-with-parentheses solve-equation))