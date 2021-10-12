package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Get_Facturation_Globale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idquittance;
	private String client;
	private String police;
	private String date_effet;
	private String date_au;
	private String avenant;
	private String attestation;
	private String matricule;
	private String quittance;
	private double pht;
	private double taxe;
	private double pttc;
	
	public Get_Facturation_Globale() {
		super();
	}

	public Get_Facturation_Globale(Integer idquittance, String client, String police, String date_effet, String date_au,
			String avenant, String attestation, String matricule, String quittance, double pht, double taxe,
			double pttc) {
		super();
		this.idquittance = idquittance;
		this.client = client;
		this.police = police;
		this.date_effet = date_effet;
		this.date_au = date_au;
		this.avenant = avenant;
		this.attestation = attestation;
		this.matricule = matricule;
		this.quittance = quittance;
		this.pht = pht;
		this.taxe = taxe;
		this.pttc = pttc;
	}

	public Integer getIdquittance() {
		return idquittance;
	}

	public void setIdquittance(Integer idquittance) {
		this.idquittance = idquittance;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getPolice() {
		return police;
	}

	public void setPolice(String police) {
		this.police = police;
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

	public String getAvenant() {
		return avenant;
	}

	public void setAvenant(String avenant) {
		this.avenant = avenant;
	}

	public String getAttestation() {
		return attestation;
	}

	public void setAttestation(String attestation) {
		this.attestation = attestation;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getQuittance() {
		return quittance;
	}

	public void setQuittance(String quittance) {
		this.quittance = quittance;
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

	
	
}
