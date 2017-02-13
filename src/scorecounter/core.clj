(ns scorecounter.core
  (:gen-class))


(defn add-player [players playerName]
    (assoc players playerName 0))

(defn add-score [players name]
  (if (contains? players name)
      (update-in players [name] inc)
      (do (println "No such player") players)))

(defn print-scores [players]
  (doseq [x players] (println (x 0) ":" (x 1))))


(defn setup [players]
    (println "Enter name: (empty to move on)")
    (let [in (read-line)]
      (if (empty? in)
        players
        (recur (add-player players in)))))

(defn game [players]
    (print-scores players)
    (println "Enter name to add score (empty to end)")
    (let [in (read-line)]
      (if (empty? in)
        players
        (recur (add-score players in)))))

(defn -main
  "The game starts here"
  [& args]
  (let [players (atom {})]
    (swap! players setup)
    (println "Moving to game mode")
    (swap! players game)
    (println "Game over")
    (print-scores @players)))
        
