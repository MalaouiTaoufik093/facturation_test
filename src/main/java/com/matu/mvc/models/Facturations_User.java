package com.matu.mvc.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
public class Facturations_User {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    private String username;
//	    private String email;
	    private String password;
	    private int idsite;
	    private String libelle;
	    private Long idoperateur;
		public Long getId() {
			return id;
		}
		
		public Long getIdoperateur() {
			return idoperateur;
		}

		public void setIdoperateur(Long idoperateur) {
			this.idoperateur = idoperateur;
		}

		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public int getIdsite() {
			return idsite;
		}
		public void setIdsite(int idsite) {
			this.idsite = idsite;
		}
		public String getLibelle() {
			return libelle;
		}
		public void setLibelle(String libelle) {
			this.libelle = libelle;
		}
		public Facturations_User(String username, String password, int idsite, String libelle) {
			super();
			this.username = username;
			this.password = password;
			this.idsite = idsite;
			this.libelle = libelle;
		}
		public Facturations_User() {
			//super();
		}	    
	    	    
	  
	    
	    
	    
}
