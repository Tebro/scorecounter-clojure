(ns scorecounter.core-test
  (:require [clojure.test :refer :all]
            [scorecounter.core :refer :all]))


(defonce test-player-name "test")

(deftest add-player-test
  (testing "Adding a player"
    (is (= {test-player-name 0} (add-player {} test-player-name))
        "The player should have been added to the map")))

(deftest add-score-test
  (testing "Adding score to a player"
    (is 
      (= 
        {test-player-name 1} 
        (add-score 
          {test-player-name 0} 
          test-player-name))
      "The score should have increased")))

