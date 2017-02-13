(ns scorecounter.core
  (:gen-class))


(def players (ref {}))

(defn add-player [name]
  (dosync
    (ref-set players (conj @players { name 0}))))

(defn add-score [name]
  (try
    (dosync
      (ref-set players (assoc @players name (inc (@players name)))))
    (catch Exception e (println "No such player"))))

(defn print-scores []
  (doseq [x @players] (println (x 0) ":" (x 1))))


(defn setup []
  (loop []
    (println "Enter name: (empty to move on)")
    (let [in (read-line)]
      (if (< (count in) 1)
        (println "Moving to game mode")
        (do 
          (add-player in)
          (recur))))))

(defn game []
  (loop []
    (print-scores)
    (println "Enter name to add score (empty to end)")
    (let [in (read-line)]
      (if (< (count in) 1)
        (println "Game Over")
        (do
          (add-score in)
          (recur))))))

(defn -main
  "The game starts here"
  [& args]
  (setup)
  (game)
  (print-scores))




