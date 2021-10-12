package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Details_facture_globale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String exercice;
	private Integer nreligne;
	private double tot_pht;
	private double tot_taxe;
	private double tot_pttc;
	private double avance;
	private double reste_a_payer;
	private String usage;

	
	
	
	public String getUsage() {
		return usage;
	}














	public void setUsage(String usage) {
		this.usage = usage;
	}














	public Details_facture_globale( String exercice, Integer nbreligne, double tot_pht,
			double tot_taxe, double tot_pttc, double avance, double reste_a_payer) {
		super();
		this.exercice = exercice;
		this.nreligne = nbreligne;
		this.tot_pht = tot_pht;
		this.tot_taxe = tot_taxe;
		this.tot_pttc = tot_pttc;
		this.avance = avance;
		this.reste_a_payer = reste_a_payer;
	}









	




	public Integer getNreligne() {
		return nreligne;
	}














	public void setNreligne(Integer nreligne) {
		this.nreligne = nreligne;
	}














	public Details_facture_globale() {
		super();
	}


	

	public String getExercice() {
		return exercice;
	}


	public void setExercice(String exercice) {
		this.exercice = exercice;
	}


	
	public double getTot_pht() {
		return tot_pht;
	}


	public void setTot_pht(double tot_pht) {
		this.tot_pht = tot_pht;
	}


	public double getTot_taxe() {
		return tot_taxe;
	}


	public void setTot_taxe(double tot_taxe) {
		this.tot_taxe = tot_taxe;
	}


	public double getTot_pttc() {
		return tot_pttc;
	}


	public void setTot_pttc(double tot_pttc) {
		this.tot_pttc = tot_pttc;
	}


	public double getAvance() {
		return avance;
	}


	public void setAvance(double avance) {
		this.avance = avance;
	}


	public double getReste_a_payer() {
		return reste_a_payer;
	}


	public void setReste_a_payer(double reste_a_payer) {
		this.reste_a_payer = reste_a_payer;
	}

	

	
	
}
