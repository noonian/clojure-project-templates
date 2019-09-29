(ns clj.new.pathom-api
  (:require [clj.new.templates :refer [renderer project-name name-to-path ->files
                                       multi-segment sanitize-ns]]
            [clojure.string :as str]))

(def render (renderer "pathom-api"))

(defn strip-qualifier [s]
  (last (str/split s #"/")))

(defn pathom-api
  "Generates project configured with integrant and integrant/repl."
  [name]
  (let [data {:name (project-name name)
              :sanitized (name-to-path (strip-qualifier name))
              :root-ns (sanitize-ns (strip-qualifier name))}]
    (println "Generating fresh 'clj new' integrant-system project.")
    (->files data
             ["deps.edn" (render "deps.edn" data)]
             [".dir-locals.el" (render "dir-locals.el" data)]
             ["dev/src/user.clj" (render "user.clj" data)]
             ["src/config.edn" (render "config.edn" data)]
             ["src/{{sanitized}}/system.clj" (render "system.clj" data)]
             ["src/{{sanitized}}/jetty.clj" (render "jetty.clj" data)]
             ["src/{{sanitized}}/eql.clj" (render "eql.clj" data)]
             ["src/{{sanitized}}/routes.clj" (render "routes.clj" data)])))
