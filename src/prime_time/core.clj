(ns prime-time.core
  (:require [prime-time.calc :as calc]
            [prime-time.formatting :as formatting]))

(defn -main [& [args]]
  "Main entry point into the application. Takes a number as the first argument and generates a
  number of primes, and their multiples in a table. Validates that the input argument is a number"
  {:pre [(number? (read-string args))]}
  (let [primes (calc/generate-primes (read-string args))]
    (formatting/print-multiplication-table (calc/generate-prime-multiples primes))))