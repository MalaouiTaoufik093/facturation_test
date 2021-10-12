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
public class MATU_Client {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer idaffaire;
private Integer idclient;
private Integer id_avenant;
private String matricule;
private String client;
private String effet;
private String expiration;
private String usage;
private String avenant;
private String police;
private String attestation;
private String marque;
private String datemise;
private String puissance;
private String tonnage;
private String essence;
private Integer nb_place;
private String idscat;
private Integer periode_assure;




public Integer getPeriode_assure() {
	return periode_assure;
}


public void setPeriode_assure(Integer periode_assure) {
	this.periode_assure = periode_assure;
}


public String getIdscat() {
	return idscat;
}


public void setIdscat(String idscat) {
	this.idscat = idscat;
}


public Integer getNb_place() {
	return nb_place;
}


public void setNb_place(Integer nb_place) {
	this.nb_place = nb_place;
}


public String getEssence() {
	return essence;
}


public void setEssence(String essence) {
	this.essence = essence;
}


public MATU_Client() {
	super();
}


public String getPuissance() {
	return puissance;
}


public void setPuissance(String puissance) {
	this.puissance = puissance;
}


public String getTonnage() {
	return tonnage;
}


public void setTonnage(String tonnage) {
	this.tonnage = tonnage;
}


public Integer getId_avenant() {
	return id_avenant;
}

public void setId_avenant(Integer id_avenant) {
	this.id_avenant = id_avenant;
}

public Integer getIdaffaire() {
	return idaffaire;
}

public void setIdaffaire(Integer idaffaire) {
	this.idaffaire = idaffaire;
}

public Integer getIdclient() {
	return idclient;
}

public void setIdclient(Integer idclient) {
	this.idclient = idclient;
}

public String getMatricule() {
	return matricule;
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

public String getUsage() {
	return usage;
}

public void setUsage(String usage) {
	this.usage = usage;
}

public String getAvenant() {
	return avenant;
}

public void setAvenant(String avenant) {
	this.avenant = avenant;
}

public String getPolice() {
	return police;
}

public void setPolice(String police) {
	this.police = police;
}

public String getAttestation() {
	return attestation;
}

public void setAttestation(String attestation) {
	this.attestation = attestation;
}

public String getMarque() {
	return marque;
}

public void setMarque(String marque) {
	this.marque = marque;
}

public String getDatemise() {
	return datemise;
}

public void setDatemise(String datemise) {
	this.datemise = datemise;
}

public MATU_Client(Integer idclient, String matricule, String client, String effet, String expiration, String usage,
		String avenant, String police, String attestation, String marque, String datemise) {
	super();
	this.idclient = idclient;
	this.matricule = matricule;
	this.client = client;
	this.effet = effet;
	this.expiration = expiration;
	this.usage = usage;
	this.avenant = avenant;
	this.police = police;
	this.attestation = attestation;
	this.marque = marque;
	this.datemise = datemise;
}




}
