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
public class Facturation_EditerFactureDetails_Mono {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private String matricule;
private String police;
private double pht;
private double taxe;
private double pttc;
private String effet;
private String expiration;
private String exercice;
private double avance;
private double rap;
private String flotte;
private Integer idquittance;
private double tot_pht;
private double tot_taxe;
private double tot_pttc;
private String numfacture;
private String datefacture;
public String getMatricule() {
	return matricule;
}
public void setMatricule(String matricule) {
	this.matricule = matricule;
}
public String getPolice() {
	return police;
}
public void setPolice(String police) {
	this.police = police;
}
public double getPht() {
	return pht;
}
public void setPht(double pht) {
	this.pht = pht;
}
public double getTaxe() {
	return taxe;
}
public void setTaxe(double taxe) {
	this.taxe = taxe;
}
public double getPttc() {
	return pttc;
}
public void setPttc(double pttc) {
	this.pttc = pttc;
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
public Integer getIdquittance() {
	return idquittance;
}
public void setIdquittance(Integer idquittance) {
	this.idquittance = idquittance;
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
public String getNumfacture() {
	return numfacture;
}
public void setNumfacture(String numfacture) {
	this.numfacture = numfacture;
}
public String getDatefacture() {
	return datefacture;
}
public void setDatefacture(String datefacture) {
	this.datefacture = datefacture;
}
public Facturation_EditerFactureDetails_Mono() {
	super();
}
public Facturation_EditerFactureDetails_Mono(String matricule, String police, double pht, double taxe, double pttc,
		String effet, String expiration, String exercice, double avance, double rap, String flotte, Integer idquittance,
		double tot_pht, double tot_taxe, double tot_pttc, String numfacture, String datefacture) {
	super();
	this.matricule = matricule;
	this.police = police;
	this.pht = pht;
	this.taxe = taxe;
	this.pttc = pttc;
	this.effet = effet;
	this.expiration = expiration;
	this.exercice = exercice;
	this.avance = avance;
	this.rap = rap;
	this.flotte = flotte;
	this.idquittance = idquittance;
	this.tot_pht = tot_pht;
	this.tot_taxe = tot_taxe;
	this.tot_pttc = tot_pttc;
	this.numfacture = numfacture;
	this.datefacture = datefacture;
}





}
