(def p 6240322667N)   
(def q 6240323147N)    
(def n (* p q))
(def m (* (dec p) (dec q)))
; gcd = (second (egcd a b ))
(defn rand-bigint [n] (bigint (bigdec (rand n))))

(defn relative-prime [a b]
	(= (second( egcd a b )) 1))

(defn find-e-helper [m]
  (let [check-e (fn [e m] (and (relative-prime e m) (< e m)))]
    (let [probable-e (find-first strong-prime? (rand-bigint m))]
      (if (check-e probable-e m)
          probable-e
          (find-e-helper m)))))

(defn find-e [m]
    (let [candidate-e (find-e-helper m)
          probable-d (modular-inverse candidate-e m)
          d-and-e-multiplication (* candidate-e probable-d)]       
      (if (= 1 (modular-inverse d-and-e-multiplication m))
          candidate-e
          (find-e m) 
        )))

(def e (find-e m))