
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
     * per n>1
     * 
     * R(n) = 1 + R(n-2) + R(n-1)
     */
    
    // Fibonacci
    public static int fib(int n) {
        if(n<2) {
            return 1;
        } else {
            return fib(n-2) + fib(n-1);
        }
    }
}
