
public class StringSList {
    
    private static final StringSList NULL_LIST = new StringSList();
    
    public static StringSList pathsDP(String p, int i, int j) {
        // Matrice dei risultati parziali
        StringSList[][] mem = new StringSList[i + 1][j + 1];  
        
        // Riempimento bottom-up: prima riga e prima colonna (casi base)
        for (int a = 0; a <= i; a++) {
            for (int b = 0; b <= j; b++) {
                if (a == 0 && b == 0) {
                    // Nessun tratto da percorrere: solo percorso vuoto
                    mem[a][b] = NULL_LIST.cons("");
                } else {
                    char c = p.charAt(p.length() - a - b);
                    StringSList listaBasso = NULL_LIST;
                    StringSList listaDestra = NULL_LIST;
    
                    // Se possiamo venire dall'alto (a > 0) e il carattere lo permette
                    if (a > 0 && c != 'R') {
                        listaBasso = map("D", mem[a - 1][b]);
                    }
                    // Se possiamo venire da sinistra (b > 0) e il carattere lo permette
                    if (b > 0 && c != 'D') {
                        listaDestra = map("R", mem[a][b - 1]);
                    }
                    // Unione dei due insiemi di percorsi
                    mem[a][b] = listaBasso.append(listaDestra);
                }
            }
        }
        return mem[i][j];
    }
}
