
/**
 * es. 13/06/2017 con tecnica bottom up
 * Longest Increasing Subsequence (LIS)
 */
/**public class LisDP {
    public static IntList lisDP( IntList s ) {
        int n = s.length();
        IntList[][] mem = new IntList[n+1][n+1];

        for ( int i=0; i<=n; i++ ) {              // i,j < lunghezza s
            for ( int j=0; j<=n; j++ ) {
                if ( i==0 ) {
                    mem[i][j] = new IntList();      // 1* valore assegnato
                }else if (i < j){
                    mem[i][j] = mem[i-1][j];        // n valore assegnato pos[i-1,j] j:ultima posizione ultimo elemento
                }else{
                    mem[i][j] = longer(mem[i],mem[j]);
                }
            }
        }
        return mem[n][0];
    }
}
**/

/**
public class LisDP {
    public static IntList lisDP(IntList s) {
        int n = s.length();
        IntList[][] mem = new IntList[n+1][n+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    mem[i][j] = new IntList();           // caso base: lista vuota
                } else if (i < j) {
                    mem[i][j] = mem[i-1][j];             // non può terminare a j, allora copia da riga sopra
                } else {
                    mem[i][j] = longer(i, j, s, mem);    // i >= j, calcola la sottoseq. migliore
                }
            }
        }
        return mem[n][0];   // LIS globale
    }
    **/