(ns advent.day-11-version2.chart_test
  (:require [clojure.test :refer :all]
            [advent.day-11-version2.protocols :refer :all]
            [advent.day-11-version2.chart :refer :all]
            [advent.day-11-version2.parser :as parser]
            [advent.day-11-version2.visualizer :as visualizer]))

(def chart-0 (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL\n"))
(def chart-1 (parser/data->chart "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##"))
(def chart-2 (parser/data->chart "#.LL.L#.##\n#LLLLLL.L#\nL.L.L..L..\n#LLL.LL.L#\n#.LL.LL.LL\n#.LLLL#.##\n..L.L.....\n#LLLLLLLL#\n#.LLLLLL.L\n#.#LLLL.##"))
(def chart-3 (parser/data->chart "#.##.L#.##\n#L###LL.L#\nL.#.#..#..\n#L##.##.L#\n#.##.LL.LL\n#.###L#.##\n..#.#.....\n#L######L#\n#.LL###L.L\n#.#L###.##"))
(def chart-4 (parser/data->chart "#.#L.L#.##\n#LLL#LL.L#\nL.L.L..#..\n#LLL.##.L#\n#.LL.LL.LL\n#.LL#L#.##\n..L.L.....\n#L#LLLL#L#\n#.LLLLLL.L\n#.#L#L#.##"))
(def chart-5 (parser/data->chart "#.#L.L#.##\n#LLL#LL.L#\nL.#.L..#..\n#L##.##.L#\n#.#L.LL.LL\n#.#L#L#.##\n..L.L.....\n#L#L##L#L#\n#.LLLLLL.L\n#.#L#L#.##"))

(def chart-0-post-corona (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL"))
(def chart-1-post-corona (parser/data->chart "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##"))
(def chart-2-post-corona (parser/data->chart "#.LL.LL.L#\n#LLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLL#\n#.LLLLLL.L\n#.LLLLL.L#"))


(def chart-3-post-corona (parser/data->chart "#.L#.##.L#\n#L#####.LL\nL.#.#..#..\n##L#.##.##\n#.##.#L.##\n#.#####.#L\n..#.#.....\nLLL####LL#\n#.L#####.L\n#.L####.L#"))
(def chart-4-post-corona (parser/data->chart "#.L#.L#.L#\n#LLLLLL.LL\nL.L.L..#..\n##LL.LL.L#\nL.LL.LL.L#\n#.LLLLL.LL\n..L.L.....\nLLLLLLLLL#\n#.LLLLL#.L\n#.L#LL#.L#"))
(def chart-5-post-corona (parser/data->chart "#.L#.L#.L#\n#LLLLLL.LL\nL.L.L..#..\n##L#.#L.L#\nL.L#.#L.L#\n#.L####.LL\n..#.#.....\nLLL###LLL#\n#.LLLLL#.L\n#.L#LL#.L#"))
(def chart-6-post-corona (parser/data->chart "#.L#.L#.L#\n#LLLLLL.LL\nL.L.L..#..\n##L#.#L.L#\nL.L#.LL.L#\n#.LLLL#.LL\n..#.L.....\nLLL###LLL#\n#.LLLLL#.L\n#.L#LL#.L#"))



(deftest neighbours-test
  (let [directions [[0 -1] [1 -1] [1 0] [1 1] [0 1] [-1 1] [-1 0] [-1 -1]]]
    (testing "neighbours-adjacent-fn"
      (testing "state-0"
        (is (= [0 0] (neighbours-adjacent-fn chart-0 0 0 directions)))
        (is (= [0 0 0 0 0] (neighbours-adjacent-fn chart-0 1 0 directions)))
        (is (= [0 0 0 0] (neighbours-adjacent-fn chart-0 2 0 directions)))
        (is (= [0 0 0] (neighbours-adjacent-fn chart-0 9 0 directions)))
        (is (= [0 0 0] (neighbours-adjacent-fn chart-0 9 1 directions)))
        (is (= [0] (neighbours-adjacent-fn chart-0 0 9 directions)))
        (is (= [0 0] (neighbours-adjacent-fn chart-0 9 9 directions)))
        (is (= [0 0 0 0 0 0 0 0] (neighbours-adjacent-fn chart-0 4 8 directions)))
        (is (= [0 0 0 0 0] (neighbours-adjacent-fn chart-0 5 5 directions)))))

    (testing "neighbours-line-of-sight-fn"
      (testing "state-0"
        (is (= [0 0 0 0 0 0] (neighbours-line-of-sight-fn chart-0-post-corona 7 2 directions)))))))

(deftest rule-test
  (testing "rule"
    (testing "empty [chair]"
      (testing "there is more than one adjacent occupied seat"
        (is (= 0 (rule-pre-corona {:point 0 :occupied 1})))
        (is (= 0 (rule-pre-corona {:point 0 :occupied 4})))
        (is (= 0 (rule-pre-corona {:point 0 :occupied 5})))
        (is (= 0 (rule-pre-corona {:point 0 :occupied 8}))))
      (testing "there is no occupied adjacent seats")
      (is (= 1 (rule-pre-corona {:point 0 :occupied 0}))))
    (testing "occupied [chair]"
      (testing "there is four or more adjacent occupied seats"
        (is (= 0 (rule-pre-corona {:point 1 :occupied 4})))
        (is (= 0 (rule-pre-corona {:point 1 :occupied 5})))
        (is (= 0 (rule-pre-corona {:point 1 :occupied 7})))
        (is (= 0 (rule-pre-corona {:point 1 :occupied 8}))))
      (testing "there is less than four adjacent occupied seats"
        (is (= 1 (rule-pre-corona {:point 1 :occupied 0})))
        (is (= 1 (rule-pre-corona {:point 1 :occupied 1})))
        (is (= 1 (rule-pre-corona {:point 1 :occupied 2})))
        (is (= 1 (rule-pre-corona {:point 1 :occupied 3})))))
    (testing "floor"
      (is (nil? (rule-pre-corona {:point nil}))))))


(deftest update-chart-test
  (testing "update-chart"
    (let [coordinates (for [y (range 10)
                            x (range 10)]
                        [x y])]
      (testing "should move from one example state to another"
        (is (= chart-1 (update-chart-fn chart-0 coordinates neighbours-adjacent-fn rule-pre-corona)))
        (is (= chart-2 (update-chart-fn chart-1 coordinates neighbours-adjacent-fn rule-pre-corona)))
        (is (= chart-3 (update-chart-fn chart-2 coordinates neighbours-adjacent-fn rule-pre-corona)))
        (is (= chart-4 (update-chart-fn chart-3 coordinates neighbours-adjacent-fn rule-pre-corona)))
        (is (= chart-5 (update-chart-fn chart-4 coordinates neighbours-adjacent-fn rule-pre-corona))))

      (testing "post-corona"
        (is (= chart-1-post-corona (update-chart-fn chart-0-post-corona coordinates neighbours-line-of-sight-fn rule-post-corona)))
        (is (= chart-2-post-corona (update-chart-fn chart-1-post-corona coordinates neighbours-line-of-sight-fn rule-post-corona)))
        (is (= chart-3-post-corona (update-chart-fn chart-2-post-corona coordinates neighbours-line-of-sight-fn rule-post-corona)))
        (is (= chart-4-post-corona (update-chart-fn chart-3-post-corona coordinates neighbours-line-of-sight-fn rule-post-corona)))
        (is (= chart-5-post-corona (update-chart-fn chart-4-post-corona coordinates neighbours-line-of-sight-fn rule-post-corona)))
        (is (= chart-6-post-corona (update-chart-fn chart-5-post-corona coordinates neighbours-line-of-sight-fn rule-post-corona)))))))

(deftest stabilize-test
  (testing "stabilize"
    (testing "should stabilize on final state"
      (is (= chart-5 (stabilize-fn chart-0 neighbours-adjacent-fn rule-pre-corona)))
      (is (= chart-6-post-corona (stabilize-fn chart-0-post-corona neighbours-line-of-sight-fn rule-post-corona))))))