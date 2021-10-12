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
import com.matu.mvc.models.Facturation_OtoConvention;
import com.matu.mvc.models.Facturation_Quittances_Rd;
import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;
import com.matu.mvc.repositories.Facturations_Details;
import com.matu.mvc.repositories.Get_Facturation_Details_Repository;
import com.matu.mvc.repositories.Get_Facturation_Details_flotte_Repository;
import com.matu.mvc.repositories.OtoFactureMono_Repository;
import com.matu.mvc.repositories.Details_Facture_Repository;
import com.matu.mvc.repositories.Facturation_OtoConvention_Repository;
import com.matu.mvc.repositories.Facturation_Quittances_Rd_Repository;
import com.matu.mvc.repositories.ps_MatuFac_EditerFactureGlobaleMono_Repository;
import com.matu.mvc.services.UserPrincipal;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Rest_Facturation_Details {
	
	@Autowired
	Facturations_Details facturations_details;
	@Autowired
	Get_Facturation_Details_Repository get_facturation_details;
	@Autowired
	Get_Facturation_Details_flotte_Repository get_facturation_details_rep_flotte;
	@Autowired
	ps_MatuFac_EditerFactureGlobaleMono_Repository ps_MatuFac_EditerFactureGlobaleMono_Repository;
	@Autowired
	Details_Facture_Repository tt;
	@Autowired
	OtoFactureMono_Repository OtoFactureMono_Repository;
	@Autowired
	Facturation_OtoConvention_Repository Facturation_OtoConvention_Repository;

	@Autowired
	Facturation_Quittances_Rd_Repository Facturation_Quittances_Rd_Repository;
	
	            //** facture Mono ***
	@GetMapping("/etat_factures")
	public List<OtoFatctureMono> etat_factures() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return OtoFactureMono_Repository.etat_factures(idsite);
	}
	            //** facture Flotte ***
		@GetMapping("/etat_factures_flotte")
		public List<OtoFatctureMono> etat_factures_flotte() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
			int idsite = customUser.getidsite();
			return OtoFactureMono_Repository.etat_factures_flotte(idsite);
		}
	@GetMapping("/get_client_site_exercice_rd/{id}")
	public List<Facturations_Client> get_clients_by_exercice_rd(@PathVariable Integer id) {;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return facturations_details.Clients_by_site_and_exercice_rd(idsite, id);
	}
	@GetMapping("/get_client_site_exercice/{id}")
	public List<Facturations_Client> get_clients_by_exercice(@PathVariable Integer id) {;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return facturations_details.Clients_by_site_and_exercice(idsite, id);
	}
	
	@GetMapping("/get_client_flotte__site_exercice/{id}")
	public List<Facturations_Client> get_clients_flotte__by_exercice(@PathVariable Integer id) {;
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return facturations_details.Clients_flotte__by_site_and_exercice(idsite, id);
	}
	
	@GetMapping("/get_flotte_by_idclient/{idclient}")
	public List<Facturation_OtoConvention> get_flotte_by_client(@PathVariable Integer idclient) {;
		return Facturation_OtoConvention_Repository.get_flottes_by_idclient(idclient);
	}
	
	@GetMapping("/get_facture_detaille/{idclient}/{exercice}")
	public List<Get_Facturation_Details> get_facture_detaille(@PathVariable Integer idclient,@PathVariable Integer exercice) {
	return get_facturation_details.ps_MatuFac_GetFactureDetail(idclient,exercice);
	}
	// *** quittances rd ***
	@GetMapping("/get_facture_detaille_rd/{idclient}/{exercice}")
	public List<Facturation_Quittances_Rd> get_facture_detaille_rd(@PathVariable Integer idclient,@PathVariable Integer exercice) {
	return Facturation_Quittances_Rd_Repository.ps_MatuFac_GetFactureDetail_rd(idclient,exercice);
	}
	@GetMapping("/get_facture_detaille_flotte/{idclient}/{exercice}")
	public List<Get_Facturation_Details_flotte> get_facture_detaille_flotte(@PathVariable Integer idclient,@PathVariable Integer exercice) {;
		/*
		 * Authentication authentication =
		 * SecurityContextHolder.getContext().getAuthentication(); UserPrincipal
		 * customUser = (UserPrincipal) authentication.getPrincipal(); int idsite =
		 * customUser.getidsite();
		 */
	return get_facturation_details_rep_flotte.ps_MatuFac_GetFactureDetail_flotte(idclient,exercice);
	}
	

}
