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
import com.matu.mvc.models.OtoFatctureMono;
import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Edition_Facture_Flotte_Globale;
import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;
import com.matu.mvc.repositories.Facturations_Details;
import com.matu.mvc.repositories.Get_Facturation_Details_Repository;
import com.matu.mvc.repositories.OtoFactureMono_Repository;
import com.matu.mvc.repositories.Details_Facture_Repository;
import com.matu.mvc.repositories.Edition_Facture_Flotte_Globale_Repository;
import com.matu.mvc.repositories.ps_MatuFac_EditerFactureGlobaleMono_Repository;
import com.matu.mvc.repositories.ps_Matufac_EditerFactureDetailMono_Repository;
import com.matu.mvc.services.UserPrincipal;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Rest_Edition_Details_Facture_Globale {

	@Autowired
	Edition_Facture_Flotte_Globale_Repository edition_Facture_Flotte_Globale_Repository;
	@Autowired
	ps_Matufac_EditerFactureDetailMono_Repository ps_Matufac_EditerFactureDetailMono_Repository;
	
	
	
	@GetMapping("/edition_details_facture_globale")
	public List<Edition_Facture_Flotte_Globale> edition_facture_globale() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		return edition_Facture_Flotte_Globale_Repository.Edition_Facture_Flotte_Globalee();
	}
	
	
	@GetMapping("/testo")
	public List<ps_Matufac_EditerFactureDetailMono> edjhjkhkjhd() {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		//int idsite = customUser.getidsite();
		//return edition_Facture_Flotte_Globale_Repository.Edition_Facture_Flotte_Globalee();
		return ps_Matufac_EditerFactureDetailMono_Repository.details_facture2("2019144G00002");
	}
	
	
	
	
	

}
