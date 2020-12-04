(ns advent.day-04.unqualified
  (:require [clojure.spec.alpha :as s]))

(s/def ::byr string?)                                       ; Birth Year
(s/def ::iyr string?)                                       ; Issue Year
(s/def ::eyr string?)                                       ; Expiration Year
(s/def ::hgt string?)                                       ; Height
(s/def ::hcl string?)                                       ; Hair Color
(s/def ::ecl string?)                                       ; Eye Color
(s/def ::pid string?)                                       ; Passport ID
(s/def ::cid (s/nilable string?))                           ; OPTIONAL; Country ID

(s/def ::passport
  (s/keys :req-un [::byr ::iyr ::eyr ::hgt ::hcl ::ecl ::pid]
          :opt-un [::cid]))

(defn valid?
  [passport]
  (s/valid? ::passport passport))