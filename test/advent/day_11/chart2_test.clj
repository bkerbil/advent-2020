(ns advent.day-11.chart2-test
  (:require [clojure.test :refer :all]
            [advent.day-11.chart2 :refer :all]
            [advent.day-11.parser :as parser]
            [advent.day-11.rule :as rule]
            [advent.day-11.generate :as generate]))

(deftest direction-test
  (testing "direction /"
    (testing "if x does not change, but y is greater, then return :north"
      (is (= :north (direction 0 0 0 1))))
    (testing "if x is greater, and y is greater, then return :north-east"
      (is (= :north-east (direction 0 0 1 1))))
    (testing "if x is greater, but y does not change, then return :east"
      (is (= :east (direction 0 0 1 0))))
    (testing "if x is greater, but y is less, then return :south-east"
      (is (= :south-east (direction 0 0 1 -1))))
    (testing "if x does not change, but y is less, then return :south"
      (is (= :south (direction 0 0 0 -1))))
    (testing "if x is less, and y is less, then return :south-west"
      (is (= :south-west (direction 0 0 -1 -1))))
    (testing "if x is less, but y is same, then return :west"
      (is (= :west (direction 0 0 -1 0))))
    (testing "if x is less, but y is greater, then return :north-west"
      (is (= :north-west (direction 0 0 -1 1))))
    (testing "if x and y are equal to origin, then return :origin"
      (is (= :origin (direction 0 0 0 0))))))

(deftest neighbours-adjacent-test
  (testing "line of sight should return correct amounts / "
    (let [coordinates (generate/generate-line-of-sight 3 3)]
      (testing "one empty chair"
        (testing "north"
          (is (= [0] (neighbours-adjacent [nil 0 nil nil nil nil nil nil nil] [[0 -1]] 1 1 3 3)))
          (is (= [0] (neighbours-adjacent [nil 0 nil nil nil nil nil nil nil] coordinates 1 1 3 3))))
        (testing "north-east"
          (is (= [0] (neighbours-adjacent [nil nil 0 nil nil nil nil nil nil] [[1 -1]] 1 1 3 3)))
          (is (= [0] (neighbours-adjacent [nil nil 0 nil nil nil nil nil nil] coordinates 1 1 3 3))))
        (testing "east"
          (is (= [0] (neighbours-adjacent [nil nil nil nil nil 0 nil nil nil] [[1 0]] 1 1 3 3))))
        (testing "south-east"
          (is (= [0] (neighbours-adjacent [nil nil nil nil nil nil nil nil 0] [[1 1]] 1 1 3 3))))
        (testing "south"
          (is (= [0] (neighbours-adjacent [nil nil nil nil nil nil nil 0 nil] [[0 1]] 1 1 3 3))))
        (testing "south-west"
          (is (= [0] (neighbours-adjacent [nil nil nil nil nil nil 0 nil nil] [[-1 1]] 1 1 3 3))))
        (testing "west"
          (is (= [0] (neighbours-adjacent [nil nil nil 0 nil nil nil nil nil] [[-1 0]] 1 1 3 3))))
        (testing "north-west"
          (is (= [0] (neighbours-adjacent [0 nil nil nil nil nil nil nil nil] [[-1 -1]] 1 1 3 3)))))
      (testing "one occupied chair"
        (testing "north"
          (is (= [1] (neighbours-adjacent [nil 1 nil nil nil nil nil nil nil] [[0 -1]] 1 1 3 3)))
          (is (= [1] (neighbours-adjacent [nil 1 nil nil nil nil nil nil nil] coordinates 1 1 3 3))))
        (testing "north-east"
          (is (= [1] (neighbours-adjacent [nil nil 1 nil nil nil nil nil nil] [[1 -1]] 1 1 3 3))))
        (testing "east"
          (is (= [1] (neighbours-adjacent [nil nil nil nil nil 1 nil nil nil] [[1 0]] 1 1 3 3))))
        (testing "south-east"
          (is (= [1] (neighbours-adjacent [nil nil nil nil nil nil nil nil 1] [[1 1]] 1 1 3 3))))
        (testing "south"
          (is (= [1] (neighbours-adjacent [nil nil nil nil nil nil nil 1 nil] [[0 1]] 1 1 3 3))))
        (testing "south-west"
          (is (= [1] (neighbours-adjacent [nil nil nil nil nil nil 1 nil nil] [[-1 1]] 1 1 3 3))))
        (testing "west"
          (is (= [1] (neighbours-adjacent [nil nil nil 1 nil nil nil nil nil] [[-1 0]] 1 1 3 3))))
        (testing "north-west"
          (is (= [1] (neighbours-adjacent [1 nil nil nil nil nil nil nil nil] [[-1 -1]] 1 1 3 3)))))
      (testing "corner case"
        (let [chart [0 0 nil 0 1
                     0 0 nil 0 0
                     0 nil 0 nil nil
                     0 0 nil 0 0
                     0 0 0 0 0]
              a 2
              b 2
              width 5
              height 5
              coordinates (generate/generate-line-of-sight 3 3)]
          (testing "north"
            (is (= [] (neighbours-adjacent chart [[0 -1] [0 -2] [0 -3]] a b width height))))
          (testing "north-east"
            (is (= [0] (neighbours-adjacent chart [[1 -1] [2 -2] [3 -3]] a b width height))))
          (testing "east"
            (is (= [] (neighbours-adjacent chart [[1 0] [2 0] [3 0]] a b width height))))
          (testing "south-east"
            (is (= [0] (neighbours-adjacent chart [[1 1] [2 2] [3 3]] a b width height))))
          (testing "south"
            (is (= [0] (neighbours-adjacent chart [[0 1] [0 2] [0 3]] a b width height))))
          (testing "south-west"
            (is (= [0] (neighbours-adjacent chart [[-1 1] [-2 2] [-3 3]] a b width height))))
          (testing "west"
            (is (= [0] (neighbours-adjacent chart [[-1 0] [-2 0] [-3 0]] a b width height))))
          (testing "north-west"
            (is (= [0] (neighbours-adjacent chart [[-1 -1] [-2 -2] [-3 -3]] a b width height))))
          (testing "coordinates"
            (is (= [0 0 0 0 0 0] (neighbours-adjacent chart coordinates a b width height)))))))))

