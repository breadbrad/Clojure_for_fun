;#1 ;result : 197729
(defn count-IP [] (with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
	(loop[ip_count 0
		  read_line (line-seq rdr)]
		(if(empty? read_line)
			 ip_count 
			(if (nil? (re-seq 
				#"\b(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)\b"
				(first read_line)))
				(recur ip_count (rest read_line))
				(recur (inc ip_count) (rest read_line)))))))
				
;#2 ;result : empty list  
(defn find-private-IP [] (with-open [rdr (clojure.java.io/reader "NASA_access_log_Jul95_short")]
	(let [pattern #"\b(?:(?:127\.)(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)\.){2}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)|(?:10\.)(?:(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)\.){2}(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)|(?:192\.)(?:168\.)(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)\.(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?)|(?:192\.)(?:88\.)(?:99\.)(?:25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[0-9][0-9]?))\b"]
	(loop[output  []
		  read_line (line-seq rdr)]
		(if(empty? read_line)
				output 
				(if (nil? (re-seq pattern (first read_line)))
					(recur output (rest read_line))
					(recur (into output (re-seq pattern (first read_line))) (rest read_line))))))))
