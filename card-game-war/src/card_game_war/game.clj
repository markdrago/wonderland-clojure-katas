(ns card-game-war.game)

;; feel free to use these cards or use your own data structure
(def suits [:spade :club :diamond :heart])
(def ranks [2 3 4 5 6 7 8 9 10 :jack :queen :king :ace])
(def cards
  (for [suit suits
        rank ranks]
    [suit rank]))

(defn rank-value [c]
  (.indexOf ranks (second c)))

(defn suit-value [c]
  (.indexOf suits (first c)))

(defn card-gt [c1 c2]
  (let [r1 (rank-value c1)
        r2 (rank-value c2)]
    (cond
     (> r1 r2) true
     (< r1 r2) false
     :else (> (suit-value c1) (suit-value c2) ))))

(defn play-round [player1-card player2-card]
  (let [spoils [player1-card player2-card]]
    (if (card-gt player1-card player2-card)
      [spoils nil]
      [nil spoils])))

(defn play-game [player1-cards player2-cards]
  (cond
   (empty? player1-cards) (println "Player 2 Wins!")
   (empty? player2-cards) (println "Player 1 Wins!")
   :else (let [res (play-round (first player1-cards) (first player2-cards))
               p1-next (concat (rest player1-cards) (first res))
               p2-next (concat (rest player2-cards) (second res))]
           (play-game p1-next p2-next))))
