(ns jhat.core
  (:require [lamina.core :refer [siphon]]
            [aleph.tcp :refer [start-tcp-server]]))

; including Phoenician Aleph Phoenician aleph.svg, Syriac 'Ālaph ܐ, Hebrew Aleph א, and Arabic Alif ا.
(defn echo-handler [channel client-info]
  (siphon channel channel))

(start-tcp-server echo-handler {:port 1234})
