
package is.hi.yatzee.vinnsla;

/**
 * Interface fyrir stigablað fyrir einfaldan yatzee leik.
 * 
 * @author Ebba Þóra Hvannberg ebba@hi.is
 * @date
 * Háskóli Íslands
 */
public interface Yatzee {

    String getNafnLeikmanns1();
    String getNafnLeikmanns2();

    /**
     * Nær í summuna af stigunum fyrir leikmann 1 á blaðinu
     * @return summan1 af stigunum
     */
    int getSumma1();
    
    /**
     * Nær í summuna af stigunum fyrir leikmann 2 á blaðinu
     * @return summan2 af stigunum
     */
    int getSumma2();

    /**
     * Reiknar út útkomuna úr reit i (ásar, tvistar, ...
     * sexur) á Yatzeeblaði miðað
     * við stöðu teninganna í breytu teningar
     * @param teningar  teningarnir á borðinu
     * @param i lína á yatzee blaðinu
     * @return
     */
    int reiknaEinfalda(int i, Teningar teningar);

    /***
     * Reiknar út stigin fyrir taka sénsinn út frá teningunum
     * @param teningar
     * @return stigin
     */
    int reiknaSensinn(int n, Teningar teningar);
    
    /**
     * reiknar út 3 eða 4 eins séu þeir til staðar
     * sem spilarinn fær.
     * @param teningar
     * @return stigin
     */
    int reiknaEins(int n, Teningar teningar);
    
    /**
     * reiknar út stóra og litla röð
     * @param n
     * @param teningar
     * @return stigin
     */

    int reiknaRod(int n, Teningar teningar);
    
    /**
     * reiknar út stigin fyrir yahtzee
     * @param teningar
     * @return stigin
     */
    int reiknaYahtzee(int n, Teningar teningar);
    
    /**
     * reiknar út stigin fyrir fullt hús
     * @param teningar
     * @return stigin
     */
    
    int reiknaFulltHus(int n, Teningar teningar);
    
    /**
     * Reiknar út summu fyrstu 6 raðanna
     * @return stigin
     */
    int bonusSumma();
    
    /**
     * Ef summa fyrstu raðanna er meira en 63 stig fær spilarinn 35 stiga bónus
     * @param s
     * @return stigin
     */
    int bonus(int s);
    
    /**
     * Athugar hvort leiknum sé lokið og vinningsahafi sé komin
     * @return hvort vinningur sé komin
     */
    boolean vinningur();
    
    /**
     * Finnur hvor spilarinn vann eftir því hvor var með fleiri stig. Ef 
     * jafnteflu er random sigurvegari valinn
     * @return sigurvegari
     */
    int finnaSigurvegara();

    /**
     * Reiknar summu stigana á Yatzee blaði
     * @return
     */
    int reiknaSumma();
    
    /*
    Setur nafn leikmanns 1
    */
    void setNafnLeikmanns1(String nafnLeikmanns);
    /*
    Setur nafn leikmanns 2
    */
    void setNafnLeikmanns2(String nafnLeikmanns);
}
