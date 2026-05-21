;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-advanced-reader.ss" "lang")((modname |es_6.2_laboratorio_belong_2025(2)_txt|) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #t #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define belong?
  (lambda (x S)
    (cond
      ((null? S) #f)
      ((= x (car S)) #t)
      ((< x (car S)) #f)
      (else (belong? x (cdr S)))
      )
    ))

(define position
  (lambda (x S)
    (if (= x (car S))
        0
        (+ 1 (position x (cdr S)))
        )
    ))

(define sorted-ins
  (lambda (x S)
    (cond
      ((null? S) (cons x null))
      ((= x (car S)) S)
      ((< x (car S)) (cons x S))
      (else (cons (car S)
                  (sorted-ins x (cdr S))))
      )
    ))

(define sorted-list
  (lambda (S)
    (if (null? S)
        null
        (sorted-ins (car S)
                    (sorted-list (cdr S)))
        )
    ))

