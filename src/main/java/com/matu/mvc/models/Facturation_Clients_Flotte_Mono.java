package com.matu.mvc.models;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Facturation_Clients_Flotte_Mono {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
//*** attributs ***
private Integer idclient;
private String client;
private Integer idsite;
private String  adresse;

///*** constructs ***
 //**whithout params ***
public Integer getIdclient() {
	return idclient;
}

 //**whith params ***
public Facturation_Clients_Flotte_Mono(Integer idclient,  String client, Integer idsite,
		String adresse) {
	super();
	this.idclient = idclient;
	this.client = client;
	this.idsite = idsite;
	this.adresse = adresse;
}

///*** getters and setters***

public Facturation_Clients_Flotte_Mono() {
	super();
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
