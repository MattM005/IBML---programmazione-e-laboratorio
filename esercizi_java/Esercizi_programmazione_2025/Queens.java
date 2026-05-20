/**
 * ( non ancora da compilare )
 * 
 * Determinare n* soluzioni dato n scacchiera.
 * 
 * Board b;
 * int n, i, j;
 * 
 * b = new Board(n)
 * b.size()             : int
 * b.queensOn()         : int
 * b.underAttack(i,j)   : Boolean
 * b.addQueen(i,j)      : Board
 * b.arrangement()      : String
 * 
 * 
 * N* permutazioni n
 * 1x1 -> 1 sol
 * 2x2 -> no
 * 3x3 -> no
 * 4x4 -> 1/12 di 24 sol.
 * 5x5 -> 10 sol
 * 6x6 -> 0,6% su 720 sol.
 * 9x9 -> 352 sol
 * 12x12 -> 14200 sol
**/

public class Queens {
    
    private static final SList<Board> NULL_BOARDLIST = new SList<>();  // lista vuota unica
    
    public static int numeroSoluzioni( int n ) {   
        return numeroCompletamenti( new Board(n) );
    }
    
    public static int numeroCompletamenti( Board b ) {
        
        int n = b.size();
        int q = b.QueensOn(); 
        
        if( q == n ) {   // caso in cui è stata trovata
            return 1;
        } else {
            int count = 0;   // counter regine (di b)   - 0
            int i = q;   // indice di riga          - 1
            for (int j = 0; j < n; j++) {
                // se è minacciata --> NON metto regina
                // se NON è minacciata --> la metto
                if( !b.underAttack(i, j) ) {
                    Board nuova = b.addQueen(i, j);  // nuova scacchiera
                    count += numeroCompletamenti(nuova);
                }

            }
            return count;
        }
    }
    
    public static SList<Board> listaSoluzioni( int n ) {  // n > 0
        return listaCompletamenti( new Board(n) );
    }
    
    public static SList<Board> listaCompletamenti( Board b ) {
        
        int n = b.size();
        int q = b.QueensOn(); 
        
        if( q == n ) {
            return (new SList<Board>()).cons( b );   // soluzione: lista vuota
        } else {
            SList<Board> lista = new SList<Board>();   // counter regine (di b)  - 0
            int i = q + 1;          // indice di riga         - 1
            for( int j=1; j<=n; j++ ) {
                // se è minacciata --> NON metto regina
                // se NON è minacciata --> la metto
                if( !b.underAttack(i,j) ) {
                    lista = lista.append( listaCompletamenti(b.addQueen(i,j)) );   // somma n* completamenti
                }
            }
            return lista;
        }
    }
    
    // Dimensioni scacchiera: n-1 , n;  numero sol?
    public static SList<Object> listaNumeriSol( int n ) {
        // oggetto -> intero  (difficile conversione XD)
        return intervallo(1,n).map( (x) -> numeroSoluzioni(x) );   // lambda espressione
    }    
    
    private static SList<Integer> intervallo( int inf, int sup ) {
        if (inf > sup) {
            return new SList<Integer>();   // restituisce una lista di interi
        } else {
            return intervallo( inf + 1, sup ).cons(inf);
        }
    }
} // class Queens
