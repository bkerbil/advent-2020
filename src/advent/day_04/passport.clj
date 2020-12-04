(ns advent.day-04.passport
  (:require [advent.day-04.validator :as v]
            [advent.day-04.convert :as convert]
            [clojure.spec.alpha :as s]))

(declare map->unqualified unqualified->passport)

(s/def ::byr (s/and int? v/four-digits? #(<= 1920 % 2002)))
(s/def ::iyr (s/and int? v/four-digits? #(<= 2010 % 2020)))
(s/def ::eyr (s/and int? v/four-digits? #(<= 2020 % 2030)))
(s/def ::hgt (s/or :height-cm (s/cat :height (s/and int? #(<= 150 % 193))
                                     :unit (s/and keyword? #(= % :cm)))
                   :height-in (s/cat :height (s/and int? #(<= 59 % 76))
                                     :unit (s/and keyword? #(= % :in)))))
(s/def ::hcl (s/and string? #(v/matches-regex #"^\#[a-f0-9]{6}$" %)))
(s/def ::ecl #{:amb :blu :brn :gry :grn :hzl :oth})
(s/def ::pid (s/and string? v/nine-digit-number?))
(s/def ::cid (s/nilable int?))

(s/def ::passport
  (s/keys :req [::byr ::iyr ::eyr ::hgt ::hcl ::ecl ::pid]
          :opt [::cid]))

(defn map->unqualified
  [{:keys [byr iyr eyr hgt hcl ecl pid cid]}]
  {:byr (convert/string->integer byr)
   :iyr (convert/string->integer iyr)
   :eyr (convert/string->integer eyr)
   :hgt (convert/string->height hgt)
   :hcl (convert/string->color-hex hcl)
   :ecl (convert/string->keyword ecl)
   :pid pid
   :cid (convert/string->integer cid)})

(s/def ::passport-ret ::passport)

(s/fdef unqualified->passport
        :ret ::passport-ret)

(defn unqualified->passport
  [{:keys [byr iyr eyr hgt hcl ecl pid cid]}]
  {::byr byr, ::iyr iyr, ::eyr eyr, ::hgt hgt, ::hcl hcl, ::ecl ecl, ::pid pid, ::cid cid})

(defn valid?
  [passport]
  (s/valid? ::passport passport))