(ns mount.lite.features
  "Hack for having an open feature set."
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]))

(defn set-global-features [& features]
  (let [features-field (.getDeclaredField clojure.lang.LispReader "PLATFORM_FEATURES")
        platform-field (.getDeclaredField clojure.lang.LispReader "PLATFORM_KEY")]
    (.setAccessible features-field true)
    (.setAccessible platform-field true)
    (let [features-set (.get features-field nil)
          set-impl-field (.getDeclaredField clojure.lang.APersistentSet "impl")
          platform-key (.get platform-field nil)]
      (.setAccessible set-impl-field true)
      (.set set-impl-field features-set (assoc (zipmap features features) platform-key platform-key)))
    (.get features-field nil)))

(defn compiler-options-features []
  (reduce-kv (fn [s k v]
               (if (and (.startsWith (name k) "feature-") v)
                 (conj s (keyword (subs (name k) (count "feature-"))))
                 s))
             #{} *compiler-options*))

(apply set-global-features (compiler-options-features))
