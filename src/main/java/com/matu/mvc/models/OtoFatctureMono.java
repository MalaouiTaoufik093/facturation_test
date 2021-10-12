package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class OtoFatctureMono {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idfacture;
	private String datefacture;
	private String numfacture;
	private Integer idsite;
	private Integer idclient;
	private String exercice;
	private String typefacture;
	//private String branche;
	private String type_facture;
	
	private String datecreation;
	private String date_creation;

	private String client;
	private String site;
	
	public OtoFatctureMono() {
		super();
	}
	
	
	

	//public String getBranche() {
	//	return branche;
	//}




	/*
	 * public void setBranche(String branche) { this.branche = branche; }
	 */




	public OtoFatctureMono(Integer idfacture, String datefacture, String numfacture, Integer idsite, Integer idclient,
			String exercice, String typefacture, String type_facture, String datecreation, String date_creation,
			String client, String site) {
		super();
		this.idfacture = idfacture;
		this.datefacture = datefacture;
		this.numfacture = numfacture;
		this.idsite = idsite;
		this.idclient = idclient;
		this.exercice = exercice;
		this.typefacture = typefacture;
		this.type_facture = type_facture;
		this.datecreation = datecreation;
		this.date_creation = date_creation;
		this.client = client;
		this.site = site;
	}




	public Integer getIdfacture() {
		return idfacture;
	}

	public void setIdfacture(Integer idfacture) {
		this.idfacture = idfacture;
	}

	public String getDatefacture() {
		return datefacture;
	}

	public void setDatefacture(String datefacture) {
		this.datefacture = datefacture;
	}

	public String getNumfacture() {
		return numfacture;
	}

	public void setNumfacture(String numfacture) {
		this.numfacture = numfacture;
	}

	public Integer getIdsite() {
		return idsite;
	}

	public void setIdsite(Integer idsite) {
		this.idsite = idsite;
	}

	public Integer getIdclient() {
		return idclient;
	}

	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}

	public String getExercice() {
		return exercice;
	}

	public void setExercice(String exercice) {
		this.exercice = exercice;
	}

	public String getTypefacture() {
		return typefacture;
	}

	public void setTypefacture(String typefacture) {
		this.typefacture = typefacture;
	}

	public String getType_facture() {
		return type_facture;
	}

	public void setType_facture(String type_facture) {
		this.type_facture = type_facture;
	}

	public String getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(String datecreation) {
		this.datecreation = datecreation;
	}

	public String getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(String date_creation) {
		this.date_creation = date_creation;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

}
