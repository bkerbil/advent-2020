(ns advent.day-11-version2.pre-corona-test
  (:require [clojure.test :refer :all]
            [advent.day-11-version2.protocols :refer :all]
            [advent.day-11-version2.pre-corona :refer :all]
            [advent.day-11-version2.parser :as parser]))

(def state-0 (parser/data->state "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n"))
(def state-1 (parser/data->state "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##"))
(def state-2 (parser/data->state "#.LL.L#.##\n#LLLLLL.L#\nL.L.L..L..\n#LLL.LL.L#\n#.LL.LL.LL\n#.LLLL#.##\n..L.L.....\n#LLLLLLLL#\n#.LLLLLL.L\n#.#LLLL.##"))
(def state-3 (parser/data->state "#.##.L#.##\n#L###LL.L#\nL.#.#..#..\n#L##.##.L#\n#.##.LL.LL\n#.###L#.##\n..#.#.....\n#L######L#\n#.LL###L.L\n#.#L###.##"))
(def state-4 (parser/data->state "#.#L.L#.##\n#LLL#LL.L#\nL.L.L..#..\n#LLL.##.L#\n#.LL.LL.LL\n#.LL#L#.##\n..L.L.....\n#L#LLLL#L#\n#.LLLLLL.L\n#.#L#L#.##"))
(def state-5 (parser/data->state "#.#L.L#.##\n#LLL#LL.L#\nL.#.L..#..\n#L##.##.L#\n#.#L.LL.LL\n#.#L#L#.##\n..L.L.....\n#L#L##L#L#\n#.LLLLLL.L\n#.#L#L#.##"))

(deftest neighbours-test
  (testing "neighbours"
    (testing "state-0"
      (let [chart (->Chart state-0)]
        (is (= [0 0] (neighbours chart 0 0)))
        (is (= [0 0 0 0 0] (neighbours chart 1 0)))
        (is (= [0 0 0 0] (neighbours chart 2 0)))
        (is (= [0 0 0] (neighbours chart 9 0)))
        (is (= [0 0 0] (neighbours chart 9 1)))
        (is (= [0] (neighbours chart 0 9)))
        (is (= [0 0] (neighbours chart 9 9)))
        (is (= [0 0 0 0 0 0 0 0] (neighbours chart 4 8)))
        (is (= [0 0 0 0 0] (neighbours chart 5 5)))))))

(deftest rule-test
  (testing "rule"
    (testing "empty [chair]"
      (testing "there is more than one adjacent occupied seat"
        (is (= 0 (rule {:point 0 :occupied 1})))
        (is (= 0 (rule {:point 0 :occupied 4})))
        (is (= 0 (rule {:point 0 :occupied 5})))
        (is (= 0 (rule {:point 0 :occupied 8}))))
      (testing "there is no occupied adjacent seats")
      (is (= 1 (rule {:point 0 :occupied 0}))))
    (testing "occupied [chair]"
      (testing "there is four or more adjacent occupied seats"
        (is (= 0 (rule {:point 1 :occupied 4})))
        (is (= 0 (rule {:point 1 :occupied 5})))
        (is (= 0 (rule {:point 1 :occupied 7})))
        (is (= 0 (rule {:point 1 :occupied 8}))))
      (testing "there is less than four adjacent occupied seats"
        (is (= 1 (rule {:point 1 :occupied 0})))
        (is (= 1 (rule {:point 1 :occupied 1})))
        (is (= 1 (rule {:point 1 :occupied 2})))
        (is (= 1 (rule {:point 1 :occupied 3})))))
    (testing "floor"
      (is (nil? (rule {:point nil}))))))

(deftest update-chart-test
  (testing "update-chart"
    (testing "should move from one example state to another"
      (is (= (->Chart state-1) (update-chart (->Chart state-0))))
      (is (= (->Chart state-2) (update-chart (->Chart state-1))))
      (is (= (->Chart state-3) (update-chart (->Chart state-2))))
      (is (= (->Chart state-4) (update-chart (->Chart state-3))))
      (is (= (->Chart state-5) (update-chart (->Chart state-4)))))
    (testing "should stabilize on last state"
      (is (= (->Chart state-5) (update-chart (->Chart state-5)))))))

(deftest final-state-test
  (testing "final-state"
    (testing "should stabilize on final state"
      (is (= (->Chart state-5) (final-state (->Chart state-0)))))))