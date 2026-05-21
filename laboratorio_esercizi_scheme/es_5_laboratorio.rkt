;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname es_5_laboratorio) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(require "drawings.ss")

(define L-tessellation
  (lambda (n)
    (if (= n 1)
        L-tile
        (let ((t (L-tessellation (/ n 2))))
          (glue-tiles
           (glue-tiles
            t
            (shift-right (quarter-turn-right t) (/ n 2)))
           (glue-tiles
            (shift-down (quarter-turn-left t) (/ n 2))
            (shift-right (half-turn t) (/ n 2))))))))


; (L-tessellation 4) --> 3 quadrati circa
; ecc...
; 
; L-tile → 3/4 quadrato
; (shift-right L-tile 2) --> 
; (quarter-turn-left L-tile) --> 
; ...
