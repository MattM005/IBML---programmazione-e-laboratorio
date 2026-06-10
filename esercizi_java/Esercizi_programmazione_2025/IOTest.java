import huffman_toolkit.*;

public class IOTest {

    // Duplica un file di testo carattere per carattere
    public static int dupl(String src, String dst) {
        
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);
        int count = 0;
        
        while (in.textAvailable()) {
            //char c = in.readChar();
            //out.writeChar(c);
            String s = in.readCode(7);
            out.writeCode(s);
            count=count+1;
        }
        
        in.close();
        out.close();
        
        return count;
    }
    
    // Calcola le frequenze di tutti i possibili caratteri (0..65535)
    // IOTest.charFreqs("IOTest.java") --> obj con al suo interno 
    //                                   occorrenze carattere cod. ascii
    // freq['\n'] --> es. 95...
    public static int[] charFreqs(String src) {
        int[] freq = new int[65536];  // InputTextFile.CHARS
        
        for(int c=0; c<freq.length; c=c+1) {
            freq[c]=0;
        }
        
        InputTextFile in = new InputTextFile(src);
        
        while (in.textAvailable()) {
            char c = in.readChar();
            freq[c] = freq[c]+1;
        }
        
        in.close();
        
        return freq;
    }
    
    
    /**
     * 
     * 4403 bit
     * però 629 caratteri
     * 
     * allora: 1 byte ha 7 bit utili e 1 bit sprecato
     * quindi 629 * 7 = 4403 bit. 
     * 
     */
    
    /**
     * public static int dupl(String src, String dst) {
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);  // corretto: usa dst

        int count = 0;
        
        while (in.textAvailable()) {
            char c = in.readChar();
            out.writeChar(c);
            count = count + 1;
        }

        in.close();
        out.close();
        
        return count;
    }
     */
    
    /**
     * public static int dupl(String src, String dst) {
        InputTextFile in = new InputTextFile(src);
        OutputTextFile out = new OutputTextFile(dst);  // corretto: usa dst

        int count = 0;
        
        while (in.textAvailable()) {
            String s = in.readTextLine();
            out.writeTextLine(s);
            count = count++;
        }

        in.close();
        out.close();
        
        return count;
    }
     */
    
    
    
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
    
}