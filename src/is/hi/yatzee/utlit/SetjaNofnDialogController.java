/**
 * Leyfir notendum að setja inn sín eigin nöfn inn í leikinn.
 */
package is.hi.yatzee.utlit;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Sunneva
 */
public class SetjaNofnDialogController implements Initializable {

    @FXML
    private AnchorPane nafnaDialog; //heldur utan um dialog-inn
    @FXML
    private TextField leikm1; //leyfir leikmanni 1 að setja nafnið sitt inn
    @FXML
    private TextField leikm2; //leyfir leikmanni 2 að setja nafnið sitt inn
    
    @FXML
    private AdalController adalController; //tengir við AdalController
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * frumstillir adalController
     * @param adal 
     */
    public void initAdal(AdalController adal){
        adalController = adal;
    }
    
    /**
     * Birtir dialog sem leyfir notendum að setja inn þau nöfn sem þau vilja
     */
    public void nofnLeikmanna(){
        DialogPane dialogInnihald = new DialogPane(); 
        nafnaDialog.setVisible(true);
        dialogInnihald.setContent(nafnaDialog);
        Dialog dialogUmgjord = new Dialog(); 
        dialogUmgjord.setDialogPane(dialogInnihald);
        dialogUmgjord.setHeaderText(adalController.tungumal
                .getString("NofnDialogLeid"));
        ButtonType iLagiTakki = new ButtonType(adalController.tungumal
                .getString("ILagi"), ButtonBar.ButtonData.OK_DONE);
        dialogUmgjord.getDialogPane().getButtonTypes().add(iLagiTakki);
        final Node iLagiVirkni =dialogInnihald.lookupButton(iLagiTakki);
        iLagiVirkni.disableProperty().bind(leikm1.textProperty().isEmpty()
                        .or(leikm2.textProperty().isEmpty()));
        ButtonType haettaVidTakki = new ButtonType(adalController.tungumal
                .getString("Lok"), 
                ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogUmgjord.getDialogPane().getButtonTypes().add(haettaVidTakki);
        Optional<ButtonType> nofn = dialogUmgjord.showAndWait();
        if (nofn.isPresent() && (nofn.get()
                .getButtonData() == ButtonBar.ButtonData.OK_DONE)) {
            adalController.notandi1.setText(leikm1.getText());
            adalController.notandi2.setText(leikm2.getText());
        }
    }
    
}
