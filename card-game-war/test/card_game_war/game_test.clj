(ns card-game-war.game-test
  (:require [clojure.test :refer :all]
            [card-game-war.game :refer :all]))


;; fill in  tests for your game
(deftest test-play-round
  (testing "the highest rank wins the cards in the round"
    (is (true? (card-gt [:spade 8] [:club 4]) )))
  (testing "queens are higher rank than jacks"
    (is (true? (card-gt [:spade :queen] [:spade :jack]))))
  (testing "kings are higher rank than queens"
    (is (true? (card-gt [:spade :king] [:spade :queen]))))
  (testing "aces are higher rank than kings"
    (is (true? (card-gt [:spade :ace] [:spade :king]))))
  (testing "if the ranks are equal, clubs beat spades"
    (is (true? (card-gt [:club 5] [:spade 5]))))
  (testing "if the ranks are equal, diamonds beat clubs"
    (is (true? (card-gt [:diamond 5] [:club 5]))))
  (testing "if the ranks are equal, hearts beat diamonds"
    (is (true? (card-gt [:heart 5] [:diamond 5])))))

(deftest test-play-game
  (testing "the player loses when they run out of cards"))

