package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facturation_Details_facture_Rd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private String numfacture;
	//private String matricule;
	private String police;
	private String Client;
	private double pht;
	private double taxe;
	private double pttc;
    private String effet;
    private String expiration;
    private Double avance;
	private Double rap;
	public Facturation_Details_facture_Rd() {
		super();
	}

	

	public Facturation_Details_facture_Rd(String police,  String client,double pht,double taxe,double pttc) {
		super();
		this.police = police;
		//this.matricule = matricule;
		this.pht = pht;
		Client = client;
		this.taxe = taxe;
		this.pttc = pttc;
	}



	public Double getAvance() {
		return avance;
	}



	public void setAvance(Double avance) {
		this.avance = avance;
	}



	public double getRap() {
		return rap;
	}



	public void setRap(double rap) {
		this.rap = rap;
	}



	public String getEffet() {
		return effet;
	}



	public void setEffet(String effet) {
		this.effet = effet;
	}



	public String getExpiration() {
		return expiration;
	}



	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}



	public double getPttc() {
		return pttc;
	}



	public void setPttc(double pttc) {
		this.pttc = pttc;
	}



	public double getTaxe() {
		return taxe;
	}



	public void setTaxe(double taxe) {
		this.taxe = taxe;
	}



	public double getPht() {
		return pht;
	}



	public void setPht(double pht) {
		this.pht = pht;
	}



	public String getClient() {
		return Client;
	}



	public void setClient(String client) {
		Client = client;
	}



	public String getPolice() {
		return police;
	}


	public void setPolice(String police) {
		this.police = police;
	}

	/*
	 * public String getMatricule() { return matricule; }
	 * 
	 * public void setMatricule(String matricule) { this.matricule = matricule; }
	 */

	
	
	
}
