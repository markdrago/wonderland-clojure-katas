(ns alphabet-cipher.coder)

(def alphabet
  (apply str
    (map char
      (range
        (int \a)
        (+ (int \z) 1)))))

(defn char-offset [c] (- (int c) (int \a)))

(defn chart-shift [c distance]
  (nth (cycle alphabet) (+
    (char-offset c)
    distance)))

(defn chart [keychar clearchar]
  (chart-shift keychar (char-offset clearchar)))

(defn chart-rev [keychar cipherchar]
  (chart-shift keychar (- (count alphabet) (char-offset cipherchar))))

(defn chart-key [clearchar cipherchar]
  (chart-shift \a
    (+
      (count alphabet)
      (-
        (int cipherchar)
        (int clearchar)))))

(defn convert [keyword message transform-fn]
  (apply str
    (map
      (fn [p] (transform-fn (first p) (second p)))
      (map vector message (cycle keyword)))))

(defn encode [keyword message]
  (convert keyword message chart))

(defn decode [keyword message]
  (convert keyword message chart-rev))

(defn subcipher [current complete]
  (cond
    (= complete (apply str (take (count complete) (cycle current)))) current
    :else (subcipher (apply str (take (+ 1 (count current)) complete)) complete)))

(defn decypher [cipher message]
  (subcipher "" (convert cipher message chart-key)))
