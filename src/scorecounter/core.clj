(ns scorecounter.core
  (:gen-class))


(defn add-player 
  "Adds a player to the provided map of players" 
  [players playerName] 
  (assoc players playerName 0))

(defn add-score 
  "Add a point to the player identified by name in the players map"
  [players name]
  (if (contains? players name)
      (update-in players [name] inc)
      (do (println "No such player") players)))

(defn print-scores 
  "Prints out all the players in players and their scores"
  [players]
  (doseq [x players] (println (x 0) ":" (x 1))))


(defn setup 
  "Starts an interactive loop for fillin in players into the provided map" 
  [players]
  (println "Enter name: (empty to move on)")
  (let [in (read-line)]
    (if (empty? in)
      players
      (recur (add-player players in)))))

(defn game 
  "Starts interactive loop to add score to players in the provided map"
  [players]
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
        
