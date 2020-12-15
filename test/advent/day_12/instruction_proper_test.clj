(ns advent.day-12.instruction-proper-test
  (:require [clojure.test :refer :all]
            [advent.day-12.protocols :as proto]
            [advent.day-12.instruction-proper :refer [->Instruction-Proper]]))

(deftest instruction-proper-test
  (testing "known examples"
    (let [state-0 (->Instruction-Proper 0 0 10 1 :east)
          state-1 (->Instruction-Proper 100 10 10 1 :east)
          state-2 (->Instruction-Proper 100 10 10 4 :east)
          state-3 (->Instruction-Proper 170 38 10 4 :east)
          state-4 (->Instruction-Proper 170 38 4 -10 :east)
          state-5 (->Instruction-Proper 214 -72 4 -10 :east)]
      (is (= state-1 (proto/forward state-0 10)))
      (is (= state-2 (proto/north state-1 3)))
      (is (= state-3 (proto/forward state-2 7)))
      (is (= state-4 (proto/right state-3 90)))
      (is (= state-5 (proto/forward state-4 11)))))
  (testing "input"
    (let [state-0 (->Instruction-Proper 0 0 10 1 :east)
          state-1 (->Instruction-Proper 410 41 10 1 :east)
          state-2 (->Instruction-Proper 410 41 10 3 :east)
          state-3 (->Instruction-Proper 410 41 3 -10 :east)]
      (is (= state-1 (proto/forward state-0 41)))
      (is (= state-2 (proto/north state-1 2)))
      (is (= state-3 (proto/left state-2 270))))))