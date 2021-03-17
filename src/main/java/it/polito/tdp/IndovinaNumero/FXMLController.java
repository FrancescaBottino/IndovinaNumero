
package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {

	private final int nMax= 100;
	private final int tMax=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco=false;
	
	 @FXML
	 private HBox layoutTentativo;
	
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnNuovaPartita;

    @FXML
    private TextField txtTentativi;

    @FXML
    private TextField txtTentativoUtente;

    @FXML
    private Button btnProva;

    @FXML
    private TextField txtRisultato;

    @FXML
    void DoNuovaPartita(ActionEvent event) {
     //gestione inizio partita
    	
    	this.segreto=(int) ((Math.random()*nMax)+1); //numero casuale
    	this.tentativiFatti=0;
    	this.inGioco= true;
    	
    	//gestione interfaccia 
    	this.txtTentativi.setText(Integer.toString(tMax));
    	
    	this.layoutTentativo.setDisable(false); //riabilitare riga 
    	
    	
    }

    @FXML
    void DoProva(ActionEvent event) {
    	
    	//lettura input utente-->IMPO
    	String ts= txtTentativoUtente.getText();
    	int tentativo;
    	
    	try {
    		tentativo= Integer.parseInt(ts);
    		
    		
    	}
    	catch(NumberFormatException e) {
    		txtRisultato.appendText("Devi inserire un numero!");
    		return;
    	}
    	
    	this.tentativiFatti++;
    	this.txtTentativi.setText(Integer.toString(tMax-this.tentativiFatti));
    	
    	if(tentativo==this.segreto) {
    		//indovinato
    		txtRisultato.setText("Hai indovinto con "+this.tentativiFatti+" tentativi");
    		this.inGioco=false;
    		this.layoutTentativo.setDisable(true);
    		return;
    	}
    	
    	if(this.tentativiFatti==tMax) {
    		//ho esaurito i tentativi
    		txtRisultato.setText("Hai perso, il numero era " +this.segreto);
    		this.inGioco=false;
    		this.layoutTentativo.setDisable(true);
    		return ;
    	}
    	
    	//Non ho vinto, informo l'utente del suo numero, alto/basso
    	
    	if(tentativo<this.segreto) {
    		txtRisultato.setText("Tentativo troppo basso");
    		
    		
    	}else
    		{ 
    		 txtRisultato.setText("Tentativo troppo alto");
    		
    		}
    	 	

    }

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
}

