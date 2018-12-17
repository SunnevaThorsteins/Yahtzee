
package is.hi.yatzee.vinnsla;

import java.util.Random;

/**
 * Forritar teninga. Hægt er að geyma teninga þannig að ekki 
 * öllum teningum sé kastað. 
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * @date 
 * Háskóli Íslands
 */
public class Teningar {
    private final int [] teningar;      // gildi á teningunum 
    private final boolean[] geymdur;    // geymdir teningar
    
    private  int fjoldi;                // fjöldi teninga 
    private Random rand = new Random();
    static final int MAX = 6;
    static final int MIN = 1;

    /***
     * Smiður fyrir teninga sem býr til fjoldi teninga
     * Frumstillir teninga þannig að þeir hafa allir gildið 
     * 1 og þeir eru ekki geymdir  
     * @param fjoldi 
     */
    public Teningar (int fjoldi) {
        teningar = new int [fjoldi];
        geymdur = new boolean [fjoldi];
        ekkiGeymdir();
    }
    
    /**
     * Nær í fylki sem segir til hvort teningar séu geymdir 
     * @return 
     */
    public boolean[] getGeymdur() {
        return geymdur;
    }
    /**
     * Segir til um hvort i-ti teningur sé geymdur 
     * @param i númer tenings
     * @return  satt ef teningur er geymdur annars ósatt 
     */
    private boolean getGeymdur (int i) {
        return geymdur[i];
    }
    
    public void setGeymdur (int i, boolean b) {
        geymdur[i] = b;
    }
    
    /**
     * Nær í alla teninga 
     * @return 
     */
    public int[] getTeningar() {
        return teningar;
    }
    
     /**
     * Býr til random tölu
     * @return random tölu 
     */
    public int naestaRandomTala() {
        int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
        return randomNum;
    }
    
/**
 * Stillir teningana þannig að enginn er geymdur 
 */
    public void ekkiGeymdir() {
       for (int i=0; i<teningar.length; i++) {
            geymdur[i] = false;
        }
    }

    public void setTeningur(int i, int r) {
        teningar[i] = r;
    }
    
    /**
     * Kastar i-ta teningnum ef hann er ekki geymdur. Ef i-ti teningur
     * er geymdur þá er skilað 0
     * @param i
     * @return 
     */
    public int kasta (int i) {
            int r=0;
            if (!getGeymdur(i)) {
              r = naestaRandomTala();
              teningar [i] = r;
            }
            return r;
    }
}
