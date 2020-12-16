(ns advent.day-14.protocols)

(defprotocol Operations
  (update-bitmask [this bitmask])
  (write-value-to-memory [this address value])
  (initialize [this]))