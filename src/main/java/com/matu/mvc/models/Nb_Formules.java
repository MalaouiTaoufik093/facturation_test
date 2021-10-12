package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class Nb_Formules {
@javax.persistence.Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
//private Integer Id;	
private int nb;
private String formule;
//public Integer getId() {
//	return Id;
//}
//public void setId(Integer id) {
//	Id = id;
//}
public int getNb() {
	return nb;
}
public void setNb(int nb) {
	this.nb = nb;
}
public String getFormule() {
	return formule;
}
public void setFormule(String formule) {
	this.formule = formule;
}
public Nb_Formules(int nb, String formule) {
	super();
	this.nb = nb;
	this.formule = formule;
}
public Nb_Formules() {
	super();
}

}
