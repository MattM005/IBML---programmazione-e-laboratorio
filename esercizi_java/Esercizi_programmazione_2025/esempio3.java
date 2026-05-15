
/**
 Esempio3: femminile di un sostantivo
 */
public class esempio3
{
    public static String plurale( String s ) {
        /*
        if ( femminile(s) ) {
            return pluraleSf(s);
        } else {
            return pluraleSm(s);
        }*/
        return (femminile(s) ? pluraleSf(s) : pluraleSm(s));
    }
    
    public static boolean femminile( String s ) {
        return (s.substring(s.length()-1)).equals("a");
        
        // return (s.charAt(s.length()-1)) == 'a');
        
        //    in scheme: charAt->stringRef
        // carattere: 'a'
        // stringa:   "a"
    }
    
    public static String pluraleSm( String sm ) {
        return radiceSost(sm) + "i";   // stringa
    }
    
    public static String pluraleSf( String sm ) {
        return radiceSost(sm) + "e";   // stringa
    }
    
    public static String radiceSost( String s ) {
        return s.substring(0, s.length()-1);   // numero intero
    }
}