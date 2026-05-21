;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es_3_laboratorio_parte_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; Restituisce l'indice (valore numerico) di un carattere nell'alfabeto
(define char-pos
  (lambda (alphabet c i)
    (if (char=? (string-ref alphabet i) c)
        i
        (char-pos alphabet c (+ i 1)))))

;; Restituisce l'indice del punto '.' nella stringa, o -1 se non esiste
(define find-dot
  (lambda (s i)
    (cond ((= i (string-length s)) -1)
          ((char=? (string-ref s i) #\.) i)
          (else (find-dot s (+ i 1))))))

;; Converte la parte intera usando il metodo di Horner ricorsivo
(define int-val
  (lambda (alphabet base s acc)
    (if (string=? s "")
        acc
        (int-val alphabet base 
                 (substring s 1 (string-length s))
                 (+ (* acc base) (char-pos alphabet (string-ref s 0) 0))))))

;; Converte la parte frazionaria (p è la potenza negativa corrente: 1, 2, 3...)
(define frac-val
  (lambda (alphabet base s p)
    (if (string=? s "")
        0
        (+ (/ (char-pos alphabet (string-ref s 0) 0) (expt base p))
           (frac-val alphabet base (substring s 1 (string-length s)) (+ p 1))))))

(define rep->number
  (lambda (alphabet rep)
    (let ((first (string-ref rep 0)))
      (cond ;; Gestione Segno Negativo
            ((char=? first #\-) 
             (- (process-unsigned alphabet (substring rep 1 (string-length rep)))))
            ;; Gestione Segno Positivo
            ((char=? first #\+) 
             (process-unsigned alphabet (substring rep 1 (string-length rep))))
            ;; Nessun segno
            (else 
             (process-unsigned alphabet rep))))))

;; Helper che analizza la stringa senza segno e cerca il punto
(define process-unsigned
  (lambda (alphabet s)
    (let ((dot-idx (find-dot s 0))
          (base (string-length alphabet)))
      (if (= dot-idx -1)
          ;; Solo parte intera
          (int-val alphabet base s 0)
          ;; Parte intera + Parte frazionaria
          (+ (int-val alphabet base (substring s 0 dot-idx) 0)
             (frac-val alphabet base (substring s (+ dot-idx 1) (string-length s)) 1))))))

(define bin-rep->number
  (lambda (rep)
    (rep->number "01" rep)))

; (bin-rep->number "10110.011") -> 22.375
; (rep->number "0123456789ABCDEF" "0.A") -> 0.625
; (rep->number "zu" "-uuzz") -> -12