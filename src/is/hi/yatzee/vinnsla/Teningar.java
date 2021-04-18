
package is.hi.yatzee.vinnsla;

import java.util.Random;

/**
 * @author Ebba Þóra Hvannberg ebba@hi.is 
 * @date 
 * University of Iceland
 */
public class Teningar {
    private final int [] teningar;      // value of dice
    private final boolean[] geymdur;    // stored dices
    
    private  int fjoldi;                // number of dice
    private Random rand = new Random();
    static final int MAX = 6;
    static final int MIN = 1;

    public Teningar (int fjoldi) {
        teningar = new int [fjoldi];
        geymdur = new boolean [fjoldi];
        ekkiGeymdir();
    }
    // getStored
    public boolean[] getGeymdur() {
        return geymdur;
    }
    // getStored given a dice
    private boolean getGeymdur (int i) {
        return geymdur[i];
    }
    // setStored
    public void setGeymdur (int i, boolean b) {
        geymdur[i] = b;
    }
    // getDice
    public int[] getTeningar() {
        return teningar;
    }
    // nextRandomNumber
    public int naestaRandomTala() {
        int randomNum = rand.nextInt((MAX - MIN) + 1) + MIN;
        return randomNum;
    }
    // not stored
    public void ekkiGeymdir() {
       for (int i=0; i<teningar.length; i++) {
            geymdur[i] = false;
        }
    }
    // setDice given a dice and value
    public void setTeningur(int i, int r) {
        teningar[i] = r;
    }
    // throw dice
    public int kasta (int i) {
            int r=0;
            if (!getGeymdur(i)) {
              r = naestaRandomTala();
              teningar [i] = r;
            }
            return r;
    }
}
