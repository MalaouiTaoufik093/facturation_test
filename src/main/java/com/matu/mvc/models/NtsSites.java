package com.matu.mvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity 
public class NtsSites {
@Id 
@GeneratedValue (strategy =GenerationType.IDENTITY )
private Integer idsite;
private String agence;
private String tel;
private String fax;
private String email;
private String codesite;
private String adresse;




public NtsSites(Integer idsite, String agence, String tel, String fax, String email, String codesite, String adresse) {
	super();
	this.idsite = idsite;
	this.agence = agence;
	this.tel = tel;
	this.fax = fax;
	this.email = email;
	this.codesite = codesite;
	this.adresse = adresse;
}



public String getAdresse() {
	return adresse;
}



public void setAdresse(String adresse) {
	this.adresse = adresse;
}



public String getCodesite() {
	return codesite;
}

public void setCodesite(String codesite) {
	this.codesite = codesite;
}

public Integer getIdsite() {
	return idsite;
}

public void setIdsite(Integer idsite) {
	this.idsite = idsite;
}

public String getAgence() {
	return agence;
}

public void setAgence(String agence) {
	this.agence = agence;
}

public String getTel() {
	return tel;
}

public void setTel(String tel) {
	this.tel = tel;
}

public String getFax() {
	return fax;
}

public void setFax(String fax) {
	this.fax = fax;
}

public String getEmail() {
	return email;
}

public void setEmail(String email) {
	this.email = email;
}

public NtsSites() {
	//super();
}








}
