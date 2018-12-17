/*
 * Dialog sem birtist þegar leiknum hefur verið lokið. 
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
public class LeikLokidController implements Initializable {

    @FXML
    private AnchorPane leikLokidDialog; //heldur utan um dialog-inn
    @FXML
    private Label sigurvegari; //hér má setja inn sigurvegara
    
    private AdalController adalController; //tenging við aðal Controller

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /**
     * Frumstillir aðalcontroller
     * @param adal 
     */
    public void initAdal(AdalController adal) {
        adalController = adal;
    }
    /**
     * Birtir dialog sem tilkynnir spilaranum um hvor þeirra vann leikinn.
     * Spilari getur svo valið að skoða stigatöflu, hætta í forriti eða byrjað 
     * nýjan leik
     */
    public void birtaLeikLokid(){
        DialogPane dialogInnihald = new DialogPane(); 
        leikLokidDialog.setVisible(true);
        dialogInnihald.setContent(leikLokidDialog);
        Dialog dialogUmgjord = new Dialog(); 
        dialogUmgjord.setDialogPane(dialogInnihald);
        setjaTakka(dialogUmgjord);
        Optional<ButtonType> lokid = dialogUmgjord.showAndWait();
        if(lokid.isPresent() && (lokid.get()
                .getButtonData() == ButtonBar.ButtonData.OK_DONE)){
            nyr();
        }
        if(lokid.isPresent() && (lokid.get()
                .getButtonData() == ButtonBar.ButtonData.OTHER)){
            opnaStig();
        }
        if(lokid.isPresent() && (lokid.get()
                .getButtonData() == ButtonBar.ButtonData.CANCEL_CLOSE)){
            System.exit(0);
        }
    }
    /**
     * Byrjar nýjan leik
     */
    private void nyr(){
        adalController.nyrLeikur();
    }
    /**
     * Opnar stigatöflu
     */
    private void opnaStig(){
        adalController.sjaToflu();
    }
    /**
     * Setur inn sigurvegara á tilkynninguna
     * @param s 
     */
    public void sigur(String s){
        sigurvegari.setText(s+" "+adalController.tungumal.getString("van"));
    }
    
    /**
     * Bætir við tökkunum á dialog-inn
     * @param dialogUmgjord 
     */
    private void setjaTakka(Dialog dialogUmgjord) {
       ButtonType nyrLeikur = new ButtonType(adalController.tungumal
                .getString("NyrLeikur"), ButtonBar.ButtonData.OK_DONE);
        ButtonType sjaStigatoflu = new ButtonType(adalController.tungumal
                .getString("SjaStigTofl"), ButtonBar.ButtonData.OTHER);
        ButtonType haettaVidTakki = new ButtonType(adalController.tungumal
                .getString("Haetta"), 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogUmgjord.getDialogPane().getButtonTypes().add(nyrLeikur);
        dialogUmgjord.getDialogPane().getButtonTypes().add(sjaStigatoflu);
        dialogUmgjord.getDialogPane().getButtonTypes().add(haettaVidTakki);
    }

    
}
