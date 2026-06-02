
/**
* Aggiungi qui una descrizione della classe DynamicProgramming
* 
* @author (il tuo nome) 
* @version (un numero di versione o una data)
*/
public class DynamicProgramming {
    
    /*
     * 
     * R(5) = 15
     * 
     * R(100) => 2.7 * 10^17
     * 
     * R(n) => (3/2)^(n-1)
     * 
     * R(k) => (3/2)^(k-1)  ipotesi induttiva
     * 
     * per n>1: 
     * R(n) = 1 + R(n-2) + R(n-1)
     * 
     * R(0) = 1 => (3/2)^(0-1) = 2/3
     * R(1) = 1 => (3/2)^(1-1) = 1
     * 
     * per n>1: 
     * R(n) = 1 + R(n-2) + R(n-1)
     *      => 0 + (3/2)^(n-3) + (3/2)^(n-2)
     *      = (3/2)^(n-3) + (3/2)^(n-2) * ((3/2)^(-1) + 1)
     *      = (3/2)^(n-2) * ((3/2)^(-1) + 1) 
     *      = (3/2)^(n-2) * (2/3 + 1) 
     *      = (5/3) * (3/2)^(n-2) 
     *      > (3/2)^(n-1)
     *      
     *      5/3 > 3/2 <==> 10>9  VERO
     *      
     *      
     *      DynamicProgramming.fibMem(100) --> -1869596475  (int)
     *      Va in OVERFLOW... 
     *      L'unica opzione a disposizione nei tipi semplici è cambiare a long 10^18 (x2 dei byte)
     *      
     *      DynamicProgramming.fibMem(100) --> 1298777728820984005   (long)
     */
    
    // Fibonacci (poco efficiente)
    public static long fib(int n) {     // int->long
        if(n<2) {
            return 1;
        } else {
            return fib(n-2) + fib(n-1);
        }
    }
    
    
    // Memoization (non ha traduzione in italiano) è una TECNICA, è molto + efficiente!!!  
    private static final int UNKNOWN = 0; 
    
    public static long fibMem(int n) {
        // creaz. e realizz. di mem
        long[] mem = new long [n+1];       // array di interi
        
        for(int i=0; i<=n; i++) {
            mem[i] = UNKNOWN;
        }
    return fibRec(n, mem);
    }
    
    public static long fibRec(int n, long[] mem) {
        if(mem[n] == UNKNOWN) {
           if(n < 2) {
                mem[n] = 1;
            } else {
                mem[n] = fibRec(n-2, mem) + fibRec(n-1, mem);   // mem cambia nello svolgimento del programma
            } 
        }
        return mem[n];
    }
    
    /* Percorsi di Manhattan */
    /**
     * (define paths
     *   (lambda (i j)
     *     (if (or (= i 0) (= j 0))
     *       1
     *       (+ paths i (- j 1)) (paths (- i 1) j))
     *       )
     *     ))
     */
    
    public static long paths (int i, int j) {
        if (i == 0 || j == 0) {
            return 1;
        } else {
            return paths(i,j-1) + paths(i-1,j);
        }
    }
    
    
    // tecnica: MEMOIZATION
    // DynamicProgramming.pathsMem(5,5) --> 252
    
    public static long pathsMem(int i, int j) {
        long[][] mem = new long[i+1][j+1];   // MATRICE
        
        for(int u=0; u<=i; u++) {
            for(int v=0; v<=j; v++) {
                mem[u][v] = UNKNOWN;
            }
        }
        
        return pathsRec(i,j,mem);
    }
    
    public static long pathsRec(int i, int j, long[][] mem) {
        if(mem[i][j] == UNKNOWN) {
            if (i == 0 || j == 0) {
                mem[i][j] = 1;
            } else {
                mem[i][j] = pathsRec(i,j-1,mem) + pathsRec(i-1,j,mem);
            }
        }
        return mem[i][j];
    }
    
    
    // tecniche: 
    // 1) top-down - (memoization)
    // 2) bottom-up* - (esempio manhattan)
    
