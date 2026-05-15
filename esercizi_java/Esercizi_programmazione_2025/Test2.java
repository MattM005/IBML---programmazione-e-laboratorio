
/**
 * Punta a IntSList (usa la classe IntSList)
 */

public class Test2 {
  public static IntSList intervallo(int inf, int sup) {
      if (inf > sup) {
        return IntSList.NULL_INTLIST;    // new IntSList()
      } else {
        return intervallo(inf+1,sup).cons(inf);
      }
  }
}
