(ns boot-code.data_test
  (:require [clojure.test :refer :all]
            [boot-code.data :refer :all :as data]
            [clojure.spec.test.alpha :as stest]
            [clojure.spec.alpha :as s])
  (:import (boot_code.data Instruction)))

(stest/instrument `string->instruction)

(deftest string->polarity-test
  (testing "string->polarity /"
    (testing "given plus, should return 1"
      (is (= 1 (string->operator "+"))))
    (testing "given minus, should return -1"
      (is (= -1 (string->operator "-"))))))

(deftest string->instruction-test
  (testing "string->instruction /"
    (testing "calling with valid input should return Instruction (record)"
      (is (= (Instruction. :acc 4) (string->instruction "acc +4")))
      (is (= (Instruction. :acc -20) (string->instruction "acc -20"))))
    (testing "should work with values from input"
      (is (= (Instruction. :acc 37) (string->instruction "acc +37")))
      (is (= (Instruction. :acc -4) (string->instruction "acc -4")))
      (is (= (Instruction. :nop 405) (string->instruction "nop +405")))
      (is (= (Instruction. :nop -53) (string->instruction "nop -53")))
      (is (= (Instruction. :jmp 276) (string->instruction "jmp +276")))
      (is (= (Instruction. :jmp -25) (string->instruction "jmp -25"))))
    (testing "is validated by spec"
      (is (true? (s/valid? ::data/instruction (string->instruction "acc +37"))))
      (is (true? (s/valid? ::data/instruction (string->instruction "acc -4"))))
      (is (true? (s/valid? ::data/instruction (string->instruction "nop +405"))))
      (is (true? (s/valid? ::data/instruction (string->instruction "nop -53"))))
      (is (true? (s/valid? ::data/instruction (string->instruction "jmp +276"))))
      (is (true? (s/valid? ::data/instruction (string->instruction "jmp -25")))))
    (testing "spec validation throws exception with invalid values?"
      (is (thrown? NumberFormatException (s/valid? ::data/instruction (string->instruction "acc +34.01"))))
      (is (thrown? AssertionError (s/valid? ::data/instruction (string->instruction "xyz +123")))))))