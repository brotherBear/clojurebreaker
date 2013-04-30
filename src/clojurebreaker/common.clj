(ns clojurebreaker.common
  (:use [hiccup.core :only [html]]
        [hiccup.def :only [defhtml]]
        [hiccup.page :only [include-css]]))

(defhtml layout [& body]
  [:head
   [:title "Welcome to ClojureBreaker!"]
   (include-css "/css/screen.css")]
  (into [:body] body))


(defmacro defpartial
  "Create a function that returns html using hiccup. The function is
   callable with the given name."
  [fname params & body]
  `(defn ~fname ~params
     (html
      ~@body)))
