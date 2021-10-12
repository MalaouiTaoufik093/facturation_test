package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Details_Flotte_Avec_Retrait {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String attestation;
	private String police;
	private String quittance;
	private Integer idquittance;
	private String avenant;
	private String matricule;
	private double Cot_Ht;
	private double cotttc;
	private String date_effet;
	private String date_au;
	private Integer places;
	
	private double taxe;
	private double acctim;
	// ** GARANTIES ***
	private double rc;
	private double dr;
	private double tierce;
	private double inc;
	private double vol;
	private double bdg;
	private double dc;
	private double autre;
	private double catnat;

	//** getters and setters *** 
	
	
	
	public String getDate_effet() {
		return date_effet;
	}
	public double getTaxe() {
		return taxe;
	}
	public void setTaxe(double taxe) {
		this.taxe = taxe;
	}
	public double getAcctim() {
		return acctim;
	}
	public void setAcctim(double acctim) {
		this.acctim = acctim;
	}
	public Integer getPlaces() {
		return places;
	}
	public void setPlaces(Integer places) {
		this.places = places;
	}
	public double getInc() {
		return inc;
	}
	public void setInc(double inc) {
		this.inc = inc;
	}
	public double getRc() {
		return rc;
	}
	public void setRc(double rc) {
		this.rc = rc;
	}
	public double getDr() {
		return dr;
	}
	public void setDr(double dr) {
		this.dr = dr;
	}
	public double getTierce() {
		return tierce;
	}
	public void setTierce(double tierce) {
		this.tierce = tierce;
	}
	public double getIncendieinc() {
		return inc;
	}
	public void setIncendieinc(double incendieinc) {
		this.inc = incendieinc;
	}
	public double getVol() {
		return vol;
	}
	public void setVol(double vol) {
		this.vol = vol;
	}
	public double getBdg() {
		return bdg;
	}
	public void setBdg(double bdg) {
		this.bdg = bdg;
	}
	public double getDc() {
		return dc;
	}
	public void setDc(double dc) {
		this.dc = dc;
	}
	public double getAutre() {
		return autre;
	}
	public void setAutre(double autre) {
		this.autre = autre;
	}
	public double getCatnat() {
		return catnat;
	}
	public void setCatnat(double catnat) {
		this.catnat = catnat;
	}
	public void setDate_effet(String date_effet) {
		this.date_effet = date_effet;
	}
	public String getDate_au() {
		return date_au;
	}
	public void setDate_au(String date_au) {
		this.date_au = date_au;
	}
	public double getCot_Ht() {
		return Cot_Ht;
	}
	public void setCot_Ht(double cot_Ht) {
		Cot_Ht = cot_Ht;
	}
	public double getCot_htc() {
		return Cot_Ht;
	}
	public void setCot_htc(double cot_htc) {
		this.Cot_Ht = cot_htc;
	}
	public double getCotttc() {
		return cotttc;
	}
	public void setCotttc(double cotttc) {
		this.cotttc = cotttc;
	}
	public String getAvenant() {
		return avenant;
	}
	public void setAvenant(String avenant) {
		this.avenant = avenant;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getAttestation() {
		return attestation;
	}
	public void setAttestation(String attestation) {
		this.attestation = attestation;
	}
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public String getQuittance() {
		return quittance;
	}
	public void setQuittance(String quittance) {
		this.quittance = quittance;
	}
	public Integer getIdquittance() {
		return idquittance;
	}
	public void setIdquittance(Integer idquittance) {
		this.idquittance = idquittance;
	}
	public String getNatAvenant() {
		return avenant;
	}
	public void setNatAvenant(String natAvenant) {
		avenant = natAvenant;
	}
	public Details_Flotte_Avec_Retrait() {
		super();
	}
	public Details_Flotte_Avec_Retrait(String attestation, String police, String quittance, Integer idquittance,
			String natAvenant) {
		super();
		this.attestation = attestation;
		this.police = police;
		this.quittance = quittance;
		this.idquittance = idquittance;
		avenant = natAvenant;
	}
	
	
	
}
