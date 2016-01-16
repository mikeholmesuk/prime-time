(ns prime-time.calc-test
  (:require [clojure.test :refer :all]
            [prime-time.calc :refer :all]))

(deftest generate-primes-test

  (testing "returns a seq"
    (is (seq? (generate-primes (rand-int 30)))))

  (testing "generates the correct number of values (randomly-generated)"
    (let [rand-number (rand-int 30)]
      (is (= rand-number (count (generate-primes rand-number))))))

  (testing "generates first x (rand to 10) primes correctly"
    (let [known-primes '(2, 3, 5, 7, 11, 13, 17, 19, 23, 29)
          rand-number (rand-int 10)]
      (is (= (generate-primes rand-number) (take rand-number known-primes))))))

(deftest calculate-multiples-test

  (testing "returns a vector"
    (let [primes (generate-primes (rand-int 30))]
      (is (vector?
            (calculate-multiples (nth primes (rand-int (count primes))) primes)))))

  (testing "returns the same count of output values as input values"
    (let [primes (generate-primes (rand-int 30))]
      (is (= (count primes)
             (count (calculate-multiples (nth primes (rand-int (count primes))) primes))))))

  (testing "calculates all multiplication values correctly"
    (let [primes (generate-primes (rand-int 30))
          rand-prime (nth primes (rand-int (count primes)))]
      (map-indexed
        (fn [idx multiplied-val]
          (is (= multiplied-val (* rand-prime (nth primes idx)))))
        (calculate-multiples rand-prime primes)))))

(deftest generate-primes-multiples-test

  (testing "returns a map"
    (is (map? (generate-prime-multiples (generate-primes (rand-int 30))))))

  (testing "returns a map with th same count as the input primes list"
    (let [primes (generate-primes (rand-int 30))]
      (is (= (count primes) (count (generate-prime-multiples primes)))))))