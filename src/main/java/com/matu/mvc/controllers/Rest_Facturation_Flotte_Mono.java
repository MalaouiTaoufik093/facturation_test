package com.matu.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matu.mvc.models.Facturations_Client;
import com.matu.mvc.models.Get_Facturation_Details;
import com.matu.mvc.models.Get_Facturation_Details_flotte;
import com.matu.mvc.models.OtoFatctureMono;
import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Facturation_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturation_EditerFactureDetails_Flotte;
import com.matu.mvc.models.Facturation_OtoConvention;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte_Mono;
import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;
import com.matu.mvc.repositories.Facturations_Details;
import com.matu.mvc.repositories.Get_Facturation_Details_Repository;
import com.matu.mvc.repositories.Get_Facturation_Details_flotte_Repository;
import com.matu.mvc.repositories.InsererFactureFlotte_Repository;
import com.matu.mvc.repositories.OtoFactureMono_Repository;
import com.matu.mvc.repositories.Details_Facture_Repository;
import com.matu.mvc.repositories.Facturation_Clients_Flotte_Mono_Repository;
import com.matu.mvc.repositories.Facturation_EditerFactureDetails_Flotte_Rpository;
import com.matu.mvc.repositories.Facturation_OtoConvention_Repository;
import com.matu.mvc.repositories.Facturation_Quittances_Clients_Flotte_Mono_Repository;
import com.matu.mvc.repositories.Facturation_Quittances_Clients_Flotte_Repository;
import com.matu.mvc.repositories.ps_MatuFac_EditerFactureGlobaleMono_Repository;
import com.matu.mvc.services.UserPrincipal;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Rest_Facturation_Flotte_Mono {
	
	@Autowired
	Facturation_Quittances_Clients_Flotte_Mono_Repository quittances_clients_flotte_mono_repository;

	@Autowired
	InsererFactureFlotte_Repository rp;

	@Autowired
	Facturation_Clients_Flotte_Mono_Repository clients_flotte_mono_repository;

	@Autowired
    Facturation_Quittances_Clients_Flotte_Repository facturation_quittances_client_flotte_repository;
	
	//*** test ***
		@GetMapping("/test/{exercice}/{quittances}/{idoperateur}")
		public String
		get_facture_detailleaa(@PathVariable String exercice,@PathVariable String quittances,@PathVariable Long idoperateur) {
		Integer idclient=40;
		return rp.insererfacture_mono_flotte(11, idclient, exercice, quittances, idoperateur);
		}
	
	//*** Récupérer Les Quittances  Mono Aprés choix des clients ***
	@GetMapping("/get_quittances_clients_mono/{exercice}/{clients}")
	public List<Facturation_Quittances_Clients_Flotte_Mono> 
	get_facture_detaille(@PathVariable Integer exercice,@PathVariable String clients) {
	return quittances_clients_flotte_mono_repository.Get_Quittances_Clients_Mono(exercice, clients);
	}
	
    //*** Récupérer Les Quittances Flotte Aprés choix des clients ***
	@GetMapping("/get_quittances_clients_flotte/{exercice}/{clients}")
	public List<Facturation_Quittances_Clients_Flotte> 
	get_quittances_flotte(@PathVariable Integer exercice,@PathVariable String clients) {
	return facturation_quittances_client_flotte_repository.Get_Quittances_Clients_Flotte(exercice, clients);
	}

	//*** Récupérer les Clients : Flotte et Mono Par Exercice et Site *** 
	@GetMapping("/get_client_Flotte_Mono_exercice/{exercice}")
	public List<Facturation_Clients_Flotte_Mono> get_clients_flotte_mono_exercice(@PathVariable Integer exercice) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return clients_flotte_mono_repository.Clients_flote_mono_by_site_and_exercice(idsite, exercice);
	}
	


}
