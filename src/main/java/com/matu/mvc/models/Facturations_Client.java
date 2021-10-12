package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facturations_Client {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idclient;
	private String client;
	private Integer idsite;
	private String adresse;
	public Facturations_Client() {
		super();
	}
	public Facturations_Client(Integer idclient, String client, Integer idsite, String adresse) {
		super();
		this.idclient = idclient;
		this.client = client;
		this.idsite = idsite;
		this.adresse = adresse;
	}
	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Integer getIdsite() {
		return idsite;
	}
	public void setIdsite(Integer idsite) {
		this.idsite = idsite;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	
}
