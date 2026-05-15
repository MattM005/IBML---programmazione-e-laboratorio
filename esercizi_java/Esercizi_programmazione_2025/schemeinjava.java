
/**
 * da scheme in java
 */

public class schemeinjava
{
    /** 1 esercizio: misure foglio a3,a4.... 4->29,7 **/
    /*
     * (define s    ; val: misura (lato piu' lungo)
        (lambda (k)   ; k: intero non negativo
            ; (if (>= k 2)
                ; (/ (s (- k 2)) 2)
            ; (if (= k θ)
                ; sθ
                ; s1
            ; ))
        (cond ((>= k 2)
            (/ (s (- k 2)) 2))
            ((= i k θ)
                sθ
            )
            (else ; (= k 1)
               s1
            )
        ))
      )
     */
    public static double s ( int k ) {    // k>=0
        if( k>=2 ){
            return ( s(k-2) / 2 );
        } else if ( k==0 ) {
            return s0;
        } else {
            return s1;
        }
    }
    
    public static final double s0 = 100 * Math.pow(2, 0.25);
    public static final double s1 = 100 * Math.pow(2, -0.25);


    
    /** 2 esercizio: mcd (minimo comune divisore) **/
    /*
     * (define mcd    ; val: intero
          (lambda (x y)   ; x, y: interi positivi
            (cond ((= x y)
                    x)
            ((< x y)
                (mcd x (- y x))))
            (else    ; x > y
                (mcd (- x y) y))
                )
            ))
        ))
     */
    // versione prof.
    public static int mcd (int x, int y) {
        while( x!=y ) {
            if( x<y ){
                y=y-x;
            } else {
                x=x-y;
            }
        }
        return x;
    }
    // versione mia
    public static int mcd2 (int x, int y) {
        if(x==y) {
            return x;
        } else if (x < y) {
            return mcd2(x, y-x);
        } else {
            return mcd2(x-y, y);       
        }
    }
    
    /** 3 esercizio: lista numeri primi **/
    /*
     * (define primo? ; val: boolean
          (lambda (n) ; n ≥ 2 intero
            (if (even? n)
                (= n 2)
                (non-ci-sono-divisori-dispari? n 3 (sqrt n)) ; (floor (sqrt n))
            )
          )
        )
        
        (define non-ci-sono-divisori-dispari? ; val: boolean
          (lambda (n inf sup) ; n ≥ 2 intero; inf, sup: interi
            (cond ((> inf sup)
                   true)
                  ((= (remainder n inf) 0)
                   false)
                  (else
                   (non-ci-sono-divisori-dispari? n (+ inf 2) (- n 1)))
            )
          )
        )
        
        (define lista-primi ... )
     */
    public static boolean primo(int n) {
        if(n % 2 == 0) {   // n pari
            return n==2;
        } else {
            int inf = 3;
            double sup = Math.sqrt(n);
            
            while((inf <= sup) && (n % inf > 0)) {
                inf = inf + 2;
            }
            return (inf > sup);
        }
    }
    
    public static void listaPrimi(int soglia){
        for(int i=2; i <= soglia; i++){
            if(primo(i)) {
                System.out.println(" " + i);
            }
        }
        System.out.println();
    }
    
    /** 4 esercizio: btr-val bilancia **/
    /*
     * (define btr-val   ; btr-val: intero
          (lambda (btr)   ; btr: stringa
            (let ((k (- (string-length btr) 1))) ; indice dell’ultima cifra
              (if (= k 0)
                  (btd-val btr) ; caso base: 1 cifra
                  (+ (* 3 (btr-val (substring btr 0 k)))  ; valore parte sx
                     (btd-val (substring btr k (string-length btr))) ; ultima cifra
                   )
                )
             )
         ))
        
        ; converte una singola cifra bilanciata in intero
        (define btd-val 
          (lambda (btd)
            (cond ((string=? btd "-") -1)
                  ((string=? btd ".") 0)
                  ((string=? btd "+") 1)
             )
          ))
     *
     */
    public static int btrVal( String btr ) {
        int n=0;
        
        for(int k=0; k<btr.length(); k++) {
            n = 3*n + btdVal( btr.charAt(k) );    
        }
        
        return n;
    }
    
    public static int btdVal( char btd ) {
        switch(btd) {
            case ('-') : {
                return -1;
            } 
            case ('.') : {
                return 0;
            }
            case ('+') : {
                return +1;
            }
        }
        return 0;
    }
    
    /*esercizio 5: proc. UFO */
    /*
     (define ufo            ; valore: ?
         (lambda (x)          ; x > 0 naturale
           (cond ((= x 1) 1)
             ((even? x)   ; x pari
              (- (* 2 (ufo (quotient x 2))) 1))
             (else        ; x dispari
              (+ (* 2 (ufo (quotient x 2))) 1))
             )))
     */
    
    public static int ufo (int x) {
        int[] u = new int[ (int) (Math.log(x)/Math.log(2)) + 1];   // dim.array=quante caselle mi servono
        // Math.log(x)/Math.log(2) -> log base 2 di x
        u[1] = 1;
        
        for(int k=2; k<=x; k++) { 
            if(x%2 == 0) {   // k pari
                // (- (* 2 (ufo (quotient x 2))) 1))  
                u[k] = 2 * u[k/2] - 1;
            } else {        // k dispari
                // (+ (* 2 (ufo (quotient x 2))) 1))
                u[k] = 2 * u[k/2] + 1;
            }
        }
        
        return u[x];
    }
    
    // versione 2
    public static int ufo2 (int x) {
        int[] u = new int[ (int) (Math.log(x)/Math.log(2)) + 1];   // dim.array=quante caselle mi servono
        int k = 0;
        u[0] = x;
        
        while(x > 1) { 
            x = x / 2;
            k = k + 1;
            u[k] = x;
        }
        
        int y = 1;
        while (k > 0) {    
            k = k - 1;
            if(u[k] % 2 == 0) {   // k pari
                y = 2*y - 1;
            } else {        // k dispari
                y = 2*y + 1;
            }
        }
        return y;
    }
    
    /*esercizio 6: crivello di Eratostene */
    /**
     * Funziona come un "setaccio" che elimina progressivamente 
     * i multipli di ogni numero primo trovato, partendo dal 2, 
     * lasciando nella lista solo i numeri primi.
     */    
    
    public static void eratostene (int n) {
        boolean[] crivello = new boolean[n+1];
        
        for(int k=2; k <= n; k++) {
            crivello[k] = true;
        }
        
        for(int k=2; k <= n; k++) {
            
            if(crivello[k]) {
                System.out.print(" " + k);
                
                for(int m=2*k; m<=n; m=m+k) {
                    crivello[m] = false;
                }
            }
        }
        System.out.println();
    }
    
    
}
