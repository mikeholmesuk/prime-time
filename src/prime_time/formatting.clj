(ns prime-time.formatting
  (:require [clojure.string :as s]))

(defn pad-and-join-values
  "This is a utility function taking a list of values as a vector and returning a
  list of formatted/ padded values"
  [values]
  (s/join (mapv #(format "%5d" %) values)))

(defn format-header
  "Takes a list of primes and generates the header of the table as a formatted string"
  [primes]
  (let [header (str (format "%6s" "|") (pad-and-join-values primes))]
    (str header "\n" (apply str (repeat (count header) "-")) "\n")))

(defn format-row
  "Takes a single key and value map containing the prime (as a key) and the calculated values
  and returns a formatted string with appropriate padding.
  - row: a single map entry with the prime and the calculated values. e.g. {2 [4 6 10]} "
  [row]
  (str (format "%5d" (key row)) "|" (pad-and-join-values (val row))))

(defn print-multiplication-table
  "Takes a hash-map containing the primes and the multiplication values and prints to the console
  by building a formatted string.
  - map: a map of primes and the calculated multiplied values. e.g {2 [4 6 10], 4 [6 9 15], ...}"
  [primes-with-multiples-map]
  {:pre [(map? primes-with-multiples-map)]}
  (printf
    (str (format-header (keys primes-with-multiples-map))
         (s/join "\n"
                 (map #(format-row %) primes-with-multiples-map)) "\n\n")))