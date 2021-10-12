package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ps_Matufac_EditerFactureDetailMono {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String matricule;
	private String police;
	private String Client;
	private double pht;
	private double taxe;
	private double pttc;
    private String effet;
    private String expiration;
    private Double avance;
	private Double rap;
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
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
	public Double getAvance() {
		return avance;
	}
	public void setAvance(Double avance) {
		this.avance = avance;
	}
	public Double getRap() {
		return rap;
	}
	public void setRap(Double rap) {
		this.rap = rap;
	}
	public ps_Matufac_EditerFactureDetailMono(String police, String matricule, String client, double pht, double taxe,
			double pttc, String effet, String expiration, Double avance, Double rap) {
		super();
		this.police = police;
		this.matricule = matricule;
		Client = client;
		this.pht = pht;
		this.taxe = taxe;
		this.pttc = pttc;
		this.effet = effet;
		this.expiration = expiration;
		this.avance = avance;
		this.rap = rap;
	}
	public ps_Matufac_EditerFactureDetailMono() {
		super();
	}
	
	
	
}
