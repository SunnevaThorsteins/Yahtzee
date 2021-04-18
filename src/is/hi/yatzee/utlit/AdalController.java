/*
 * The main controller for the yhatzee game. In charge of the functionality for
 * the UI for the yhatzee sheet, dice thows and dice.
 * 
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
    private ValmyndController valmyndController;
    @FXML
    private SetjaNofnDialogController nafnaDialogController;
    @FXML
    private StigataflaController stigataflaController;
    @FXML
    private LeikLokidController leikLokidController;
    @FXML
    private TonlistController tonlistController;
    @FXML
    private ToggleButton ten1; //dice 1 button
    @FXML
    private ToggleButton ten2; //dice 2 button
    @FXML
    private ToggleButton ten3; //dice 3 button
    @FXML
    private ToggleButton ten4; //dice 4 button
    @FXML
    private ToggleButton ten5; //dice 5 button

    @FXML
    private ToggleButton asar; //Aces button
    @FXML
    private ToggleButton tvistar; //Twos button
    @FXML
    private ToggleButton thristar; //Threes button
    @FXML
    private ToggleButton fjarkar; //Fours button
    @FXML
    private ToggleButton fimmur; //Fives button
    @FXML
    private ToggleButton sexur; //Sixes button
    @FXML
    private ToggleButton takaSens; //Chance button
    @FXML
    private ToggleButton thrirEins; //Three of a kind button
    @FXML
    private ToggleButton fjorirEins; //Four of a kind button
    @FXML
    private ToggleButton fulltHus; //Full house button
    @FXML
    private ToggleButton litilRod; //small straight button
    @FXML
    private ToggleButton storRod; //large straight button
    @FXML
    private ToggleButton yahtzee; //yhatzee button
    
    private YatzeeImp leikur;
    
    private final int fjoldiTeninga = 5; //dice amount
    
    private final Teningar yatzeeTeningar = new Teningar(fjoldiTeninga); 

    private final Image [] tenMyndir = new Image[6];//dice photos
    private final String [] MYNDIR = new String []{"one","two","three","four",
        "five","six"}; //dice names array
    private Label[] ollStigBox1; // contains all points for player 1
    private Label[] ollStigBox2; // contains all points for player 2
    private ToggleButton [] allirHnappar; // all option buttons
    
    private ToggleButton [] allirTeningar; // all dices
    @FXML
    private ToggleGroup hnappar; 
    @FXML
    private ImageView tenMynd1; 
    @FXML
    private ImageView tenMynd2; 
    @FXML
    private ImageView tenMynd3; 
    @FXML
    private ImageView tenMynd4; 
    @FXML
    private ImageView tenMynd5; 
    
    @FXML
    private Label asarStig1;  
    @FXML
    private Label tvistarStig1; 
    @FXML
    private Label thristarStig1; 
    @FXML
    private Label fjarkarStig1;
    @FXML
    private Label fimmurStig1; 
    @FXML
    private Label sexurStig1;
    @FXML
    private Label thrirEinsStig1; 
    @FXML
    private Label fjorirEinsStig1;
    @FXML
    private Label litilRodStig1;
    @FXML
    private Label storRodStig1;
    @FXML
    private Label sensinnStig1;
    @FXML
    private Label yahtzeeStig1; 
    @FXML
    private Label asarStig2;
    @FXML
    private Label tvistarStig2; 
    @FXML
    private Label thristarStig2; 
    @FXML
    private Label fjarkarStig2;
    @FXML
    private Label fimmurStig2; 
    @FXML
    private Label sexurStig2; 
    @FXML
    private Label thrirEinsStig2; 
    @FXML
    private Label fjorirEinsStig2;
    @FXML
    private Label litilRodStig2; 
    @FXML
    private Label storRodStig2; 
    @FXML
    private Label sensinnStig2;
    @FXML
    private Label yahtzeeStig2; 
    @FXML
    private Label husStig1; 
    @FXML
    private Label husStig2; 
    @FXML
    public Label notandi1; 
    @FXML
    private Button kastaTening1; // dice button for player 1
    @FXML
    private Label kost1; 
    @FXML
    private Label samtals1; 
    @FXML
    public Label notandi2;
    @FXML
    private Button kastaTening2; // dice button for player 2
    @FXML
    private Label kost2; 
    @FXML
    private Label samtals2; 
    private boolean hnappar1[];// contains which rows player 1 has
    private boolean hnappar2[];// contains which rows player 2 has
    @FXML
    private Label bonusSumma;
    @FXML
    private Label bonus; 
    @FXML
    private Label bonusSummaStig1;
    @FXML
    private Label bonusStig1;
    @FXML
    private Label bonusSummaStig2;
    @FXML
    private Label bonusStig2;
    @FXML
    private Button losaTen;//frees dice
    public ResourceBundle tungumal;
    private final int fjoldiTakka = 13; //total numbers of option dice
    private boolean kastad = false; // wheather dices have been thrown this turn
    private boolean synaUppl = false;
    
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
        
        kastaTening1.disableProperty().
                bind(kost1.textProperty().
                        isEqualTo("0"));
        
        kastaTening2.disableProperty().
                bind(kost2.textProperty().
                        isEqualTo("0"));
    }
    
    // A popup I disabled since it was annoying and wasn't adding to the user experience.
    public void upplysingar(){
        synaUppl = false;
        Alert leikreglur = new Alert (Alert.AlertType.INFORMATION);
        leikreglur.setHeaderText(tungumal.getString("Komin"));
        leikreglur.setContentText(tungumal.getString("Grunnur"));
        leikreglur.showAndWait();
    } 

    // resets all point lables
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
    
    // initilaizes option buttons to be clickable 
    public void initHnappar(){
        for(int i = 0; i < 7; i++){
            allirHnappar[i].setDisable(false);
            allirHnappar[i].setSelected(false);
        }
    }
    
    // calculate total points and update label
    public void samtalsStig(){
        leikur.reiknaSumma();
        if(leikur.nuverandi == 1) {
            samtals1.setText(Integer.toString(leikur.getSumma1()));
        } else {
            samtals2.setText(Integer.toString(leikur.getSumma2()));
        }
    }
    
    // finds which option was chosen and starts next turn
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
    
    // adds points to the correct label
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
    
    // finds which option button was pressed
    private int finnaStad(ToggleButton button){
        for(int i = 0; i < allirHnappar.length; i++){
            if(button == allirHnappar[i]){
                return i;
            }
        }
        return 0;
    }
    
    //throws dices and updates the UI for number of throws left
    @FXML
    private void kastaHandler(ActionEvent event) {
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
    
    // throws dices, avoiding re rolling saved dice. Updates the dice UI
    private void kastaTening(){
        for(int i = 0; i < fjoldiTeninga; i++){
            int t = yatzeeTeningar.kasta(i);
             if ( t != 0){
                 setImageView(i, t);
             }
             
        }
        setjaMoguleika();
    }

    // updates dice UI 
     private void setImageView(int i, int t) {
         ImageView imageView = new ImageView (tenMyndir[t-1]);
         allirTeningar[i].setGraphic(imageView);
         imageView.setPreserveRatio(true);
         imageView.setFitWidth(76);
         imageView.setFitHeight(82); 
    }
    
    //handler for which dice was thrown
    @FXML
    private void tenHandler(ActionEvent event) {
        ToggleButton dice = (ToggleButton)event.getSource();
        dice.setDisable(true);
        for(int i = 0; i < fjoldiTeninga; i++){
            if (dice == allirTeningar[i]){yatzeeTeningar.setGeymdur(i, true);}
        }
    }
    
    //starts new round
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
    
    //sets which rows players have already used
    private void setjaHnappa(){
        for(int i = 0; i < allirHnappar.length; i++){
            if(leikur.nuverandi == 1){
                allirHnappar[i].setDisable(hnappar2[i]);
            } else {
                allirHnappar[i].setDisable(hnappar1[i]);
            }
        }
    }
    
    //starts a new game
    public void nyrLeikur(){
        leikur = new YatzeeImp();
        for(int i = 0; i < 5; i++){
            allirTeningar[i].setDisable(false);
        }
        nullstillaStigin();
        nullstillaUmferdir();
        nyUmferd();
    }
    
    //round ends
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
    
    // initializes current round
    private void nullstillaUmferdir(){
        leikur.nuverandi = 1;
        kost2.setText("0");
        hnappar1 = new boolean[13];
        hnappar2 = new boolean[13];
    }
    
    // changes player names
    public void breytaNofnum(){
        nafnaDialogController.nofnLeikmanna();
        leikur.setNafnLeikmanns1(notandi1.getText());
        leikur.setNafnLeikmanns2(notandi2.getText());
    }
    
    //shows score board
    public void sjaToflu(){
        stigataflaController.birtaToflu();
    }
    
    //shows game over dialog
    public void leikurBuin(){
        setjaSigurvegaraiToflu(leikur.finnaSigurvegara());
        leikLokidController.birtaLeikLokid();
    }
    
    //adds the winner to game over dialog and adds them to the scoreboard
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
    
    // handler for freeing dice
    @FXML
    private void losaTenHandler(ActionEvent event) {
        for(int i = 0; i < fjoldiTeninga; i++){
            yatzeeTeningar.setGeymdur(i, false);
            allirTeningar[i].setDisable(false);
            allirTeningar[i].setSelected(false);
        }
    }
    
   //shows the music player
    void spilari() {
        tonlistController.synaSpilara();
    }
    
    // shows the user the possible points they would get if they chose an option
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
    
    // resets the options
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
     * adds possible points to the game board
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