
package it.polito.tdp.IndovinaNumero;

import java.net.URL;
import java.security.InvalidParameterException;
import java.util.ResourceBundle;

import it.polito.tdp.IndovinaNumero.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class FXMLController {
	
	private Model model; //aggiunto

/*	private final int nMax= 100;
	private final int tMax=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco=false; */
	
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
    /* //gestione inizio partita
    	
    	this.segreto=(int) ((Math.random()*nMax)+1); //numero casuale
    	this.tentativiFatti=0;
    	this.inGioco= true;
    	*/
    	
    	this.model.nuovaPartita(); //inizio la partita, delego il model
    	
    	//gestione interfaccia  RIMANE QUI
    	
    	this.txtRisultato.setText("");
    	this.txtTentativi.setText(Integer.toString(this.model.gettMax())); //aggiungo get
    	
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
    	
    	this.txtTentativoUtente.setText("");
    	 	
    	// this.tentativiFatti++;
    	
    	/*if(tentativo==this.segreto) {
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
    		
    		}*/
    	
        int result;
        
        try {
        result=this.model.tentativo(tentativo);
        }
        catch(IllegalStateException se) {
        	
        	this.txtRisultato.setText(se.getMessage());
        	this.layoutTentativo.setDisable(true);
        	return;
        	
        	
        }catch(InvalidParameterException pe) {
        	
        	txtRisultato.setText(pe.getMessage());
        	return;
        }
    	
        
        this.txtTentativi.setText(Integer.toString(this.model.gettMax()-this.model.getTentativiFatti()));
        
    	if(result==0) {
    		
    		txtRisultato.setText("Hai vinto con " + this.model.getTentativiFatti() + " tentativi");
    		this.layoutTentativo.setDisable(true);
    		
    		
    	}
    	else if(result<0) {
    		txtRisultato.setText("Tentativo troppo basso");
    		
    	}
    	else
    		{
    		  txtRisultato.setText("Tentativo troppo alto");
    		  
    		}
    		
    	
    	//manca la parte in cui esaurisco i tentaivi -->try e catch
    	
    	 	

    }
    
    

    @FXML
    void initialize() {
        assert btnNuovaPartita != null : "fx:id=\"btnNuovaPartita\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativi != null : "fx:id=\"txtTentativi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTentativoUtente != null : "fx:id=\"txtTentativoUtente\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnProva != null : "fx:id=\"btnProva\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

  
    }
   
    //aggiungo , setto modello , metodo esterno
    
    public void setModel(Model model) {
    	this.model=model;
    }
}

