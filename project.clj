(defproject functionalbytes/mount-lite "0.9.4"
  :description "Mount, but Clojure only and a more flexible API."
  :url "https://github.com/aroemers/mount-lite"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]]
  :plugins [[lein-codox "0.9.1"]]
  :codox {:output-path "../mount-lite-gh-pages"}
  :aot [mount.lite]
  :omit-source true
  :profiles {:graph {:dependencies [[org.clojure/tools.namespace "0.3.0-alpha3"]
                                    [org.clojure/tools.reader "0.10.0"]
                                    [org.clojure/java.classpath "0.2.3"]]
                     :jvm-opts ["-Dclojure.compiler.feature-graph=true"]
                     :version "0.9.4-graph"}})
