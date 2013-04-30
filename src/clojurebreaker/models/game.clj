(ns clojurebreaker.models.game
  (:require [clojure.test.generative.generators :as gen]))

(defn create []
  (gen/vec #(gen/one-of "r" "g" "b" "y") 4))
