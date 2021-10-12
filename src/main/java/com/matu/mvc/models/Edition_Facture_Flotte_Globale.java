package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Edition_Facture_Flotte_Globale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String attestation;
	private String site;
	private String client;
	private String adrclient1;
	private String natavenant;
	private String matricule;
	private Integer places;
	private Double r_val_neuv;
	private Double v_val_vena;
	private String date_effet;
	private String date_au;
	private Double v_val_gla;
	private Double rc;
	private Double dr;
	private Double tierce;
	private Double inc;
	private Double vol;
	private Double bdg;
	private Double dc;
	private Double autre;
	private Double cot_ht;
	private Double taxe;
	private Double cotttc;
    private Double acctim;
	

	public Double getAcctim() {
		return acctim;
	}

	public void setAcctim(Double acctim) {
		this.acctim = acctim;
	}

	public Double getV_val_gla() {
		return v_val_gla;
	}

	public void setV_val_gla(Double v_val_gla) {
		this.v_val_gla = v_val_gla;
	}

	public Double getRc() {
		return rc;
	}

	public void setRc(Double rc) {
		this.rc = rc;
	}

	public Double getDr() {
		return dr;
	}

	public void setDr(Double dr) {
		this.dr = dr;
	}

	public Double getTierce() {
		return tierce;
	}

	public void setTierce(Double tierce) {
		this.tierce = tierce;
	}

	public Double getInc() {
		return inc;
	}

	public void setInc(Double inc) {
		this.inc = inc;
	}

	public Double getVol() {
		return vol;
	}

	public void setVol(Double vol) {
		this.vol = vol;
	}

	public Double getBdg() {
		return bdg;
	}

	public void setBdg(Double bdg) {
		this.bdg = bdg;
	}

	public Double getDc() {
		return dc;
	}

	public void setDc(Double dc) {
		this.dc = dc;
	}

	public Double getAutre() {
		return autre;
	}

	public void setAutre(Double autre) {
		this.autre = autre;
	}

	public Double getCot_ht() {
		return cot_ht;
	}

	public void setCot_ht(Double cot_ht) {
		this.cot_ht = cot_ht;
	}

	public Double getTaxe() {
		return taxe;
	}

	public void setTaxe(Double taxe) {
		this.taxe = taxe;
	}

	public Double getCotttc() {
		return cotttc;
	}

	public void setCotttc(Double cotttc) {
		this.cotttc = cotttc;
	}

	public void setV_val_vena(Double v_val_vena) {
		this.v_val_vena = v_val_vena;
	}

	public Double getR_val_neuv() {
		return r_val_neuv;
	}

	public void setR_val_neuv(Double r_val_neuv) {
		this.r_val_neuv = r_val_neuv;
	}

	public Double getV_val_vena() {
		return v_val_vena;
	}

	public void setV__val_vena(Double v__val_vena) {
		this.v_val_vena = v__val_vena;
	}

	public String getDate_effet() {
		return date_effet;
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

	public String getNatavenant() {
		return natavenant;
	}

	public void setNatavenant(String natavenant) {
		this.natavenant = natavenant;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Integer getPlaces() {
		return places;
	}

	public void setPlaces(Integer places) {
		this.places = places;
	}

	public String getAdrclient1() {
		return adrclient1;
	}

	public void setAdrclient1(String adrclient1) {
		this.adrclient1 = adrclient1;
	}

	public String getAttestation() {
		return attestation;
	}

	public void setAttestation(String attestation) {
		this.attestation = attestation;
	}

	public Edition_Facture_Flotte_Globale(String attestation) {
		super();
		this.attestation = attestation;
	}

	public Edition_Facture_Flotte_Globale() {
		super();
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}
	
	
}
