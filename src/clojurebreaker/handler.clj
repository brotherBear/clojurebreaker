(ns clojurebreaker.handler
  (:use [compojure.core]
        [ring.middleware.file :only [wrap-file]]
        [sandbar.stateful-session]
        [hiccup.form])
  (:require [compojure.route :as route]
            [clojurebreaker.models.game :as game]
            [clojurebreaker.common :as common]
            [ring.middleware.stacktrace :as trace]))


(common/defpartial board []
  (form-to [:post "/guess"]
           (text-field "one")
           (text-field "two")
           (text-field "three")
           (text-field "four")
           (submit-button "Guess")))


(defn welcome []
  (when-not (session-get :game) (session-put! :game (game/create)))
  (let [counter (+ 1 (session-get :counter 0))]
    (do (session-put! :counter counter)
        (common/layout
         [:p "Stateful clojurebreaker " counter]
         [:p "Clojurebreaker game: " (session-get :game)]
         (board)))))

(defroutes app-routes
  (GET "/" [] (welcome))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      trace/wrap-stacktrace
      wrap-stateful-session))
