;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es_8_torre_di_Hanoi_rompicapo_v2) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define hanoi-disks
  (lambda (n k)
    (hanoi-config n k 1 2 3 0 0 0)
    ))


(define hanoi-config
  (lambda (n k s d t ns nd nt)
    (if (= n 0)
        (list (list s ns)
              (list d nd)
              (list t nt)
              )
        (let ((half (expt 2 (- n 1))))
          (if (< k half)
              ;; il disco n è ancora su s
              (hanoi-config (- n 1) k s t d (+ ns 1) nt nd)

              ;; il disco n è già su d
              (hanoi-config (- n 1) (- k half) t d s nt (+ nd 1) ns)
              )
          )
        )
    ))