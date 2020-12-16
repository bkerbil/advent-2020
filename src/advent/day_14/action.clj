(ns advent.day-14.action
  (:require [advent.day-14.protocols :as proto]))

(defmulti action :action)

(defmethod action :mask
  [{:keys [value]} program]
  (proto/update-bitmask program value))

(defmethod action :mem
  [{:keys [address value]} program]
  (proto/write-value-to-memory program address value))