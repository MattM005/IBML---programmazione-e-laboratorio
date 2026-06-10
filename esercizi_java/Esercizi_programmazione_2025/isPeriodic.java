

public class isPeriodic {
    public static boolean isPeriodic(double[] seq, int tau) {
        
        if(seq == null || tau <= 0) {
            return false;
        }
        
        if(tau >= seq.length) {
            return true;
        }
        
        for(int i = tau; i < seq.length; i=i+1) {
            if(seq[i] != seq[i - tau]) {
                return false;
            }
        }
        
        return true;
    }        
    
    /*
     * 
     *  k=0  ["","","","","","","","","",""]   ← stringhe vuote
        k=1  ["i","r","r","a","d","i","a","r","e"]  ← singoli caratteri
        k=2  [LPS("ir"), LPS("rr"), ...]
        k=3  [LPS("irr"), LPS("rra"), ...]
        ...
        k=9  [LPS("irradiare")]   ← riga che ti serve, posizione 0
              ↑
            mem[9][0]  → "radar"
     * 
     */
    /*public static String lpsDP(String s) {
        int n = s.length();
        String[][] mem = new String[n + 1][n]; // [n+1][n]: righe = lunghezze 0..n, colonne = posizioni 0..n-1
    
        for (int k = 0; k <= n; k++) {
            for (int i = 0; i <= n - k; i++) {
                
                if (k < 2) {
                    
                    mem[k][i] = s.substring(i, i + k); 
                    
                } else if (s.charAt(i) == s.charAt(i + k - 1)) {
                    
                    mem[k][i] = s.charAt(i) + mem[k - 2][i + 1] + s.charAt(i + k - 1);
                    
                } else {
                    mem[k][i] = longer(mem[k - 1][i], mem[k - 1][i + 1]);
                }
            }
        }
        return mem[n][0]; 
    }*/
}
