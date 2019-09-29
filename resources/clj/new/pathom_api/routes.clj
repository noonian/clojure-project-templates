(ns {{root-ns}}.routes
  (:require [clojure.core.async :as async :refer [<!!]]
            [integrant.core :as ig]
            [ring.util.response :as resp]
            [ring.middleware.cors :as cors]
            [ring.middleware.format :as format]
            [compojure.core :as cc]
            [compojure.route :as route]
            [clojure.pprint :refer [pprint]]))

(defn wrap-middleware [handler]
  (-> handler
      format/wrap-restful-format
      (cors/wrap-cors :access-control-allow-origin #"https://wilkerlucio.github.io"
                      :access-control-allow-methods [:get :put :post :delete])))

(defmethod ig/init-key ::ring-handler [_ {:keys [parser]}]
  (wrap-middleware
   (cc/routes
    (cc/GET "/hello" [] (resp/response "Hello, World!"))
    (cc/POST "/api/eql/alpha" {query :body-params} (resp/response (<!! (parser {} query))))
    (route/not-found "not found"))))
