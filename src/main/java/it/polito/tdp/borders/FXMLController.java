
package it.polito.tdp.borders;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.borders.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtAnno"
    private TextField txtAnno; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCalcolaConfini(ActionEvent event) {
    	txtResult.clear();
    	
    	try {
    		String s = txtAnno.getText();
    		Integer anno = Integer.parseInt(s);
    		
    		if (anno < 1816 || anno > 2016 ) {
    			txtResult.appendText("Inserire un anno nell'intervallo 1816-2016!");
    		} else {
    			this.model.creaGrafo(anno);
    			txtResult.appendText("GRAFO CREATO!\n");
    			txtResult.appendText("#VERTICI: "+this.model.nVertici()+"\n");
    			txtResult.appendText("#ARCHI: "+this.model.nArchi()+"\n");
    			txtResult.appendText("Componenti connesse: "+this.model.connesse()+"\n");
    			txtResult.appendText("Elenco stati: " +this.model.elencoStati()+"\n");
    		}
    		
    	} catch (NumberFormatException e) {
    		txtResult.appendText("Inserire un numero nel formato corretto!");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtAnno != null : "fx:id=\"txtAnno\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
