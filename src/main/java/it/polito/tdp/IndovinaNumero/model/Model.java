package it.polito.tdp.IndovinaNumero.model;

import java.security.InvalidParameterException;
import java.util.HashSet;
import java.util.Set;

public class Model {
	
	private final int nMax= 100;
	private final int tMax=8;
	private int segreto;
	private int tentativiFatti;
	private boolean inGioco=false;
	
	//ESTENSIONE 5: non si può inserire due volte lo stesso numero
	private Set<Integer> tentativi;
	
	
	public int getSegreto() {
		return segreto;
	}


	public void setSegreto(int segreto) {
		this.segreto = segreto;
	}


	public int getTentativiFatti() {
		return tentativiFatti;
	}


	public void setTentativiFatti(int tentativiFatti) {
		this.tentativiFatti = tentativiFatti;
	}


	public boolean isInGioco() {
		return inGioco;
	}


	public void setInGioco(boolean inGioco) {
		this.inGioco = inGioco;
	}


	public int getnMax() {
		return nMax;
	}


	public int gettMax() {
		return tMax;
	}


	public void nuovaPartita() {

		//gestione inizio della partita
		
    	this.segreto=(int) ((Math.random()*nMax)+1); //numero casuale
    	this.tentativiFatti=0;
    	this.inGioco= true;
    	
    	this.tentativi= new HashSet<Integer>();
		
	}
	
	public int tentativo(int tentativo) {
		
		//se ritorno 0 era corretto
		//se ritorno 1 tentativo era più alto e -1 è più basso
		
		//controllo se la partita è in corso
		if(!inGioco) {
			throw new IllegalStateException("Hai perso! il segreto era: "+this.segreto);
		}
		
		//controllo input
		if(!tentativoValido(tentativo)) {
			throw new InvalidParameterException("Devi inserire un numero tra 1 e nMax, e non puoi inserire due volte lo stesso numero");
		}
		
		//il tentativo è valido
		this.tentativiFatti ++;
		
		this.tentativi.add(tentativo); //salvo la storia dei tentativi + controllo che tentativo sia unico
		
		if(this.tentativiFatti==(tMax-1)) {
			this.inGioco=false;
			
		}
		
		if(tentativo==this.segreto) {
			this.inGioco=false;
			return 0;}
		
		else if(tentativo< this.segreto)
			return -1;
		else 
			return 1;	
		
		
	}
	
	//per controllare input + estensione 5
	
	private boolean tentativoValido(int tentativo) {
		
		if(tentativo<1 || tentativo>nMax)
			return false;
		if(tentativi.contains(tentativo)) //se il tentativo è uguale ad altri, sul SET è immediato
			return false;
		
		return true;
	}

}
