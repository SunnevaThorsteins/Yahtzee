/*
 * Aðal controllerinn fyrir yahtzee spil þar sem allt er tengt saman. Sér um 
 * helstu virknina til dæmis að byggja upp viðmótið fyrir yahtzee blaðið, köstin
 * og teningana.
 */
package is.hi.yatzee.utlit;

import is.hi.yatzee.vinnsla.Teningar;
import is.hi.yatzee.vinnsla.YatzeeImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class AdalController implements Initializable {
    @FXML
    private ValmyndController valmyndController; //breyta sem heldur utan um 
    //valmyndcontroller
    @FXML
    private SetjaNofnDialogController nafnaDialogController; //heldur utan um
    //SetjaNofnDialogController
    @FXML
    private StigataflaController stigataflaController; //heldur utan um
    //StigataflaController
    @FXML
    private LeikLokidController leikLokidController; //heldur utan um
    //LeikLokidController
    @FXML
    private TonlistController tonlistController; //heldur utan um 
    //TonlistController
    private Button kastaTening; //breyta fyrir kasta takkann
    @FXML
    private ToggleButton ten1; //heldur utan um tening 1
    @FXML
    private ToggleButton ten2; //heldur utan um tening 2
    @FXML
    private ToggleButton ten3; //heldur utan um tening 3
    @FXML
    private ToggleButton ten4; //heldur utan um tening 4
    @FXML
    private ToggleButton ten5; //heldur utan um tening 5

    @FXML
    private ToggleButton asar; //heldur utan um ásar takkann
    @FXML
    private ToggleButton tvistar; //heldur utan um tvistar takkann
    @FXML
    private ToggleButton thristar; //heldur utan um þristar takkann
    @FXML
    private ToggleButton fjarkar; //heldur utan um fjarkar takkann
    @FXML
    private ToggleButton fimmur; //heldur utan um fimmur takkann
    @FXML
    private ToggleButton sexur; //heldur utan um sexur takkann
    @FXML
    private ToggleButton takaSens; //heldur utan um taka sénsinn takkann
    @FXML
    private ToggleButton thrirEins; //heldur utan um 3 eins takkan
    @FXML
    private ToggleButton fjorirEins; //heldur utan um 4 eins takkan
    @FXML
    private ToggleButton fulltHus; //heldur utan um fullt hús takkan
    @FXML
    private ToggleButton litilRod; //heldur utan um litla röð takkan
    @FXML
    private ToggleButton storRod; //heldur utan um stóra röð takkan
    @FXML
    private ToggleButton yahtzee; //heldur utan um yahtzee takkan
    
    private YatzeeImp leikur; //heldur utan um YatzeeImp
    
    private final int fjoldiTeninga = 5; //fjöldi teninga í leik
    
    private final Teningar yatzeeTeningar = new Teningar(fjoldiTeninga); 
    //tengir við Teninga klasann

    private final Image [] tenMyndir = new Image[6];//fylki fyrir teninga myndir
    private final String [] MYNDIR = new String []{"one","two","three","four",
        "five","six"}; //Strengja fylki fyrir nöfn teninga
    private int fjoldi; //fjöldi kasta eftir í umferð 
    private Label[] ollStigBox1; //fylki sem inniheldur öll stig fyrir leikm. 1
    private Label[] ollStigBox2; //fylki sem inniheldur öll stig fyrir leikm. 2 
    private ToggleButton [] allirHnappar; //fylki sem inniheldur alla hnappa
    /*private Label sensinnStig; //heldur utan um label fyrir taka sénsinn
    private Label kost; *///heldur utan um köstin sem eru eftir
    
    private ToggleButton [] allirTeningar; //fylki fyrir alla teninga
    @FXML
    private ToggleGroup hnappar; //heldur utan um hópinn af línum
    @FXML
    private ImageView tenMynd1; //heldur utan um myndina á teningi 1
    @FXML
    private ImageView tenMynd2; //heldur utan um myndina á teningi 2
    @FXML
    private ImageView tenMynd3; //heldur utan um myndina á teningi 3
    @FXML
    private ImageView tenMynd4; //heldur utan um myndina á teningi 4
    @FXML
    private ImageView tenMynd5; //heldur utan um myndina á teningi 5
    
    private ImageView [] allarTenMyndir; //fylki fyrir allar myndir á teningum
    @FXML
    private Label asarStig1; //Sýnir stigin fyrir ása fyrir leikmann 1 
    @FXML
    private Label tvistarStig1; //Sýnir stigin fyrir tvista fyrir leikmann 1
    @FXML
    private Label thristarStig1; //Sýnir stigin fyrir þrista fyrir leikmann 1
    @FXML
    private Label fjarkarStig1; //Sýnir stigin fyrir fjarka fyrir leikmann 1
    @FXML
    private Label fimmurStig1; //Sýnir stigin fyrir fimmur fyrir leikmann 1
    @FXML
    private Label sexurStig1; //Sýnir stigin fyrir sexur fyrir leikmann 1
    @FXML
    private Label thrirEinsStig1; //Sýnir stigin fyrir 3 eins fyrir leikmann 1
    @FXML
    private Label fjorirEinsStig1; //Sýnir stigin fyrir 4 eins fyrir leikmann 1
    @FXML
    private Label litilRodStig1;//Sýnir stigin fyrir litla röð fyrir leikmann 1
    @FXML
    private Label storRodStig1;//Sýnir stigin fyrir stóra röð fyrir leikmann 1
    @FXML
    private Label sensinnStig1;//Sýnir stigin fyrir taka sénsin fyrir leikmann 1
    @FXML
    private Label yahtzeeStig1; //Sýnir stigin fyrir yahtzee fyrir leikmann 1
    @FXML
    private Label asarStig2; //Sýnir stigin fyrir ása fyrir leikmann 2
    @FXML
    private Label tvistarStig2; //Sýnir stigin fyrir tvista fyrir leikmann 2
    @FXML
    private Label thristarStig2; //Sýnir stigin fyrir þrista fyrir leikmann 2
    @FXML
    private Label fjarkarStig2;//Sýnir stigin fyrir fjarka fyrir leikmann 2
    @FXML
    private Label fimmurStig2; //Sýnir stigin fyrir fimmur fyrir leikmann 2
    @FXML
    private Label sexurStig2; //Sýnir stigin fyrir sexur fyrir leikmann 2
    @FXML
    private Label thrirEinsStig2; //Sýnir stigin fyrir 3 eins fyrir leikmann 2
    @FXML
    private Label fjorirEinsStig2; //Sýnir stigin fyrir 4 eins fyrir leikmann 2
    @FXML
    private Label litilRodStig2; //Sýnir stigin fyrir  fyrir leikmann 2
    @FXML
    private Label storRodStig2; //Sýnir stigin fyrir stóra röð fyrir leikmann 2
    @FXML
    private Label sensinnStig2;//Sýnir stigin fyrir taka séns fyrir leikmann 2
    @FXML
    private Label yahtzeeStig2; //Sýnir stigin fyrir yahtzee fyrir leikmann 2
    @FXML
    private Label husStig1; //Sýnir stigin fyrir fullt hús fyrir leikmann 1
    @FXML
    private Label husStig2; //Sýnir stigin fyrir fullt hús fyrir leikmann 2
    @FXML
    public Label notandi1; //Sýnir nafn notanda 1
    @FXML
    private Button kastaTening1; //kastar teningum fyrir notanda 1
    @FXML
    private Label kost1; //hversu mörg köst leikmaður 1 á eftir í umferðinni
    @FXML
    private Label samtals1; //Sýnir hve mörg stig leikmaður 1 er með samtals
    @FXML
    public Label notandi2; //Sýnir nafn notanda 2
    @FXML
    private Button kastaTening2; //kastar teningum fyrir notanda 2
    @FXML
    private Label kost2; //hversu mörg köst leikmaður 2 á eftir í umferðinni
    @FXML
    private Label samtals2; //Sýnir hve mörg stig leikmaður 2 er með samtals
    
    private boolean hnappar1[];//heldur utan um hvaða raðir spilari1 er búin með
    private boolean hnappar2[];//heldur utan um hvaða raðir spilari2 er búin með
    @FXML
    private Label bonusSumma;//markar röðina þar sem summa stiga f. fyrstu 6 
    //raðir sést
    @FXML
    private Label bonus; //markar röðina þar sem bónus f. fyrstu 6 raðir sést
    @FXML
    private Label bonusSummaStig1;//sýnir summu stiga fyrir fyrstu 6 raðirnar
    //fyrir spilara 1
    @FXML
    private Label bonusStig1;//sýnir bónus fyrir fyrstu 6 raðirnar fyrir 
    //spilara 1
    @FXML
    private Label bonusSummaStig2;//sýnir summu stiga fyrir fyrstu 6 raðirnar
    //fyrir spilara 2
    @FXML
    private Label bonusStig2;//sýnir bónus fyrir fyrstu 6 raðirnar fyrir 
    //spilara 2
    @FXML
    private Button losaTen;//takki sem losar teninga
    public ResourceBundle tungumal;//tenging við alþjóðlegt notendaviðmót
    private final int fjoldiTakka = 13;
    private boolean kastad = false; //hvort búið sé að kasta teningi í umferð
    private boolean synaUppl = true; //hvort búið sé að sýna upplýsingar
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tungumal = rb;
        valmyndController.initAdal(this);
        nafnaDialogController.initAdal(this);
        stigataflaController.initAdal(this);
        leikLokidController.initAdal(this);
        tonlistController.init(this);
        leikur = new YatzeeImp();
        leikur.setNafnLeikmanns1(tungumal.getString("Leikmadur1"));
        leikur.setNafnLeikmanns2(tungumal.getString("Leikmadur2"));
        notandi1.setText(leikur.getNafnLeikmanns1());
        notandi2.setText(leikur.getNafnLeikmanns2());
       for (int i = 0; i < 6; i++) {
            tenMyndir[i] = new Image(getClass().getResourceAsStream("teningar/"+
                    MYNDIR[i]+".png"));
        }
       hnappar1 = new boolean [fjoldiTakka];
       hnappar2 = new boolean [fjoldiTakka];
       
        ollStigBox1 = new Label [] {asarStig1, tvistarStig1, thristarStig1, 
            fjarkarStig1, fimmurStig1, sexurStig1, thrirEinsStig1, 
            fjorirEinsStig1, husStig1, litilRodStig1, storRodStig1, sensinnStig1, 
            yahtzeeStig1};
        
        ollStigBox2 = new Label [] {asarStig2, tvistarStig2, thristarStig2, 
            fjarkarStig2, fimmurStig2, sexurStig2, thrirEinsStig2, 
            fjorirEinsStig2, husStig2, litilRodStig2, storRodStig2, sensinnStig2, 
            yahtzeeStig2};
        
        allirHnappar = new ToggleButton [] {asar, tvistar, thristar, 
            fjarkar, fimmur, sexur, thrirEins, fjorirEins, fulltHus, litilRod, 
            storRod, takaSens, yahtzee};
        allirTeningar = new ToggleButton [] {ten1, ten2, ten3, ten4, ten5};
        allarTenMyndir = new ImageView [] {tenMynd1, tenMynd2, tenMynd3, 
            tenMynd4, tenMynd5};
        
        kastaTening1.disableProperty().
                bind(kost1.textProperty().
                        isEqualTo("0"));
        
        kastaTening2.disableProperty().
                bind(kost2.textProperty().
                        isEqualTo("0"));
    }
    
    public void upplysingar(){
        synaUppl = false;
        Alert leikreglur = new Alert (Alert.AlertType.INFORMATION);
        leikreglur.setHeaderText(tungumal.getString("Komin"));
        leikreglur.setContentText(tungumal.getString("Grunnur"));
        leikreglur.showAndWait();
    }

    /*
    nullstillir label fyrir stigin og samtals stig
    */
    public void nullstillaStigin(){
        for(int i = 0; i < ollStigBox1.length; i++){
            ollStigBox1[i].setText("0");
            samtals1.setText("0");
        }
        for(int i = 0; i < ollStigBox2.length; i++){
            ollStigBox2[i].setText("0");
            samtals2.setText("0");
        }
        bonusSummaStig1.setText("");
        bonusSummaStig2.setText("");
        bonusStig1.setText("");
        bonusStig2.setText("");
        
    }
    
    /*
    gerir mögulegt að ýta aftur á hnappa
    */
    public void initHnappar(){
        for(int i = 0; i < 7; i++){
            allirHnappar[i].setDisable(false);
            allirHnappar[i].setSelected(false);
        }
    }
    
    /*
    reiknar samtals stig og setur þau á label
    */
    public void samtalsStig(){
        leikur.reiknaSumma();
        if(leikur.nuverandi == 1) {
            samtals1.setText(Integer.toString(leikur.getSumma1()));
        } else {
            samtals2.setText(Integer.toString(leikur.getSumma2()));
        }
    }
    
    /*
    finnur hvaða hnapp var ýtt á, setur stig og byrjar nýja umferð
    */
    @FXML
    private void toggleHandler(ActionEvent event) {
        ToggleButton button = (ToggleButton)event.getSource();
        button.setDisable(true);
        button.setSelected(false);
        int numer = finnaStad(button);
        if(leikur.nuverandi == 1){
            hnappar1[numer] = true;
            kost1.setText("0");
        } else {
            hnappar2[numer] = true;
            kost2.setText("0");
        }
        nullStillaMoguleika();
        setStig(numer);
        umferdLokid();
    }
    
    /*
    setur stigin á label fyrir hnapp sem var ýtt á
    */
    private void setStig(int numer){
        int stig;
        if(numer < 6){
            stig = leikur.reiknaEinfalda(numer, this.yatzeeTeningar);
        } else if(numer == 6 || numer == 7) {
            stig = leikur.reiknaEins(numer, this.yatzeeTeningar);
        } else if(numer == 8) {
            stig = leikur.reiknaFulltHus(numer, this.yatzeeTeningar);
        } else if (numer == 9 ||numer == 10) {
            stig = leikur.reiknaRod(numer, this.yatzeeTeningar);
        } else if (numer == 11){
            stig = leikur.reiknaSensinn(numer, this.yatzeeTeningar);
        } else {
            stig = leikur.reiknaYahtzee(numer, this.yatzeeTeningar);
        }
        if(leikur.nuverandi == 1) {
            ollStigBox1[numer].setText(Integer.toString(stig));
            if(leikur.buinBonus1 == false && leikur.einfaldir1 == 6) {
                bonusSummaStig1.setText(Integer.toString(leikur.bonusSumma()));
                bonusStig1.setText(Integer.toString(leikur.bonus(leikur
                        .bonusSumma())));
            } 
        } else {
            ollStigBox2[numer].setText(Integer.toString(stig));
            if(leikur.buinBonus2 == false && leikur.einfaldir2 == 6) {
                bonusSummaStig2.setText(Integer.toString(leikur.bonusSumma()));
                bonusStig2.setText(Integer.toString(leikur.bonus(leikur
                        .bonusSumma())));
            }
        }
        samtalsStig();
    }
    /*
    finnur númer hvað Togglebutton er í fylkinu allirHnappar
    */
    private int finnaStad(ToggleButton button){
        for(int i = 0; i < allirHnappar.length; i++){
            if(button == allirHnappar[i]){
                return i;
            }
        }
        return 0;
    }
    
    /*
    Handler sem sér um að kasta teningunum. Ef teningur hefur verið geymdur er
    honum ekki kastað. Þegar engin köst eru eftir í umferð er takkinn gerður
    óvirkur þar til næsta umferð hefst.
    */
    @FXML
    private void kastaHandler(ActionEvent event) {
        if(synaUppl)
            upplysingar();
        if(kastad){
            nyUmferd();
            kastad = false;
        }
        if(leikur.nuverandi == 1) {
            int text = Integer.parseInt(kost1.getText())-1;
            kost1.setText(Integer.toString(text));
            kastaTening();
        } else {
            int text = Integer.parseInt(kost2.getText())-1;
            kost2.setText(Integer.toString(text));
            kastaTening();
        }
    }
    
    /**
     * kastar teningi og sýnir notanda möguleikana sem eru í boði
     */
    private void kastaTening(){
        for(int i = 0; i < fjoldiTeninga; i++){
            int t = yatzeeTeningar.kasta(i);
             if ( t != 0){
                 setImageView(i, t);
             }
             
        }
        setjaMoguleika();
    }

    /*
    setur imageView og passar að það sé af réttri stærð
    */
     private void setImageView(int i, int t) {
         ImageView imageView = new ImageView (tenMyndir[t-1]);
         allirTeningar[i].setGraphic(imageView);
         imageView.setPreserveRatio(true);
         imageView.setFitWidth(76);
         imageView.setFitHeight(82); 
    }
    
     /*
     handler sem geymir þann tening sem var ýtt á
     */

    @FXML
    private void tenHandler(ActionEvent event) {
        ToggleButton dice = (ToggleButton)event.getSource();
        dice.setDisable(true);
        for(int i = 0; i < fjoldiTeninga; i++){
            if (dice == allirTeningar[i]){yatzeeTeningar.setGeymdur(i, true);}
        }
    }
    
    /*
    byrjar nýja umferð fyrir
    */
    private void nyUmferd(){
        boolean leikLokid = leikur.vinningur();
        if(leikLokid)
            leikurBuin();
        if(leikur.nuverandi == 1) {
            for(int i = 0; i < fjoldiTeninga; i++){
                allirTeningar[i].setDisable(false);
                allirTeningar[i].setSelected(false);
            }
            setjaHnappa();
            leikur.nuverandi = 2;
        } else if (leikur.nuverandi == 2) {
            for(int i = 0; i < fjoldiTeninga; i++){
                allirTeningar[i].setDisable(false);
                allirTeningar[i].setSelected(false);
            }
            setjaHnappa();
            leikur.nuverandi = 1;
        }
    }
    /*
    Stillir hvaða raðir spilari 1 er búinn að nota og hvaða raðir spilari 2 er 
    búinn að nota.
    */
    private void setjaHnappa(){
        for(int i = 0; i < allirHnappar.length; i++){
            if(leikur.nuverandi == 1){
                allirHnappar[i].setDisable(hnappar2[i]);
            } else {
                allirHnappar[i].setDisable(hnappar1[i]);
            }
        }
    }
    
    /*
    Byrjar nýjan leik
    */
    public void nyrLeikur(){
        leikur = new YatzeeImp();
        for(int i = 0; i < 5; i++){
            allirTeningar[i].setDisable(false);
        }
        nullstillaStigin();
        nullstillaUmferdir();
        nyUmferd();
    }
    
    public void umferdLokid(){
        kastad = true;
        for(int i = 0; i < allirHnappar.length;i++){
            allirHnappar[i].setDisable(true);
        }
        yatzeeTeningar.ekkiGeymdir();
        if(leikur.nuverandi == 1){
            kost2.setText("3");
        } else {
            kost1.setText("3");
        }
    }
    
    /**
     * núll stillir umferð með því að stilla núverandi leikmann og nullstilla
     * fylki fyrir hnappana
     */
    private void nullstillaUmferdir(){
        leikur.nuverandi = 1;
        kost2.setText("0");
        hnappar1 = new boolean[13];
        hnappar2 = new boolean[13];
    }
    
    /*
    Breytir nöfnum leikmanna í það sem þeir setja inn, 
    hlekkurinn á mylli valmyndar og nafnaDialogController
    */
    public void breytaNofnum(){
        nafnaDialogController.nofnLeikmanna();
        leikur.setNafnLeikmanns1(notandi1.getText());
        leikur.setNafnLeikmanns2(notandi2.getText());
    }
    
    /*
    Birtir stigatöflu.
    */
    public void sjaToflu(){
        stigataflaController.birtaToflu();
    }
    
    /*
    Birtir leikLokid dialog eftir að sigurvegari er settur
    */
    public void leikurBuin(){
        setjaSigurvegaraiToflu(leikur.finnaSigurvegara());
        leikLokidController.birtaLeikLokid();
    }
    
    /*
    Setur sigurvegara á leikLokid dialog og setur upplýsingar í stigatöfluna
    */
    private void setjaSigurvegaraiToflu(int v){
        if(v == 1){
            leikLokidController.sigur(leikur.getNafnLeikmanns1());
                    stigataflaController.setjaItoflu(leikur.getNafnLeikmanns1(),
                            Integer.toString(leikur.getSumma1()), 
                            leikur.getNafnLeikmanns2(), 
                            Integer.toString(leikur.getSumma2()));
        } else {
            leikLokidController.sigur(leikur.getNafnLeikmanns2());
            stigataflaController.setjaItoflu(leikur.getNafnLeikmanns2(), Integer
                .toString(leikur.getSumma2()), leikur.getNafnLeikmanns1(), 
                Integer.toString(leikur.getSumma1()));
        }
    }
    /*
    Losar geymda teninga
    */
    @FXML
    private void losaTenHandler(ActionEvent event) {
        for(int i = 0; i < fjoldiTeninga; i++){
            yatzeeTeningar.setGeymdur(i, false);
            allirTeningar[i].setDisable(false);
            allirTeningar[i].setSelected(false);
        }
    }
    
    /*
    Sýnir tónlistar spilarann
    */
    void spilari() {
        tonlistController.synaSpilara();
    }
    
    /**
     * Sýnir notanda þá möguleika sem eru í boði af stigum á borðinu
     */
    void setjaMoguleika(){
        for(int i = 0; i < fjoldiTakka; i++){
            if(leikur.nuverandi == 1) {
                if(!hnappar1[i]) {
                    leikur.mogulegStig = true;
                    setMogulegStig(i);
                    ollStigBox1[i].setTextFill(Color.web("#ff0000"));
                }
            }
            else {
                if(!hnappar2[i]){
                    leikur.mogulegStig = true;
                    setMogulegStig(i);
                    ollStigBox2[i].setTextFill(Color.web("#ff0000"));
                }
            }
        }
        leikur.mogulegStig = false;
    }
    
    /**
     * tekur möguleikana í burtu og setur borðið eins og það á að vera 
     * fyrir vinnsluna
     */
    private void nullStillaMoguleika() {
        if(leikur.nuverandi == 1) {
            for(int i = 0; i < ollStigBox1.length; i++){
                ollStigBox1[i].setTextFill(Color.web("#000000"));
                if(!hnappar1[i]) {
                    ollStigBox1[i].setText("0");
                    leikur.stigin1[i] = 0;
                }
            }
        } else {
            for(int i = 0; i < ollStigBox2.length; i++){
                ollStigBox2[i].setTextFill(Color.web("#000000"));
                if(!hnappar2[i]) {
                    ollStigBox2[i].setText("0");
                    leikur.stigin2[i] = 0;
                }
            }
        }
    }
    
    /**
     * setur möguleg stig inn á borðið
     * @param numer 
     */
    private void setMogulegStig(int numer) {
        int stig;
        if(numer < 6){
            stig = leikur.reiknaEinfalda(numer, this.yatzeeTeningar);
        } else if(numer == 6 || numer == 7) {
            stig = leikur.reiknaEins(numer, this.yatzeeTeningar);
        } else if(numer == 8) {
            stig = leikur.reiknaFulltHus(numer, this.yatzeeTeningar);
        } else if (numer == 9 ||numer == 10) {
            stig = leikur.reiknaRod(numer, this.yatzeeTeningar);
        } else if (numer == 11){
            stig = leikur.reiknaSensinn(numer, this.yatzeeTeningar);
        } else {
            stig = leikur.reiknaYahtzee(numer, this.yatzeeTeningar);
        }
        if(leikur.nuverandi == 1) {
            ollStigBox1[numer].setText(Integer.toString(stig));
        } else if(leikur.nuverandi == 2){
            ollStigBox2[numer].setText(Integer.toString(stig));
        }
    }
}