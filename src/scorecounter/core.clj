(ns scorecounter.core
  (:gen-class))


(defonce players (ref {}))

(defn add-player [name]
  (dosync
    (alter players assoc name 0)))

(defn add-score [name]
  (if (contains? @players name)
    (dosync
      (ref-set players (update-in @players [name] inc)))
    (println "No such player")))

(defn print-scores []
  (doseq [x @players] (println (x 0) ":" (x 1))))


(defn setup []
    (println "Enter name: (empty to move on)")
    (let [in (read-line)]
      (if (empty? in)
        (println "Moving to game mode")
        (do 
          (add-player in)
          (recur)))))

(defn game []
    (print-scores)
    (println "Enter name to add score (empty to end)")
    (let [in (read-line)]
      (if (empty? in)
        (println "Game Over")
        (do
          (add-score in)
          (recur)))))

(defn -main
  "The game starts here"
  [& args]
  (setup)
  (game)
  (print-scores))




