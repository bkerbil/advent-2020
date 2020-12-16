(ns advent.day-14.docking-program
  (:require [advent.day-14.protocols :as proto]
            [advent.day-14.bitmask :as b]))

(defrecord Docking-Program
  [memory bitmask]
  proto/Operations
  (update-bitmask [program new-bitmask]
    (assoc program :bitmask new-bitmask))
  (write-value-to-memory [program address value]
    (let [result (b/apply-bitmask bitmask value)]
      (assoc-in program [:memory address] result)))
  (initialize [_]
    (reduce + (map val memory))))

(defmulti action :action)

(defmethod action :mask
  [{:keys [value]} program]
  (proto/update-bitmask program value))

(defmethod action :mem
  [{:keys [address value]} program]
  (proto/write-value-to-memory program address value))