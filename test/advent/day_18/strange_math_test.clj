(ns advent.day-18.strange-math-test
  (:require [clojure.test :refer :all]
            [advent.day-18.parser :as parser]
            [advent.day-18.strange-math :refer :all]))

(deftest solve-simple-equation-test
  (testing "solve-simple-equation"
    (testing "should be able to solve strange math equations without any parentheses"
      (is (= 71 (solve-simple-equation [1 + 2 * 3 + 4 * 5 + 6]))))))

(deftest solve-equations-left-to-right-test
  (testing "solve-equations-left-to-right"
    (is (= 71 (solve-equations-left-to-right
                (-> "1 + 2 * 3 + 4 * 5 + 6" parser/parse convert))))
    (is (= 51 (solve-equations-left-to-right
                (-> "1 + (2 * 3) + (4 * (5 + 6))" parser/parse convert))))
    (is (= 26 (solve-equations-left-to-right
                (-> "2 * 3 + (4 * 5)" parser/parse convert))))
    (is (= 437 (solve-equations-left-to-right
                 (-> "5 + (8 * 3 + 9 + 3 * 4 * 3)" parser/parse convert))))
    (is (= 12240 (solve-equations-left-to-right
                   (-> "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))" parser/parse convert))))
    (is (= 13632 (solve-equations-left-to-right
                   (-> "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" parser/parse convert))))))

(deftest solve-equations-left-to-right-multiplication-before-addition-test
  (testing "solve-equations-left-to-right-multiplication-before-addition"
    (is (= 231 (solve-equations-left-to-right-multiplication-before-addition
                 (-> "1 + 2 * 3 + 4 * 5 + 6" parser/parse convert))))
    (is (= 51 (solve-equations-left-to-right-multiplication-before-addition
                (-> "1 + (2 * 3) + (4 * (5 + 6))" parser/parse convert))))
    (is (= 46 (solve-equations-left-to-right-multiplication-before-addition
                (-> "2 * 3 + (4 * 5)" parser/parse convert))))
    (is (= 1445 (solve-equations-left-to-right-multiplication-before-addition
                  (-> "5 + (8 * 3 + 9 + 3 * 4 * 3)" parser/parse convert))))
    (is (= 669060 (solve-equations-left-to-right-multiplication-before-addition
                    (-> "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))" parser/parse convert))))
    (is (= 23340 (solve-equations-left-to-right-multiplication-before-addition
                   (-> "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2" parser/parse convert))))))