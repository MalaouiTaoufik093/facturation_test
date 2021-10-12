package com.matu.mvc.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity 
public class StdQuittances {
@Id 
@GeneratedValue (strategy =GenerationType.IDENTITY )
private Integer IdQuittance;
private Integer IdSite;
private Integer exercice;
private String police;
private Integer quittance;
private Integer idclient;
private String nature;
private String Date_Du;
private String Date_Au;
private Integer Source;
private double PNette;
private double Taxe;
private double Accessoire;
private double Timbre;
private double Commission;
private String Coassurance;
private Integer idaperiteur;
private String DirectCie;
private String RegleCoass;
private String DateEmis;
private double TaxePF;
private double NetCie;
private double PTotale;
private Integer Id_OldClient;
public StdQuittances(Integer idSite, Integer exercice, String police, Integer quittance, Integer idclient, String nature, String date_Du,
		String date_Au, Integer source, double pNette, double taxe, double accessoire, double timbre, double commission,
		String coassurance, Integer idaperiteur, String directCie, String regleCoass, String dateEmis, double taxePF,
		double netCie, double pTotale, Integer id_OldClient) {
	super();
	IdSite = idSite;
	this.exercice = exercice;
	this.police = police;
	this.quittance = quittance;
	this.idclient = idclient;
	this.nature = nature;
	Date_Du = date_Du;
	Date_Au = date_Au;
	Source = source;
	PNette = pNette;
	Taxe = taxe;
	Accessoire = accessoire;
	Timbre = timbre;
	Commission = commission;
	Coassurance = coassurance;
	this.idaperiteur = idaperiteur;
	DirectCie = directCie;
	RegleCoass = regleCoass;
	DateEmis = dateEmis;
	TaxePF = taxePF;
	NetCie = netCie;
	PTotale = pTotale;
	Id_OldClient = id_OldClient;
}
public Integer getIdQuittance() {
	return IdQuittance;
}
public void setIdQuittance(Integer idQuittance) {
	IdQuittance = idQuittance;
}

public String getPolice() {
	return police;
}
public void setPolice(String police) {
	this.police = police;
}
public Integer getExercice() {
	return exercice;
}
public void setExercice(Integer exercice) {
	this.exercice = exercice;
}
public Integer getIdSite() {
	return IdSite;
}
public void setIdSite(Integer idSite) {
	IdSite = idSite;
}
public Integer getQuittance() {
	return quittance;
}
public void setQuittance(Integer quittance) {
	this.quittance = quittance;
}
public Integer getIdclient() {
	return idclient;
}
public void setIdclient(Integer idclient) {
	this.idclient = idclient;
}
public String getNature() {
	return nature;
}
public void setNature(String nature) {
	this.nature = nature;
}
public String getDate_Du() {
	return Date_Du;
}
public void setDate_Du(String date_Du) {
	Date_Du = date_Du;
}
public String getDate_Au() {
	return Date_Au;
}
public void setDate_Au(String date_Au) {
	Date_Au = date_Au;
}
public Integer getSource() {
	return Source;
}
public void setSource(Integer source) {
	Source = source;
}
public double getPNette() {
	return PNette;
}
public void setPNette(double pNette) {
	PNette = pNette;
}
public double getTaxe() {
	return Taxe;
}
public void setTaxe(double taxe) {
	Taxe = taxe;
}
public double getAccessoire() {
	return Accessoire;
}
public void setAccessoire(double accessoire) {
	Accessoire = accessoire;
}
public double getTimbre() {
	return Timbre;
}
public void setTimbre(double timbre) {
	Timbre = timbre;
}
public double getCommission() {
	return Commission;
}
public void setCommission(double commission) {
	Commission = commission;
}
public String getCoassurance() {
	return Coassurance;
}
public void setCoassurance(String coassurance) {
	Coassurance = coassurance;
}
public Integer getIdaperiteur() {
	return idaperiteur;
}
public void setIdaperiteur(Integer idaperiteur) {
	this.idaperiteur = idaperiteur;
}
public String getDirectCie() {
	return DirectCie;
}
public void setDirectCie(String directCie) {
	DirectCie = directCie;
}
public String getRegleCoass() {
	return RegleCoass;
}
public void setRegleCoass(String regleCoass) {
	RegleCoass = regleCoass;
}
public String getDateEmis() {
	return DateEmis;
}
public void setDateEmis(String dateEmis) {
	DateEmis = dateEmis;
}
public double getTaxePF() {
	return TaxePF;
}
public void setTaxePF(double taxePF) {
	TaxePF = taxePF;
}
public double getNetCie() {
	return NetCie;
}
public void setNetCie(double netCie) {
	NetCie = netCie;
}
public double getPTotale() {
	return PTotale;
}
public void setPTotale(double pTotale) {
	PTotale = pTotale;
}
public Integer getId_OldClient() {
	return Id_OldClient;
}
public void setId_OldClient(Integer id_OldClient) {
	Id_OldClient = id_OldClient;
}
public StdQuittances() {
	//super();
}








}
