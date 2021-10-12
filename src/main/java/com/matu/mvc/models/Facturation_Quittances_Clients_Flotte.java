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
public class Facturation_Quittances_Clients_Flotte {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idquittance;
private Integer idclient;
private String client;
private String convention;
private String police;
private String nom_flotte;
private String flotte;
private String quittance;
private String total;
private String emission;
private String effet;
private String expiration;
public Facturation_Quittances_Clients_Flotte() {
	super();
}

public String getEmission() {
	return emission;
}

public void setEmission(String emission) {
	this.emission = emission;
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

public Integer getIdquittance() {
	return idquittance;
}
public void setIdquittance(Integer idquittance) {
	this.idquittance = idquittance;
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
public String getConvention() {
	return convention;
}
public void setConvention(String convention) {
	this.convention = convention;
}
public String getPolice() {
	return police;
}
public void setPolice(String police) {
	this.police = police;
}
public String getNom_flotte() {
	return nom_flotte;
}
public void setNom_flotte(String nom_flotte) {
	this.nom_flotte = nom_flotte;
}
public String getFlotte() {
	return flotte;
}
public void setFlotte(String flotte) {
	this.flotte = flotte;
}
public String getQuittance() {
	return quittance;
}
public void setQuittance(String quittance) {
	this.quittance = quittance;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
public Facturation_Quittances_Clients_Flotte(Integer idquittance, Integer idclient, String client, String convention,
		String police, String nom_flotte, String flotte, String quittance, String total) {
	super();
	this.idquittance = idquittance;
	this.idclient = idclient;
	this.client = client;
	this.convention = convention;
	this.police = police;
	this.nom_flotte = nom_flotte;
	this.flotte = flotte;
	this.quittance = quittance;
	this.total = total;
}





}
