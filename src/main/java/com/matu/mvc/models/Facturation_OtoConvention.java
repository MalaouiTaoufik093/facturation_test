package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Facturation_OtoConvention {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idconvention;
	private String flotte;
	private String police;
	public String getId_convention() {
		return idconvention;
	}
	public void setId_convention(String id_convention) {
		this.idconvention = id_convention;
	}
	public String getFlotte() {
		return flotte;
	}
	public void setFlotte(String flotte) {
		this.flotte = flotte;
	}
	public String getPolice() {
		return police;
	}
	public void setPolice(String police) {
		this.police = police;
	}
	public Facturation_OtoConvention(String id_convention, String flotte, String police) {
		super();
		this.idconvention = id_convention;
		this.flotte = flotte;
		this.police = police;
	}
	public Facturation_OtoConvention() {
		super();
	}	
	
	
}
