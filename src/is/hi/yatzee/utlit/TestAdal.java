package is.hi.yatzee.utlit;


import static org.junit.Assert.*;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.concurrent.TimeoutException;

import org.junit.After;
import org.junit.Test;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.api.FxRobotException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.collections.ObservableList;

import static org.hamcrest.CoreMatchers.*;
import static org.testfx.api.FxAssert.verifyThat;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;


public class TestAdal extends ApplicationTest{
	
	String[] P1UIPOINTIDS = new String[] {"#asarStig1", "#tvistarStig1", "#thristarStig1", 
            "#fjarkarStig1", "#fimmurStig1", "#sexurStig1", "#thrirEinsStig1", 
            "#fjorirEinsStig1", "#husStig1", "#litilRodStig1", "#storRodStig1", "#sensinnStig1", 
            "#yahtzeeStig1"};
	String[] P2UIPOINTIDS = new String[] {"#asarStig2", "#tvistarStig2", "#thristarStig2", 
            "#fjarkarStig2", "#fimmurStig2", "#sexurStig2", "#thrirEinsStig2", 
            "#fjorirEinsStig2", "#husStig2", "#litilRodStig2", "#storRodStig2", "#sensinnStig2", 
            "#yahtzeeStig2"}; 
    String[] ALLOPTIONIDS = new String[] {
			"#asar", "#tvistar", "#thristar", "#fjarkar", "#fimmur", "#sexur", "#thrirEins", "#fjorirEins", 
			"#fulltHus", "#litilRod", "#storRod", "#takaSens", "#yahtzee"};
    String[] ALLDICEIDS = new String[] {"#ten1", "#ten2", "#ten3", "#ten4", "#ten5"};
    String[] ALLDICEIMGID = new String[] {"#tenMynd1", "#tenMynd2", "#tenMynd3", "#tenMynd4", "#tenMynd5"};
	
	
    @Override
	public void start(Stage stage) throws Exception {
    	ResourceBundle bundle = ResourceBundle.getBundle("is.hi.yatzee.utlit.texti", new Locale("en","GB"));
    	Parent root = FXMLLoader.load(getClass().getResource("Adal.fxml"), bundle);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setTitle("Yahtzee");
        stage.toFront();
    }
    
    @Test
    public void testCheckInitCorrectP1Points() {
    	for(int i = 0; i < P1UIPOINTIDS.length; i++) {
    		Label pointLabel = find(P1UIPOINTIDS[i]);
    		assertThat(pointLabel.getText(), is("0"));
    	}
    }
    
    @Test
    public void testCheckInitCorrectP2Points() {
    	for(int i = 0; i < P2UIPOINTIDS.length; i++) {
    		Label pointLabel = find(P2UIPOINTIDS[i]);
    		assertThat(pointLabel.getText(), is("0"));
    	}
    }
    
    @Test
    public void testInitTotal1Correct() {
    	Label total = find("#samtals1");
    	assertThat(total.getText(), is("0"));
    }
    
    @Test
    public void testInitTotal2Correct() {
    	Label total = find("#samtals2");
    	assertThat(total.getText(), is("0"));
    }
    
    @Test
    public void testFirstTurn() {
    	assertThat(find("#kastaTening2").isDisabled(), is(true));
    	assertThat(find("#kastaTening1").isDisabled(), is(false));
    	Label label = find("#kost1");
    	assertThat(label.getText(), is("3"));
    	clickOn("#kastaTening1");
    	label = find("#kost1");
    	assertThat(label.getText(), is("2"));
    	clickOn("#asar");
    	assertThat(find("#kastaTening1").isDisabled(), is(true));
    	assertThat(find("#kastaTening2").isDisabled(), is(false));
    	clickOn("#kastaTening2");
    }
    
    @Test
    public void testSaveAndFreeDice() {
    	clickOn("#kastaTening1");
    	clickOn(ALLDICEIDS[0]);
    	ToggleButton dice1 = find(ALLDICEIDS[0]);
    	assertThat(find(ALLDICEIDS[0]).isDisabled(), is(true));
    	clickOn("#losaTen");
    	assertThat(find(ALLDICEIDS[0]).isDisabled(), is(false));
    }
    
    @After
    public void after() throws TimeoutException {
    	FxToolkit.hideStage();
    	release(new KeyCode[]{});
    	release(new MouseButton[]{});
    }
    
    public <T extends Node> T find(final String query) {
    	return (T) lookup(query).queryAll().iterator().next();
    }
    
}
