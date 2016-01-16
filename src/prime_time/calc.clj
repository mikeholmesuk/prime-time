(ns prime-time.calc)

(defn sieve [s]
  "Shamelessly lifted from the lazy-seq clojure page (https://clojuredocs.org/clojure.core/lazy-seq)
  and used to filter out prime numbers from a provided list"
  (cons (first s)
        (lazy-seq (sieve (filter #(not= 0 (mod % (first s))) (rest s))))))

(defn generate-primes
  "Function which takes a number and generates the requested number of primes as a list"
  [number-to-gen]
  {:pre [(number? number-to-gen)]}
  (take number-to-gen (sieve (iterate inc 2))))

(defn calculate-multiples
  "Given a prime number and the complete list of primes, this function multiplies the prime against
  every other prime in the provided list and returns them as a vector"
  [x primes]
  (mapv #(* x %) primes))

(defn generate-prime-multiples
  "Given a list of primes, returns a (sorted) map of those primes along with the results of
  multiplying each prime against every other prime in the list"
  [primes]
  (into (sorted-map)
        (map #(hash-map % (calculate-multiples % primes)) primes)))
