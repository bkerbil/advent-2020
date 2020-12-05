(ns advent.day-05.binary-search-test
  (:require [clojure.test :refer :all]
            [advent.day-05.binary-search :refer :all]
            [advent.day-05.parser :as parser]))

(defn string->find-seat-id
  [boarding-pass]
  (->> boarding-pass
       parser/string->steps
       find-seat-id))

(deftest find-seat-id-test
  (testing "example cases"
    (is (= 567 (string->find-seat-id "BFFFBBFRRR")))
    (is (= 119 (string->find-seat-id "FFFBBBFRRR")))
    (is (= 820 (string->find-seat-id "BBFFBBFRLL")))))