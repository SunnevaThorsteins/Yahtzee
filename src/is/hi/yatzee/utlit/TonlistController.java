/**
 * Tónlistarspilari sem leyfir notanda að hlusta á lög á meðan á leiknum stendur.
 * Hægt er að velja milli 6 laga. Hægt er að spila þau og stoppa þau að vild. 
 * Lögin eru fengin af youtube.
 * Nature: https://www.youtube.com/watch?v=rSS85mzmv1c
 * Scifi: https://www.youtube.com/watch?v=N67zX980ARc
 * Upbeat: https://www.youtube.com/watch?v=mGx_FATyasQ&t=41s
 * Jazz: https://www.youtube.com/watch?v=v8vscVsxG8U
 * Adventure: https://www.youtube.com/watch?v=gRbuvUG7oQs&t=23s
 * Fantacy: https://www.youtube.com/watch?v=gRuggMzH3Gw
 */
package is.hi.yatzee.utlit;

import java.io.File;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author Sunneva
 */
public class TonlistController implements Initializable {
    @FXML
    private AnchorPane spilarinn; //Heldur utan um dialog-ið
    private AdalController adalController; //tenging við aðal controllerinn
    private MediaPlayer player; //spilarinn sem spilar lögin
    private String[] log = new String[] {"UpBeat", "Fantacy", 
        "Adventure", "Nature", "Jazz", "Scifi"}; //Nöfn laganna sem má spila
    int numerLags = 0; //heldur utan um númer lagsin
    @FXML
    private ListView<String> lagaVal; //heldur utan um listan af lögunum
    private MultipleSelectionModel msl; //heldur utan um selection model-ið
    private boolean iGangi; //heldur utan um hvort búið sé að spila lag
    @FXML
    private Button spila; //takki sem spilar lag
    @FXML
    private Button stop; //takki sem stoppar lag

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Birtir tónlistarspilara dialog
     */
    public void synaSpilara(){
        DialogPane dialogInnihald = new DialogPane(); 
        spilarinn.setVisible(true);
        dialogInnihald.setContent(spilarinn);
        Dialog dialogUmgjord = new Dialog(); 
        dialogUmgjord.setDialogPane(dialogInnihald);
        ButtonType haettaVidTakki = new ButtonType(adalController.tungumal
                .getString("Haetta"), ButtonBar.ButtonData.CANCEL_CLOSE);
        dialogUmgjord.getDialogPane().getButtonTypes().add(haettaVidTakki);
        Optional<ButtonType> nofn = dialogUmgjord.showAndWait();
    }
    
    /**
     * Spilar valið lag. Ef lag er í gangi stoppar spilarinn það áður en
     * næsta er spilað. 
     * @param event 
     */
    @FXML
    private void spilaHandler(ActionEvent event) {
        if(iGangi)
            player.stop();
        System.out.println(numerLags);
        if(numerLags != -1){
            Media sound = new Media(new File(log[numerLags]+".mp3").toURI()
                    .toString());
            player = new MediaPlayer(sound);
            player.play();
            iGangi = true;
        }
    }
    
    /*
    Stoppar lag, hafi lag verið spilað.
    */
    @FXML
    private void stoppaHandler(ActionEvent event) {
        if(iGangi)
            player.stop();
    }
    
    /**
     * frumstillir adalController og setur upp spilarann með því að kalla á 
     * frumstillaLog og frumstillaHandler
     * @param adal 
     */
    void init(AdalController adal) {
        adalController = adal;
        lagaVal.setItems(frumstillaLog());
        frumstillaHandler();
    }
    /**
     * setur lögin inn í tónlistar spilarann
     * @return obl
     */
    private ObservableList<String> frumstillaLog() {
        ObservableList<String> obl = FXCollections.observableArrayList();
        for(int i = 0; i < log.length; i++){
            obl.add(log[i]);
        }
        return obl;
    }
    
    /**
     * frumstillir selection model-ið fyrir tónlistar spilarann
     */
    private void frumstillaHandler(){
        msl = lagaVal.getSelectionModel();
        msl.selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, 
                    String oldValue, String newValue) {
                numerLags = msl.getSelectedIndex();
            }
        });
    }
    
}
