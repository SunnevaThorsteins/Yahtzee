/**
 * Þessi klasi heldur utan um valmyndina í yatzee spilinu. Þar er hægt að velja 
 * tungumál leiksins, að hætta í forriti, að breyta nöfnum spilaranna, nýjan 
 * leik, fá upplýsingar um forritið, lesa leikreglur og opna tónlistarspilara og 
 * stigatöflu.
 */
package is.hi.yatzee.utlit;

import is.hi.yatzee.vinnsla.YatzeeImp;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 * FXML Controller class
 *
 * @author Sunneva
 */

public class ValmyndController implements Initializable {
    
    private AdalController adalController;//tenging við aðal controller
    @FXML
    private MenuBar mal; //heldur utan um menuBar
    /**
     * Initializes the controller class.
     */
    
    /*
    frumstillir aðalcontroller
    */
    public void initAdal(AdalController adal){
        adalController = adal;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    /*
    Byrjar nýjan leik. Núllstillir stig, frumstillir hnappa og kallar á 
    nyrLeikur
    */
    @FXML
    private void nyrLeikurHandler(ActionEvent event) {
        adalController.nullstillaStigin();
        adalController.initHnappar();
        adalController.nyrLeikur();
    }
    
    /*
    gefur notanda upplýsingar um forritið með alert
    */

    @FXML
    private void umForritHandler(ActionEvent event) {
        Alert umForritid = new Alert (Alert.AlertType.INFORMATION);
        umForritid.setTitle(adalController.tungumal.getString("UmForrit"));
        umForritid.setHeaderText(adalController.tungumal.getString("Utgafa"));
        umForritid.setContentText(adalController.tungumal.getString("Utgafa")
                + " v0.2");
        umForritid.showAndWait();
    }
    
    /*
    gefur notanda upplýsingar um leikreglur
    */
    @FXML
    private void leikreglurHandler(ActionEvent event) {
        Alert leikreglur = new Alert (Alert.AlertType.INFORMATION);
        leikreglur.setTitle(adalController.tungumal.getString("yahregl"));
        leikreglur.setHeaderText(adalController.tungumal.getString("yahregl"));
        leikreglur.setContentText(adalController.tungumal.getString("leikregl"));
        leikreglur.showAndWait();
    }
    /*
    Leyfir notanda að hætta í forritinu
    */
    @FXML
    private void haettaHandler(ActionEvent event) {
        System.exit(0);
    }
    /*
    Leyfir notanda að breyta nöfnum sínum í valmyndinni
    */
    @FXML
    private void breytaNofnumHandler(ActionEvent event) {
        adalController.breytaNofnum();
    }
    /*
    Leyfir notanda að sjá stigatöflu í valmyndinni
    */
    @FXML
    private void stigaTaflaHandler(ActionEvent event) {
        adalController.sjaToflu();
    }
    /*
    Breytir forritinu yfir á ensku í gegnum valmynd
    */
    @FXML
    private void enskHandler(ActionEvent event) {
        mal.getScene().setRoot(NestedMain.enskRoot);
    }
    /*
    Breytir forritinu yfir á íslensku í gegnum valmynd
    */
    @FXML
    private void iskHandler(ActionEvent event) {
        mal.getScene().setRoot(NestedMain.iskRoot);
    }
    
    /*
    Leyfir notanda að opna tónlistarspilara í gegnum valmynd
    */
    @FXML
    private void opnaTonlist(ActionEvent event) {
        adalController.spilari();
    }

    @FXML
    private void upplHandler(ActionEvent event) {
        adalController.upplysingar();
    }
    
}
