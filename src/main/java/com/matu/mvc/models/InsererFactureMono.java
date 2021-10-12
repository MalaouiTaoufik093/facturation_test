package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class InsererFactureMono {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idclient;
	private Integer idsite;
	private String numfacture;
	private String exercice;
	public InsererFactureMono() {
		super();
	}
	public InsererFactureMono(Integer idclient, Integer idsite, String numfacture, String exercice) {
		super();
		this.idclient = idclient;
		this.idsite = idsite;
		this.numfacture = numfacture;
		this.exercice = exercice;
	}
	public Integer getIdclient() {
		return idclient;
	}
	public void setIdclient(Integer idclient) {
		this.idclient = idclient;
	}
	public Integer getIdsite() {
		return idsite;
	}
	public void setIdsite(Integer idsite) {
		this.idsite = idsite;
	}
	public String getNumfacture() {
		return numfacture;
	}
	public void setNumfacture(String numfacture) {
		this.numfacture = numfacture;
	}
	public String getExercice() {
		return exercice;
	}
	public void setExercice(String exercice) {
		this.exercice = exercice;
	}

	
	
}
