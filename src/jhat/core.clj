(ns jhat.core
  (:require [lamina.core :refer [siphon enqueue receive-all enqueue-and-close]]
            [aleph.tcp :refer [start-tcp-server]]
            [gloss.core :refer [string]]
            [clojure.string :as s]))

; including Phoenician Aleph Phoenician aleph.svg, Syriac 'Ālaph ܐ, Hebrew Aleph א, and Arabic Alif ا.
(defn echo-handler [channel client-info]
  (siphon channel channel))

(defn handle-message [ch m]
  (prn m)
  (cond (= (s/trim-newline (str m)) "q") (enqueue-and-close ch "Goodbye!")
        :else (enqueue ch (str "You said " m))))

(defn handler [ch client-into]
  (receive-all ch #(handle-message ch %)))

(comment
  (start-tcp-server echo-handler {:port 1234})
  (start-tcp-server handler {:port 1245 :frame (string :utf-8 :delimiters ["\r\n"])}))
