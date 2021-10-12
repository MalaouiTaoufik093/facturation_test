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
public class Facturation_EditerFactureDetails_Flotte {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idquittance;
private String police;

private String quittance;
private double total;
private String emis;
private String effet;
private String expiration;
private String exercice;
private double avance;
private double rap;
private String flotte;
private double tot_pttc;
public String getPolice() {
	return police;
}
public void setPolice(String police) {
	this.police = police;
}
public Integer getIdquittance() {
	return idquittance;
}
public void setIdquittance(Integer idquittance) {
	this.idquittance = idquittance;
}
public String getQuittance() {
	return quittance;
}
public void setQuittance(String quittance) {
	this.quittance = quittance;
}
public double getTotal() {
	return total;
}
public void setTotal(double total) {
	this.total = total;
}
public String getEmis() {
	return emis;
}
public void setEmis(String emis) {
	this.emis = emis;
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
public String getExercice() {
	return exercice;
}
public void setExercice(String exercice) {
	this.exercice = exercice;
}
public double getAvance() {
	return avance;
}
public void setAvance(double avance) {
	this.avance = avance;
}
public double getRap() {
	return rap;
}
public void setRap(double rap) {
	this.rap = rap;
}
public String getFlotte() {
	return flotte;
}
public void setFlotte(String flotte) {
	this.flotte = flotte;
}
public double getTot_pttc() {
	return tot_pttc;
}
public void setTot_pttc(double tot_pttc) {
	this.tot_pttc = tot_pttc;
}
public Facturation_EditerFactureDetails_Flotte() {
	super();
}
public Facturation_EditerFactureDetails_Flotte(String police, Integer idquittance, String quittance, double total,
		String emis, String effet, String expiration, String exercice, double avance, double rap, String flotte,
		double tot_pttc) {
	super();
	this.police = police;
	this.idquittance = idquittance;
	this.quittance = quittance;
	this.total = total;
	this.emis = emis;
	this.effet = effet;
	this.expiration = expiration;
	this.exercice = exercice;
	this.avance = avance;
	this.rap = rap;
	this.flotte = flotte;
	this.tot_pttc = tot_pttc;
}



}
