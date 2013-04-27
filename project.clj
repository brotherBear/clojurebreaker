(defproject clojurebreaker "0.1.0-SNAPSHOT"
  :description "Based on example in Programming Clojure 2nd ed.
  Create a browser-based game of codebreaker in clojure."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/test.generative "0.1.3"]
                 [compojure "1.1.5"]
                 [org.clojure/math.combinatorics "0.0.1"]]
  :plugins [[lein-ring "0.8.3"]]
  :ring {:handler clojurebreaker.handler/app}
  :profiles
  {:dev {:dependencies [[ring-mock "0.1.3"]]}})
