/**
  * Determinare n* soluzioni dato n scacchiera.
  * 
  * Board b;
  * int n, i, j;
  * 
  * b = new Board(n)
  * 
  * b.size()             : int
  * b.queensOn()         : int
  * 
  * b.underAttack(i,j)   : Boolean
  * 
  * b.addQueen(i,j)      : Board
  * 
  * b.arrangement()      : String
  * 
**/

import java.util.function.*;

public class Board {
    
    private static final String ROWS = "123456789ABCDEF";
    private static final String COLS = "abcdefghilmno";
    
    private final int size;
    private int queens; 
    private final BiPredicate<Integer, Integer> attack;
    // private final String config;
    private final SList<SList<Integer>> config;

    public Board(int n) {   
       size = n;
       queens = 0;
       attack = (x,y) -> false;    // (lambda (x y) false)
       // config = " ";
       config = new SList<SList<Integer>>();
    }
    
    private Board(Board b, int i, int j) {
        size = b.size();
        queens = b.QueensOn() + 1;
        attack = (x,y) -> ((x == i) || (y == j) ||
                           (x-y == i-j) || (x+y == i+j) ||
                           b.underAttack(x,y));
        // config = b.arrangement() + COLS.charAt(j) + ROWS.charAt(i) + " ";
        SList<Integer> pair = (new SList<Integer>()).cons(j).cons(i);
        config = b.arrangement().cons(pair);
    }

    public int size() {
        return size;
    }

    public int QueensOn() {
        return queens;
    }
    
    public boolean underAttack(int i, int j) {
        return attack.test(i,j);
    }
    
    public SList<SList<Integer>> arrangement() {
        return config;
    }
    
   public Board addQueen(int i, int j) {
        return new Board(this,i,j);
    }

    public String toString() {
        return "[ " + arrangement() + " ]";
    }    
}
