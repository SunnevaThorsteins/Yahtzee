/**
 * Stigatafla sem heldur utan hvor leikmaður vann, hver stigin hans voru, hver 
 * mótspilarinn var og hver stigin hans voru. Getur haldið utan um allt að 8 
 * leiki en þegar það er komið verður notandi að hreinsa töfluna ef hann vill 
 * láta halda áfram að skrá leikina.  
 */
package is.hi.yatzee.utlit;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sunneva
 */
public class StigataflaController implements Initializable {
    
    @FXML
    private Label sigur1; //heldur utan um sigurvegara úr leik 1
    @FXML
    private Label sigurStig1; //heldur utan um stig sigurvegara 1
    @FXML
    private Label aMoti1; //heldur utan um hverjum kept var á móti í leik 1
    @FXML
    private Label aMotiStig1; //heldur utan um stig mótspilara í leik 1
    @FXML
    private Label sigur2; //heldur utan um sigurvegara úr leik 2
    @FXML
    private Label sigurStig2; //heldur utan um stig sigurvegara 2
    @FXML
    private Label aMoti2; //heldur utan um hverjum kept var á móti í leik 2
    @FXML
    private Label aMotiStig2; //heldur utan um stig mótspilara í leik 2
    @FXML
    private Label sigur3; //heldur utan um sigurvegara úr leik 3
    @FXML
    private Label sigurStig3; //heldur utan um stig sigurvegara 3
    @FXML
    private Label aMoti3; //heldur utan um hverjum kept var á móti í leik 3
    @FXML
    private Label aMotiStig3; //heldur utan um stig mótspilara í leik 3
    @FXML
    private Label sigur4; //heldur utan um sigurvegara úr leik 4
    @FXML
    private Label sigurStig4; //heldur utan um stig sigurvegara 4
    @FXML
    private Label aMoti4; //heldur utan um hverjum kept var á móti í leik 4
    @FXML
    private Label aMotiStig4; //heldur utan um stig mótspilara í leik 4
    @FXML
    private Label sigur5; //heldur utan um sigurvegara úr leik 5
    @FXML
    private Label sigurStig5; //heldur utan um stig sigurvegara 5
    @FXML
    private Label aMoti5; //heldur utan um hverjum kept var á móti í leik 5
    @FXML
    private Label aMotiStig5; //heldur utan um stig mótspilara í leik 5
    @FXML
    private Label sigur6; //heldur utan um sigurvegara úr leik 6
    @FXML
    private Label sigurStig6; //heldur utan um stig sigurvegara 6
    @FXML
    private Label aMoti6; //heldur utan um hverjum kept var á móti í leik 6
    @FXML
    private Label aMotiStig6; //heldur utan um stig mótspilara í leik 6
    @FXML
    private Label sigur7; //heldur utan um sigurvegara úr leik 7
    @FXML
    private Label sigurStig7; //heldur utan um stig sigurvegara 7
    @FXML
    private Label aMoti7; //heldur utan um hverjum kept var á móti í leik 7
    @FXML
    private Label aMotiStig7; //heldur utan um stig mótspilara í leik 7
    @FXML
    private Label sigur8; //heldur utan um sigurvegara úr leik 8
    @FXML
    private Label sigurStig8; //heldur utan um stig sigurvegara 8
    @FXML
    private Label aMoti8; //heldur utan um hverjum kept var á móti í leik 8
    @FXML
    private Label aMotiStig8; //heldur utan um stig mótspilara í leik 8
    @FXML
    private AnchorPane sTafla; //heldur utan um diloginn
    
    private int stada = 1; //heldur utan um númer leiks
    
    private AdalController adalController; //tenging við aðal controller
    
    private Label sigurNofn[]; //fylki fyrir sigurvegara
    private Label sigurStig[]; //fylki fyrir stig sigurvegara
    private Label tapNofn[]; //fylki fyrir mótspilara sigurvegara
    private Label tapStig[]; //fylki fyrir stig mótspilara
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    /***
     * frumstillir adalController
     * @param adal 
     */
    public void initAdal(AdalController adal){
        adalController = adal;
        stillaSigurOgTap();
    }
    /***
     * birtir stigatoflu dialog. Bætir við hætta og hreinsa tökkum 
     */
    public void birtaToflu(){
        DialogPane dialogInnihald = new DialogPane(); 
        sTafla.setVisible(true);
        dialogInnihald.setContent(sTafla);
        Dialog dialogUmgjord = new Dialog(); 
        dialogUmgjord.setDialogPane(dialogInnihald);
        dialogUmgjord.setHeaderText(adalController.tungumal.getString("Tafla"));
        ButtonType hreinsaTofluTakki = new ButtonType(adalController.tungumal
                .getString("HreinsaToflu"), 
                ButtonBar.ButtonData.APPLY);
        ButtonType haettaVidTakki = new ButtonType(adalController.tungumal
                .getString("Lok"), ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogUmgjord.getDialogPane().getButtonTypes().add(hreinsaTofluTakki);
        dialogUmgjord.getDialogPane().getButtonTypes().add(haettaVidTakki);
        Optional<ButtonType> tafla = dialogUmgjord.showAndWait();
        if (tafla.isPresent() && (tafla.get()
                .getButtonData() == ButtonBar.ButtonData.APPLY)) {
            hreinsaToflu();
        }
    }
    /*
    Bætir sigurvegara, stigum hans, mótspilara og stigum hvers leiks inn í 
    töfluna á viðeigandi stað.
    */
    public void setjaItoflu(String sig, String sstig, String tap, String tstig){
        for(int i = 1; i < sigurNofn.length; i++){
            if(stada == i){
                sigurNofn[i].setText(sig);
                sigurStig[i].setText(sstig);
                tapNofn[i].setText(tap);
                tapStig[i].setText(tstig);
            }
        }
        stada++;
    }
    /*
    Hreinsar allt út úr töflunni.
    */
    private void hreinsaToflu(){
        for(int i = 0; i < sigurNofn.length; i++){
            sigurNofn[i].setText("");
            sigurStig[i].setText("");
            tapNofn[i].setText("");
            tapStig[i].setText("");
        }
        stada = 1;
    }
    /*
    setur label-ana fyrir sigurvegara, stigin þeirra, tapara og stigin þeirra í
    fylki svo léttara sé að vinna með gögn.
    */
    private void stillaSigurOgTap() {
        sigurNofn = new Label[]{sigur1, sigur2, sigur3, sigur4, sigur5, sigur6,
        sigur7, sigur8};
        sigurStig = new Label[]{sigurStig1, sigurStig2, sigurStig3, sigurStig4, 
            sigurStig5, sigurStig6, sigurStig7, sigurStig8};
        tapNofn = new Label[]{aMoti1, aMoti2, aMoti3, aMoti4, aMoti5, aMoti6, 
            aMoti7, aMoti8};
        tapStig = new Label[]{aMotiStig1, aMotiStig2, aMotiStig3, aMotiStig4, 
            aMotiStig5, aMotiStig6, aMotiStig7, aMotiStig8};
    }

    
    
}
