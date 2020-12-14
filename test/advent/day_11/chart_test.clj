(ns advent.day-11.chart_test
  (:require [clojure.test :refer :all]
            [advent.day-11.parser :as parser]
            [advent.day-11.chart :refer :all]
            [advent.day-11.vision :refer :all]
            [advent.day-11.rule :refer :all]))

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

(deftest update-chart-test
  (testing "update-chart"
    (let [coordinates (for [y (range 10)
                            x (range 10)]
                        [x y])]
      (testing "should move from one example state to another"
        (is (= chart-1 ((update-chart-fn chart-0 neighbours-adjacent-fn pre-corona) coordinates (transient []))))
        (is (= chart-2 ((update-chart-fn chart-1 neighbours-adjacent-fn pre-corona) coordinates (transient []))))
        (is (= chart-3 ((update-chart-fn chart-2 neighbours-adjacent-fn pre-corona) coordinates (transient []))))
        (is (= chart-4 ((update-chart-fn chart-3 neighbours-adjacent-fn pre-corona) coordinates (transient []))))
        (is (= chart-5 ((update-chart-fn chart-4 neighbours-adjacent-fn pre-corona) coordinates (transient [])))))
      (testing "post-corona"
        (is (= chart-1-post-corona ((update-chart-fn chart-0-post-corona neighbours-line-of-sight-fn post-corona) coordinates (transient []))))
        (is (= chart-2-post-corona ((update-chart-fn chart-1-post-corona neighbours-line-of-sight-fn post-corona) coordinates (transient []))))
        (is (= chart-3-post-corona ((update-chart-fn chart-2-post-corona neighbours-line-of-sight-fn post-corona) coordinates (transient []))))
        (is (= chart-4-post-corona ((update-chart-fn chart-3-post-corona neighbours-line-of-sight-fn post-corona) coordinates (transient []))))
        (is (= chart-5-post-corona ((update-chart-fn chart-4-post-corona neighbours-line-of-sight-fn post-corona) coordinates (transient []))))
        (is (= chart-6-post-corona ((update-chart-fn chart-5-post-corona neighbours-line-of-sight-fn post-corona) coordinates (transient []))))))))

(deftest stabilize-test
  (testing "stabilize"
    (testing "should stabilize on final state"
      (is (= chart-5 ((stabilize-fn chart-0 neighbours-adjacent-fn pre-corona) chart-0)))
      (is (= chart-6-post-corona ((stabilize-fn chart-0-post-corona neighbours-line-of-sight-fn post-corona) chart-0-post-corona))))))