    // bottom-up*
    public static long pathsDP(int i, int j) {
        long[][] mem = new long[i+1][j+1];
        
        for(int v=0; v<=j; v++) {
            mem[0][v] = 1;
        } 
        
        for(int u=1; u<=i; u++) {
            mem[u][0] = 1;
        }
        
        for(int u=1; u<=i; u++) {
            for(int v=1; v<=i; v++) {
                mem[u][v] = mem[u][v-1] + mem[u-1][v]; 
            }
        }
        
        return pathsRec(i,j,mem);
    }
    
    /**
    
     * Top-Down
        ricorsione + memoization
        si parte dal problema grande
        si salvano i risultati intermedi
        
     * Bottom-Up
        si costruisce la soluzione passo dopo passo
        usando tabelle/matrici
        
     */
    
    // Numeri di Stirling 2 specie (pasticcini)
    /**
     * (define stirling
      (lambda (n k)   ; n,k: interi positivi t.c. [1<=k<=n]
        ; ricorsione
        (if (or (= k 1) (= k n))
            1
            (+ (stirling (- n 1) (- k 1))   ; (k > 1) dispongo i pasticcini in tutti - quello del canestrello
               (* k (stirling (- n 1) k))  ; (k < n) dispongo l'altro, meno sé stesso in tutti gli altri.
               ))
        ))
     */
    
    public static int st(int n, int k) {
       if(k == 1 || n == k) {
           return 1;
       } else {
           return st(n-1,k-1) + (st(n-1,k))*k;
       }
    }
    
    
    // LCS (sottoseq. più lunga)
    public static int llcs(String u, String v) {
        if(u.equals("") || v.equals("")) {
            return 0; 
        } else if (u.charAt(0) == v.charAt(0)) {
            return 1 + llcs(u.substring(1), v.substring(1));
        } else {
            return Math.max(llcs(u.substring(1), v), 
                            llcs(u,v.substring(1)));
        }
    }
    
    // lcs "arto", "atrio"
    // "arto", "rto", "to", "o", "". tot. possibilità, uguale per atrio.
    
    public static String lcsMem(String u, String v) {
        int m = u.length();
        int n = v.length();

        String[][] mem = new String[m + 1][n + 1];

        return lcsRec(u, v, mem);
    }

    private static String lcsRec(String u, String v, String[][] mem) {
        int m = u.length();
        int n = v.length();

        if (mem[m][n] == null) {

            if (m == 0 || n == 0) {
                mem[m][n] = "";

            } else if (u.charAt(0) == v.charAt(0)) {

                mem[m][n] = u.charAt(0)
                        + lcsRec(u.substring(1), v.substring(1), mem);

            } else {
                
                mem[m][n] = longer(
                                    lcsRec(u.substring(1), v, mem),
                                    lcsRec(u, v.substring(1), mem)
                                    );
            }
        }

        return mem[m][n];
    }

    public static String longer(String u, String v) {

        int m = u.length();
        int n = v.length();

        if (m < n) {
            return v;

        } else if (m > n) {
            return u;

        } else if (Math.random() < 0.5) {
            return v;

        } else {
            return u;
        }
    }
    
    // Programmazione dinamic bottom-up (conviene scrivere e vedere se fa il risultato giusto)
    
    public static int llcsDP(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        int[][] mem = new int[m+1][n+1];
        
        for(int j=0; j<=n; j++) {
            mem[0][j] = 1;
        } 
        
        for(int i=0; i<=m; i++) {
            mem[i][0] = 1;
        } 
        
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                
                if(u.charAt(m-i) == v.charAt(n-j)) {
                    mem[i][j] = 1+mem[i-1][j-1];
                } else {
                    mem[i][j] = Math.max(mem[i-1][j], mem[i][j-1]);
                }
            }
        }
        
        return mem[m][n];
    }
    
    
    
}
