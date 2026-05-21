;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es_1_laboratorio_vers_1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
;; ultima lettera stringa
(define ultima
  (lambda (str)
    (string-ref str (- (string-length str) 1))))

;; tipo di sostantivo
(define sog-o (lambda (s) (char=? (ultima s) #\o)))
(define sog-i (lambda (s) (char=? (ultima s) #\i)))
(define sog-a (lambda (s) (char=? (ultima s) #\a)))
(define sog-e (lambda (s) (char=? (ultima s) #\e)))

;; articolo
(define sog-art
  (lambda (sog)
    (cond
      ((sog-o sog) (string-append "il " sog))
      ((sog-i sog) (string-append "i " sog))
      ((sog-a sog) (string-append "la " sog))
      ((sog-e sog) (string-append "le " sog))
      )
   ))

;; tipo di verbo
(define verb-are
  (lambda (v)
    (string=? (substring v (- (string-length v) 3))
              "are")
    ))

(define verb-ere
  (lambda (v)
    (string=? (substring v (- (string-length v) 3))
              "ere")
    ))

(define verb-ire
  (lambda (v)
    (string=? (substring v (- (string-length v) 3))
              "ire")
    ))

;; radice del verbo
(define rad-verb
  (lambda (v)
    (substring v 0 (- (string-length v) 3))
    ))

;; coniugazione
(define verb
  (lambda (sog v)
    (cond
      ;; verbi in -are
      ((and (verb-are v) (or (sog-i sog) (sog-e sog)))
          (string-append (rad-verb v) "ano")
       )
      ((and (verb-are v) (or (sog-o sog) (sog-a sog)))
          (string-append (rad-verb v) "a")
       )
      ;; verbi in -ere e -ire
      ((and (or (verb-ere v) (verb-ire v))
            (or (sog-i sog) (sog-e sog))
            )
          (string-append (rad-verb v) "ono")
       )

      ((and (or (verb-ere v) (verb-ire v))
            (or (sog-o sog) (sog-a sog))
            )
          (string-append (rad-verb v) "e")
       )
      )
    ))

; metto insieme frase
(define frase
  (lambda (sog verbo ogg)
    (string-append (sog-art sog) " " (verb sog verbo) " " (sog-art ogg))
    ))