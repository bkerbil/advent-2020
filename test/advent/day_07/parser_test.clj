(ns advent.day-07.parser-test
  (:require [clojure.test :refer :all]
            [advent.day-07.parser :refer :all]))

(deftest parse-test
  (testing "given string, should return a map with key as being the bag containing others; value being the bags contained"
    (is (= {:striped-beige [:dull-beige 5]}
           (parse "striped beige bags contain 5 dull beige bags.")))
    (is (= {:dark-turquoise [:dark-bronze 4 :posh-tan 3]}
           (parse "dark turquoise bags contain 4 dark bronze bags, 3 posh tan bags.")))
    (is (= {:dull-magenta [:dim-plum 5 :dark-coral 5 :mirrored-white 3 :posh-teal 3]}
           (parse "dull magenta bags contain 5 dim plum bags, 5 dark coral bags, 3 mirrored white bags, 3 posh teal bags.")))))

(deftest parse-test
  (testing "known examples"
    (is (= {:light-red [:bright-white 1 :muted-yellow 2]}
           (parse "light red bags contain 1 bright white bag, 2 muted yellow bags.")))))