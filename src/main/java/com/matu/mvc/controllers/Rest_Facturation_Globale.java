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


import com.matu.mvc.repositories.Get_Facturation_Globale_Repository;
import com.matu.mvc.repositories.Get_Facturation_Globale_flotte_Repository;
import com.matu.mvc.models.Get_Facturation_Globale;
import com.matu.mvc.models.Get_Facturation_Globale_flotte;
import com.matu.mvc.services.UserPrincipal;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Rest_Facturation_Globale {
	
	
	@Autowired
	Get_Facturation_Globale_Repository get_Facturation_Globale_Repository;

	@Autowired
	Get_Facturation_Globale_flotte_Repository get_Facturation_Globale_flotte_Repository;
	
	
	
	/*
	 * @GetMapping("/etat_factures") public List<OtoFatctureMono> etat_factures() {;
	 * return OtoFactureMono_Repository.etat_factures(); }
	 * 
	 * @GetMapping("/get_client_site_exercice/{id}") public
	 * List<Facturations_Client> get_clients_by_exercice(@PathVariable Integer id)
	 * {; Authentication authentication =
	 * SecurityContextHolder.getContext().getAuthentication(); UserPrincipal
	 * customUser = (UserPrincipal) authentication.getPrincipal(); int idsite =
	 * customUser.getidsite(); return
	 * facturations_details.Clients_by_site_and_exercice(idsite, id); }
	 */
	
	@GetMapping("/get_facture_globale/{idclient}/{exercice}")
	public List<Get_Facturation_Globale> get_facture_globale(@PathVariable Integer idclient,@PathVariable Integer exercice) {;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
	return get_Facturation_Globale_Repository.ps_MatuFac_GetFactureGlobale(idclient, exercice);
	}
	
	@GetMapping("/get_facture_globale_flotte/{idclient}/{exercice}")
	public List<Get_Facturation_Globale_flotte> get_facture_globale_flotte(@PathVariable Integer idclient,@PathVariable Integer exercice) {;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
	return get_Facturation_Globale_flotte_Repository.ps_MatuFac_GetFactureGlobale(idclient, exercice);
	}
	

}
