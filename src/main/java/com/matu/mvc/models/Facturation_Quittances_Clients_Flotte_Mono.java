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
public class Facturation_Quittances_Clients_Flotte_Mono {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idhistorique;
private Integer idclient;
private String matricule;
private String client;
private String convention;
private String date_emis;
private String date_effet;
private String date_expiration;
private String police;
private String total;
private String flotte;
private String avenant;
private Integer idquittance;

public Integer getIdclient() {
	return idclient;
}
public void setIdclient(Integer idclient) {
	this.idclient = idclient;
}
public String getMatricule() {
	return matricule;
}

public Integer getIdquittance() {
	return idquittance;
}
public void setIdquittance(Integer idquittance) {
	this.idquittance = idquittance;
}
public String getAvenant() {
	return avenant;
}
public void setAvenant(String avenant) {
	this.avenant = avenant;
}
public Integer getIdhistorique() {
	return idhistorique;
}
public void setIdhistorique(Integer idhistorique) {
	this.idhistorique = idhistorique;
}
public void setMatricule(String matricule) {
	this.matricule = matricule;
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
public String getDate_emis() {
	return date_emis;
}
public void setDate_emis(String date_emis) {
	this.date_emis = date_emis;
}
public String getDate_effet() {
	return date_effet;
}
public void setDate_effet(String date_effet) {
	this.date_effet = date_effet;
}
public String getDate_expiration() {
	return date_expiration;
}
public void setDate_expiration(String date_expiration) {
	this.date_expiration = date_expiration;
}
public String getPolice() {
	return police;
}
public void setPolice(String police) {
	this.police = police;
}
public String getTotal() {
	return total;
}
public void setTotal(String total) {
	this.total = total;
}
public String getFlotte() {
	return flotte;
}
public void setFlotte(String flotte) {
	this.flotte = flotte;
}
public Facturation_Quittances_Clients_Flotte_Mono() {
	super();
}
public Facturation_Quittances_Clients_Flotte_Mono(Integer idclient, String matricule, String client, String convention,
		String date_emis, String date_effet, String date_expiration, String police, String total, String flotte) {
	super();
	this.idclient = idclient;
	this.matricule = matricule;
	this.client = client;
	this.convention = convention;
	this.date_emis = date_emis;
	this.date_effet = date_effet;
	this.date_expiration = date_expiration;
	this.police = police;
	this.total = total;
	this.flotte = flotte;
}




}
