(ns {{root-ns}}.jetty
  (:require [integrant.core :as ig]
            [ring.adapter.jetty :as jetty]))

(defmethod ig/init-key ::http-server [_ {:keys [handler port]}]
  (jetty/run-jetty handler {:port port :join? false}))

(defmethod ig/halt-key! ::http-server [_ jetty-server]
  (.stop jetty-server))
