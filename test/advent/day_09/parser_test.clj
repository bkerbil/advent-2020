(ns advent.day-09.parser-test
  (:require [clojure.test :refer :all]
            [advent.day-09.parser :refer :all]))

(deftest data->numbers-test
  (testing "should split input by newlines and transform strings into numbers"
    (is (= [1 2 3] (data->numbers "1\n2\n3")))
    (is (= [4294967294] (data->numbers "4294967294")))
    (is (= [4294967294 2147483647000] (data->numbers "4294967294\n2147483647000")))))