package com.matu.mvc.models;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Info_Client_Facture {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idclient;
	private String numfacture;
	private String Client;
	private String adresse;
	private String telephone;
	private String email;
	private String cin_pat;
	private String ice;
	//private LocalDateTime datecreation;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Info_Client_Facture() {
		super();
	}
	
	
	public String getIce() {
		return ice;
	}
	public void setIce(String ice) {
		this.ice = ice;
	}
	public String getCin_pat() {
		return cin_pat;
	}
	/*
	 * public LocalDateTime getDatecreation() { return datecreation; } public void
	 * setDatecreation(LocalDateTime datecreation) { this.datecreation =
	 * datecreation; }
	 */
	public String getcin_pat() {
		return cin_pat;
	}
	public void setCin_pat(String cin_pat) {
		this.cin_pat=cin_pat;
	}
	public Info_Client_Facture(Integer idclient, String numfacture, String client, String adresse, String telephone) {
		super();
		this.idclient = idclient;
		this.numfacture = numfacture;
		Client = client;
		this.adresse = adresse;
		this.telephone = telephone;
	}
	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public String getNumfacture() {
		return numfacture;
	}
	public void setNumfacture(String numfacture) {
		this.numfacture = numfacture;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	
}
