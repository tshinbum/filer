(require '[clojure.java.jdbc :as sql])

(def sqlite-db {:subprotocol "sqlite"
               :subname "//127.0.0.1:3306/clojure_test"
               :user "clojure_test"
               :password "clojure_test"})

(sql/with-connection sqlite-db
    (sql/insert-records :fruit
        {:name "Apple" :appearance "rosy" :cost 24}
        {:name "Orange" :appearance "round" :cost 49}))

(sql/with-connection sqlite-db
    (sql/with-query-results rows
        ["SELECT * FROM fruit WHERE appearance = ?" "rosy"]
        (:cost (first rows))))