(deftest update-chart-test
  (testing "update-chart /"
    (let [state-0 (parser/data->chart "L.LL.LL.LL\nLLLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLLL\nL.LLLLLL.L\nL.LLLLL.LL")
          state-1 (parser/data->chart "#.##.##.##\n#######.##\n#.#.#..#..\n####.##.##\n#.##.##.##\n#.#####.##\n..#.#.....\n##########\n#.######.#\n#.#####.##")
          state-2 (parser/data->chart "#.LL.LL.L#\n#LLLLLL.LL\nL.L.L..L..\nLLLL.LL.LL\nL.LL.LL.LL\nL.LLLLL.LL\n..L.L.....\nLLLLLLLLL#\n#.LLLLLL.L\n#.LLLLL.L#")
          state-3 (parser/data->chart "#.L#.##.L#\n#L#####.LL\nL.#.#..#..\n##L#.##.##\n#.##.#L.##\n#.#####.#L\n..#.#.....\nLLL####LL#\n#.L#####.L\n#.L####.L#")
          state-4 (parser/data->chart "#.L#.L#.L#\n#LLLLLL.LL\nL.L.L..#..\n##LL.LL.L#\nL.LL.LL.L#\n#.LLLLL.LL\n..L.L.....\nLLLLLLLLL#\n#.LLLLL#.L\n#.L#LL#.L#")
          state-5 (parser/data->chart "#.L#.L#.L#\n#LLLLLL.LL\nL.L.L..#..\n##L#.#L.L#\nL.L#.#L.L#\n#.L####.LL\n..#.#.....\nLLL###LLL#\n#.LLLLL#.L\n#.L#LL#.L#")
          state-6 (parser/data->chart "#.L#.L#.L#\n#LLLLLL.LL\nL.L.L..#..\n##L#.#L.L#\nL.L#.LL.L#\n#.LLLL#.LL\n..#.L.....\nLLL###LLL#\n#.LLLLL#.L\n#.L#LL#.L#")
          action rule/action-line-of-sight
          line-of-sight (generate/generate-line-of-sight 10 10)]
      (testing "example states and changes between"
        (is (= state-1 (update-chart state-0 10 10 action line-of-sight)))
        (is (= state-2 (update-chart state-1 10 10 action line-of-sight)))
        (is (= state-3 (update-chart state-2 10 10 action line-of-sight)))
        (is (= state-4 (update-chart state-3 10 10 action line-of-sight)))
        (is (= state-5 (update-chart state-4 10 10 action line-of-sight)))
        (is (= state-6 (update-chart state-5 10 10 action line-of-sight)))))))