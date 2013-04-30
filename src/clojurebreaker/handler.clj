(ns clojurebreaker.handler
  (:use [compojure.core]
        [ring.middleware.file :only [wrap-file]]
;        [ring.middleware.session]
        [sandbar.stateful-session]
        [clojurebreaker.models.game])
  (:require [compojure.route :as route]
            [clojurebreaker.common :as common]
            [ring.middleware.stacktrace :as trace]))


(defn welcome []
  (when-not (session-get :game) (session-put! :game (create)))
  (let [counter (+ 1 (session-get :counter 0))]
    (do (session-put! :counter counter)
        (common/layout
         [:p "Stateful clojurebreaker " counter]
         [:p "Clojurebreaker game: " (session-get :game)]))))

(defroutes app-routes
  (GET "/" [] (welcome))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (-> app-routes
      trace/wrap-stacktrace
      wrap-stateful-session))
