(ns user
  (:require [clojure.repl :refer :all]
            [clojure.java.io :as io]
            [clojure.pprint :refer [pprint]]
            [integrant.core :as ig]
            [integrant.repl :refer [clear go halt prep init reset reset-all]]
            [integrant.repl.state :refer [system]]
            [{{root-ns}}.system :as system]))

(integrant.repl/set-prep! #(system/new-system :dev))

(comment

  (go)
  (reset)

  )
