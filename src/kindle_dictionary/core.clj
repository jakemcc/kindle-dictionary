(ns kindle-dictionary.core
  (:refer-clojure :exclude [format])
  (:require [clojure.java.io :as io]
            [clojure.string :as string]
            [hiccup.core :refer [html]]
            [hiccup.page :refer [html4]]
            [kindle-dictionary.dictionaries :as dict])
  (:import java.awt.image.BufferedImage
           java.awt.Color
           java.awt.Font
           java.awt.Graphics
           javax.imageio.ImageIO))

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
  [:idx:entry {:name "default" :scriptable "yes" :spell "yes"}
   [:h5 [:dt (format-word entry)]]
   [:dd (format definition)]])

(defn generic-headers
  [body]
  (html4 [:head
          ;; (include-css "style.css")
          [:meta {:http-equiv "content-type" :content "text/html"}]]
         body))

(defn generate-word-list
  [words]
  (into [:mbp:framset]
        (interpose [:hr]
                   (mapv define (sort-by :entry
                                         (fn [x y] (.compareToIgnoreCase x y))
                                         words)))))

(defn cover-page
  [book]
  (generic-headers
   [:body
    [:h1 (:title book)]
    [:h3 "Created by " (:creator book)]]))

(defn opf [book]
  (with-open [r (io/reader (io/resource "opf-template.opf"))]
    (-> (slurp r)
        (string/replace "REPLACE_TITLE" (:title book))
        (string/replace "REPLACE_CREATOR" (:creator book))
        (string/replace "REPLACE_LANGUAGE" (:language book "en-us")))))

(defn copyright [book]
  (generic-headers
   (if-let [c (:copyright book)]
     [:body
      [:p c]]
     (throw (Exception. "Fill in the copyright")))))

(defn write-cover-image [book]
  (let [{:keys [title creator]} book
        img (BufferedImage. 1600 2560 BufferedImage/TYPE_INT_RGB)
        graphics (.getGraphics img)]
    (doto graphics
      (.setColor Color/WHITE)
      (.fillRect 0 0 1600 2560)
      (.setColor Color/BLACK)
      (.setFont (Font. "Arial Black" Font/BOLD 40))
      (.drawString title 40 60)
      (.drawString (str "Created by " creator) 40 120))
    (ImageIO/write img "jpg" (io/file "output" "cover-image.jpg"))))

(defn usage [book]
  (if-let [details (:details book)]
    (-> (slurp (io/resource "usage.html"))
        (string/replace "REPLACE_DETAILS" details))
    (throw (Exception. "Missing :details for book"))))

(defn output-book
  [book]
  ;; (write-cover-image book)
  (spit "output/dict.opf" (opf book))
  (spit "output/cover.html" (cover-page book))
  (spit "output/copyright.html" (copyright book))
  (spit "output/usage.html" (usage book))
  (spit "output/content.html" (let [markup (generate-word-list (:words book))]
                                (with-open [r (io/reader (io/resource "content-template.xhtml"))]
                                  (string/replace (slurp r) "REPLACETHIS" (html markup))))))

(defn make-dune-dictionary []
  (spit "content.html" (generate-word-list dict/dune-words)))

(comment
  (output-book dict/diaspora-dictionary)

  ;; kindlegen example.opf -verbose -C2 -o example-dictionary.mobi

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
