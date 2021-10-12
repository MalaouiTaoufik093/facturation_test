package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Details_Flotte_Globale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String numfacture;
	private String police;
	private String exercice;
	private String Client;
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public String getExercice() {
		return exercice;
	}
	public void setExercice(String exercice) {
		this.exercice = exercice;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
	}
	public Details_Flotte_Globale(String police, String exercice, String client) {
		super();
		this.police = police;
		this.exercice = exercice;
		Client = client;
	}
	public Details_Flotte_Globale() {
		super();
	}
	
	
	
	
	
}
