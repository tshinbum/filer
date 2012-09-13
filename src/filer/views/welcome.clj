(ns filer.views.welcome
  (:require [filer.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage defpartial render]])
  (:use [hiccup.page :only [html5]])
  (:use [hiccup.form :only [label text-field form-to submit-button]])
  )

(defpage "/my-page" []
           "Welcome to moi filer")

(defpartial layout [& content]
    (html5
        [:head
        [:title "Forms"]]
        [:body
        content]))

(defpartial user-fields [{:keys [firstname lastname]}]
    (label "firstname" "First name: ")
    (text-field "firstname" firstname)
    (label "lastname" "Last name: ")
    (text-field "lastname" lastname))

(defpage "/user/add" {:as user}
    (layout
        (form-to [:post "/user/add"]
            (user-fields user)
            (submit-button "Add user"))))

(require '[noir.response :as resp])

(defn valid? [{:keys [firstname lastname]}]
    true)

(defpage [:post "/user/add"] {:as user}
    (if (valid? user)
        (layout
            [:p "User added!" [first user] ])    
        (render "/user/add" user)))
