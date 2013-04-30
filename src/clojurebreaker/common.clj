(ns clojurebreaker.common
  (:use [hiccup.def :only [defhtml]]
        [hiccup.page :only [include-css]]))

(defhtml layout [& body]
  [:head
   [:title "Welcome to ClojureBreaker!"]
   (include-css "/css/screen.css")]
  (into [:body] body))
