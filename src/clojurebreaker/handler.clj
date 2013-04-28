(ns clojurebreaker.handler
  (:use [compojure.core]
        [hiccup.form]
        [hiccup.page]
        [ring.middleware.session])
  (:require [compojure.handler :as handler]
            [compojure.route :as route]
            [clojurebreaker.common :as common]))

(defn message []
  (html5
   [:body
    (form-to [:post "/message"]
             (text-area {:placeholder "say something..."} "message")
             [:br]
             (text-field {:placeholder "name"} "id")
             (submit-button "post message"))]))

(defn display-message [params]
  (let [form-params (:form-params params)]
    (html5
      [:body
       [:p (get form-params "id") " says " (get form-params "message")]])))

(defn welcome []
  (common/layout
   [:p "Welcome to clojurebreaker! Your current game ID is TBD" ]))

(defroutes app-routes
  (GET "/" [] (welcome))
  (GET "/message" [] (message))
  (POST "/message" params (display-message params))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
