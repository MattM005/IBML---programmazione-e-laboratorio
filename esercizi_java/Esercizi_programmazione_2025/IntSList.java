
/*
     * Protocollo classe IntSList - IDEA FONDAMENTALE - ASTRAZIONE SUI DATI
     * -ci saranno errori runtime --> alcune cose inconsistenti
     * -cose importanti:
     * (protocollo, private, >1costruttore, this, mai static davanti metodi).
     * 
     * 
     * IntSList s;
     * int i;
     * 
     * new IntSList() : IntSList  // null
     * 
     * s.isNull()     : boolean   // (null? s)
     * 
     * s.car()        : int       // (car s)
     * s.cdr()        : IntSList  // (cdr s)
     * 
     * s.cons(i)      : IntSList  // (cons i s)
     * 
     * [ new IntSList(i,s) ] <- il destinatario è una lista
     * 
     * s.length()     : int       // (length s)
     *  
     * s.listRef(i)   : int       // (list-ref s i)
     * 
     * s.equals(i)    : boolean   // (equals s t)
     * 
     * s.append(t)    : IntSList  // (equals s t)
     * 
     * s.reverse()    : IntSList  // (reverse s)
     * 
     * Guarderemo le liste come quegli oggetti che sono accessibili
     * attraverso queste 5 operazioni delineate sopra.
     *
     */
    
public class IntSList {
    
    // Costante
    // static aggiunge un vincolo in più rispetto a final. Ovvero la proprietà è statica a lvl. di classe.
    // ... vale per **tutte le istanze, una volta x tutte.
    public static final IntSList NULL_INTLIST = new IntSList();
    
    // variabili di istanza --> tutte private
    // final --> le var. non potranno essere modificate -> legato ad **1 singola istanza.
    private final boolean empty;
    private final int first;
    private final IntSList rest;
    
    // Costruttore (può essere vuoto, e infiniti purchè ci sia differenza nei parametri)
    public IntSList() {
        empty = true;
        first = 0;
        rest = null;
    }
    
    // Costruttore 2
    public IntSList(int i, IntSList r) {
        empty = false;    //non true altrimenti lista vuota.
        first = i; 
        rest = r;
    }
    
    public boolean isNull() {
        return empty;
    }
    
    public int car() {
        return first;
    }
    
    public IntSList cdr() {
        return rest;         // non è la lista vuota come in scheme...
    }
    
    public IntSList cons(int i) {
        
        return new IntSList(i,this);  // this --> destinatario (non è noto)
    }
    
    public int length() {
        if(isNull()) {
            return 0; 
        } else {
            return 1 + cdr().length();
        }
    }
    
    public String toString() {
        if(isNull() ) {
            return "()";
        } else {
            String ls = "(" + car();
            IntSList r = cdr();
            
            while( !r.isNull() ) {
                ls = ls + " " + r.car();
                r = r.cdr();
            }
            return ls + ")";
        }
    }
}


/**
 * la classe che definisce un tipo, ci consente di creare istanze di tipo liste,
 * ovvero dopo la def. del protocollo i suoi metodi vengono ben definiti.
 * - private = nascosto nella classe.
 * - 
 */


