(ns kindle-dictionary.core
  (:require [kindle-dictionary.dictionaries :as dict]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html4 include-css]]
            [clojure.string :as string])
  (:refer-clojure :exclude [format]))

(defmulti format type)

(defmethod format clojure.lang.PersistentVector
  [definition]
  [:pre (interpose [:br] definition)])

(defmethod format :default
  [definition]
  definition)

(defn remove-numbers
  [word]
  (second (re-find #"(.*)\d+$" word)))

(defn format-word
  [word]
  (if-let [other-form (remove-numbers word)]
    [:idx:orth word
     [:idx:infl
      [:idx:iform {:value other-form}]]]
    [:idx:orth word]))

(defn define [{:keys [entry definition]}]
  [:div.definition
   [:idx:entry {:name "default"}
    [:dt (format-word entry)]
    [:dd (format definition)]]])

(defn generic-headers
  [body]
  (html4 [:head
          (include-css "style.css")
          [:meta {:http-equiv "content-type"
                  :content "text/html"}]]
         body))

(defn generate-word-list
  [words]
  (generic-headers
   [:body
    [:dl
     (interpose [:hr]
                (map define (sort-by :entry
                                     (fn [x y] (.compareToIgnoreCase x y))
                                     words)))]]))

(defn cover-page
  [book]
  (generic-headers
   [:body
    [:h1 (:title book)]
    [:h3 "Created by " (:creator book)]]))

(defn output-book
  [book]
  (spit "cover.html" (cover-page book))
  (spit "content.html" (generate-word-list (:words book))))

(defn make-dune-dictionary []
  (spit "content.html" (generate-word-list dict/dune-words)))

(comment
  (output-book dict/dune-dictionary)
  )

(comment
  (def lines (remove string/blank? (string/split-lines (slurp "src/kindle_dictionary/diaspora.edn"))))

  (def entries (for [line lines
                     :let [[headword definition] (string/split line #"\. " 2)]]
                 {:entry headword :definition definition}))
  (spit "diaspora.edn" (with-out-str
                         (clojure.pprint/pprint (vec entries))))
  (filter (comp (partial = "tau") :entry) entries)
  )
