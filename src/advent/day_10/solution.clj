(ns advent.day-10.solution
  (:use [criterium.core])
  (:require [advent.day-10.parser :as parser]
            [advent.day-10.path :as path]
            [clojure.set :as set]))

(defn solve-first
  [numbers]
  (->> numbers
       (partition 2 1)
       (map (fn [[a b]] (- b a)))
       (frequencies)
       (#(* (% 1) (% 3)))))

(defn available
  [start numbers]
  (vec (filter #(<= (- % start) 3) numbers)))

(defn data->graph
  [numbers]
  (let [sorted-numbers (sort numbers)
        results (reduce (fn [{:keys [numbers] :as acc} number]
                          (let [available (available (first numbers) (rest numbers))]
                            (-> acc
                                (assoc :current number number available)
                                (update :numbers rest))))
                        {:current 0, :numbers sorted-numbers} sorted-numbers)
        keys-removed (-> results (dissoc :numbers :current))
        sorted (into (sorted-map) keys-removed)]
    sorted))

(defn graph->multiple-paths
  [graph]
  (into (sorted-map)
        (filter (fn [[_ nodes]]
                  (> (count nodes) 1)) graph)))

(defn concat-tail
  [start path]
  (set (mapcat (fn [v]
                 (let [tail (last v)
                       nodes (get path tail nil)
                       connections (map #(conj v %) nodes)]
                   connections)) start)))

(defn remove-same-paths
  [items]
  (let [sorted (sort-by #(count %) > items)]
    (loop [i sorted
           results []]
      (if (empty? i)
        results
        (let [head (first i)
              tail (rest i)
              temp (remove #(path/same-path? % head) tail)]
          (recur temp (conj results head)))))))

(defn add-bag-to-results
  [bag results]
  (let [paths (->> bag
                   (map (fn [x] (vec (sort x))))
                   remove-same-paths
                   count)]
    (conj results paths)))

(defn vec->set-of-vectors
  [v]
  (let [result (into #{} (map (fn [x] [x])) v)]
    result))

(defn paths-count
  [bag]
  (->> bag
       (map (fn [x] (vec (sort x))))
       (into #{})
       (remove-same-paths)
       count))

(defn solve-second
  ([numbers]
   (let [graph (data->graph numbers)
         paths (graph->multiple-paths graph)
         [start _] (first paths)
         bag #{[start]}
         results []]
     (solve-second paths bag results)))
  ([paths bag results]
   (cond
     (empty? paths) (reduce * (add-bag-to-results bag results))
     :else (let [item (first paths)
                 [k v] item
                 connected (concat-tail bag paths)]
             (if (empty? connected)
               (recur (dissoc paths k) (vec->set-of-vectors v) (conj results (paths-count bag)))
               (recur (dissoc paths k) (set/union bag connected) results))))))

;(def numbers (->> "input.txt" slurp parser/data->numbers (apply conj [0 157]) sort))

;(println (solve-first numbers))                             ; 1984
;(println (solve-second numbers))                            ; 3543369523456

;(bench (solve-first numbers))                               ; Execution time mean : 61 µs
;(bench (solve-second numbers))                              ; Execution time mean : 1 ms