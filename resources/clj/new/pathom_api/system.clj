(ns {{root-ns}}.system
  (:require [clojure.java.io :as io]
            [aero.core :as aero]
            [integrant.core :as ig]))

(defmethod aero/reader 'ig/ref [_ _ value]
  (ig/ref value))

(defn load-config [profile]
  (aero/read-config (io/resource "config.edn")))

(defn new-system [profile]
  (let [config (load-config profile)]
    (ig/load-namespaces config)
    config))
