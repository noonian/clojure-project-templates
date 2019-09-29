(ns {{root-ns}}.eql
  (:require [integrant.core :as ig]
            [com.wsscode.pathom.core :as p]
            [com.wsscode.pathom.connect :as pc]
            [com.wsscode.pathom.sugar :as ps]))

(pc/defresolver greeting-resolver [env input]
  {::pc/output [:{{root-ns}}/greeting]}
  {:{{root-ns}}/greeting "Hello, World!"})

(def resolvers [greeting-resolver])

(defmethod ig/init-key ::pathom-resolvers [_ _] resolvers)

(defmethod ig/init-key ::pathom-parser [_ {:keys [resolvers]}]
  (ps/connect-parallel-parser resolvers))
