/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package is.hi.yatzee.utlit;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Sunneva
 */
public class NestedMain extends Application {
    public static Parent enskRoot;
    public static Parent iskRoot;
    
    @Override
    public void start(Stage stage) throws Exception {
        //Hleður inn íslenskri þýðingu
        iskRoot = FXMLLoader.load(getClass().getResource("Adal.fxml"), 
                ResourceBundle.getBundle("is.hi.yatzee.utlit.texti", new Locale("is")));
        //Hleður inn enskru þýðingu
        enskRoot = FXMLLoader.load(getClass().getResource("Adal.fxml"), 
                ResourceBundle.getBundle("is.hi.yatzee.utlit.texti", new Locale("en","GB")));
        //Íslanska er gefna viðótið
        Parent root = iskRoot;
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Yahtzee");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
