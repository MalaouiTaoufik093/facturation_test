package com.matu.mvc.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.matu.mvc.models.Details_Flotte_Avec_Retrait;
import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_flotte;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Edition_Facture_Flotte_Globale;
import com.matu.mvc.models.Facturation_Details_facture_Rd;
import com.matu.mvc.models.Facturation_EditerFactureDetails_Flotte;
import com.matu.mvc.models.Facturation_EditerFactureDetails_Mono;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturation_Quittances_Rd;
import com.matu.mvc.models.Info_Client_Facture;
import com.matu.mvc.models.NtsSites;
import com.matu.mvc.models.OtoFatctureMono;
import com.matu.mvc.models.StdQuittances;

import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;
import com.matu.mvc.repositories.OtoFactureMono_Repository;
import com.matu.mvc.repositories.StdQuittancesRepository;
import com.matu.mvc.repositories.Details_Facture_Repository;
import com.matu.mvc.repositories.Details_Facture_flotte_Repository;
import com.matu.mvc.repositories.Details_Flotte_Avec_Retrait_Repository;
import com.matu.mvc.repositories.Details_Flotte_Globale_Repository;
import com.matu.mvc.repositories.Edition_Facture_Flotte_Globale_Repository;
import com.matu.mvc.repositories.Facturation_Details_Facture_Rd_Repository;
import com.matu.mvc.repositories.Facturation_EditerFactureDetails_Flotte_Rpository;
import com.matu.mvc.repositories.Facturation_EditerFactureDetails_Mono_Rpository;
import com.matu.mvc.repositories.Facturation_Quittances_Clients_Flotte_Mono_Repository;
import com.matu.mvc.repositories.Facturation_Quittances_Rd_Repository;
import com.matu.mvc.repositories.Facturation_UserRepository;
import com.matu.mvc.repositories.Globale_Facture_Repository;
import com.matu.mvc.repositories.Info_Client_Detail_Repository;
import com.matu.mvc.repositories.InsererFactureFlotte_Repository;
import com.matu.mvc.repositories.InsererFactureMono_Repository;
import com.matu.mvc.repositories.NtsSites_Repository;

import com.matu.mvc.repositories.ps_MatuFac_EditerFactureGlobaleMono_Repository;

import com.matu.mvc.services.Facture;
import com.matu.mvc.services.FileHandelService;
import com.matu.mvc.services.UserPrincipal;

@Controller
public class FacturationController {

	private Facture factures;
	private FileHandelService fileHandelService;
	private ServletContext context;
	@Autowired
	private StdQuittancesRepository stdQuittances;

	@Autowired
	Facture facture;
	@Autowired
	OtoFactureMono_Repository otofacturemonorepository;
	@Autowired
	ps_MatuFac_EditerFactureGlobaleMono_Repository ps_MatuFac_EditerFactureGlobaleMono_Repository;
	@Autowired
	Details_Facture_Repository Test_Repository;
	@Autowired
	Details_Facture_flotte_Repository details_facture_flotte_repository;
	@Autowired
	Info_Client_Detail_Repository info;
	@Autowired
	InsererFactureMono_Repository InsererFactureMono_Repository;
	@Autowired
	InsererFactureFlotte_Repository InsererFactureFlotte_Repository;
	@Autowired
	Facturation_UserRepository Facturation_UserRepository;
	@Autowired
	Globale_Facture_Repository Globale_Facture_Repository;
	@Autowired
	Details_Flotte_Globale_Repository Details_Flotte_Globale_Repository;
	@Autowired
	Edition_Facture_Flotte_Globale_Repository edition_facture_flotte_globale_repository;
	@Autowired
	NtsSites_Repository NtsSites_Repository;
	@Autowired
	Facturation_Quittances_Clients_Flotte_Mono_Repository Facturation_Quittances_Clients_Flotte_Mono_Repository;
    @Autowired
    Facturation_EditerFactureDetails_Mono_Rpository editerfacturedetails_mono_repository ;
    @Autowired
    Facturation_EditerFactureDetails_Flotte_Rpository editerfacturedetails_Flotte_repository ; 
    @Autowired
    Facturation_Quittances_Rd_Repository Facturation_Quittances_Rd_Repository ; 
    @Autowired
    Facturation_Details_Facture_Rd_Repository Facturation_Details_Facture_Rd_Repository ; 
    @Autowired
    com.matu.mvc.repositories.Details_Flotte_Avec_Retrait_Repository Details_Flotte_Avec_Retrait_Repository;
    
    
	@Autowired
	public FacturationController(Facture contrat_service, FileHandelService fileHandelService,
			ServletContext context) {
		this.facture = contrat_service;
		this.fileHandelService = fileHandelService;
		this.context = context;
	}

	@GetMapping(value = "/statistiques")
	public String dashboard(Model model) {
		return "WAFAIMA_dashbord";
	}

	@GetMapping(value = "/")
	public String get_clients_by_exercice(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Details2";
	}

	@GetMapping(value = "/Facturations_Details_flotte")
	public String factures_flotte(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Details_flotte";
	}
	
	@GetMapping(value = "/rd")
	public String factures_risque_divers(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_RD";
	}
	//*** show page facturation_flotte_avec_retrait ***
		@GetMapping(value = "/facturations_flotte_avec_retrait")
		public String facturations_flotte_avec_retrait(Model model) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
			String libelle_user = customUser.getlibelle();
			model.addAttribute("libelle_user", libelle_user);
			return "Facturations_Flotte_Avec_Retrait";
		}

	@GetMapping("/quittances_flotte_mono/{exercice}/{clients_selected}")
	public String sav2e(Model model,@PathVariable String exercice,@PathVariable String clients_selected) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("get_exercice", exercice);
		model.addAttribute("clients_selected", clients_selected);
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Quittances_Flotte_Mono4";
	}
	
	// *** Quittances Flotte Avec Retrait ***
	@GetMapping("/quittances_flotte_Avec_Retrait/{exercice}/{clients_selected}")
	public String quittances_flotte_Avec_Retrait(Model model,@PathVariable String exercice,@PathVariable String clients_selected) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("get_exercice", exercice);
		model.addAttribute("clients_selected", clients_selected);
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Quittances_Flotte_Avec_Retrait";
	}
	
	
	@GetMapping(value = "/Facturations_Flotte_Mono")
	public String factures_flotte_mono(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Flotte_Mono";
		//return "Facturations_Quittances_Flotte_Mono4";
	}

	
	@GetMapping(value = "/facture_globale")
	public String get_clients_by_exercice2(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Globale2";
	}
	
	@GetMapping(value = "/Facturations_Globale_flotte")
	public String get_clients_by_exercice2_flotte(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "Facturations_Globale_flotte";
	}
	
	

	@GetMapping(value = "/details_factures_globale")
	public String details_facturations_globale(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser.getlibelle();
		model.addAttribute("libelle_user", libelle_user);
		return "details_facturations_globale";
	}

	@GetMapping("/login")
	public String login(Model model) {
		return "login";
	}

	@GetMapping("/save_facture/{idclient}/{exercice}/{quittances}")
	public String save(@PathVariable Integer idclient, @PathVariable String exercice, @PathVariable String quittances) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		Long idoperateur = customUser.getidoperateur();
		String codesite = Facturation_UserRepository.get_codesite_byidsite(idsite);
		//String quittances2 = "2026457&2026464&2104264&";
		InsererFactureMono_Repository.insererfacturemono(idsite, idclient, exercice, quittances,idoperateur);
		String facture = InsererFactureMono_Repository.get_current_facture(codesite, exercice, idclient);

		return "redirect:/pdf/" + facture + "/" + exercice + "/" + idsite;
	}
	@GetMapping("/save_facture_rd/{idclient}/{exercice}/{quittances}")
	public String save_rd(@PathVariable Integer idclient, @PathVariable String exercice, @PathVariable String quittances) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		Long idoperateur = customUser.getidoperateur();
		String codesite = Facturation_UserRepository.get_codesite_byidsite(idsite);
		//String quittances2 = "2026457&2026464&2104264&";
		InsererFactureMono_Repository.insererfacturerd(idsite, idclient, exercice, quittances,idoperateur);
		String facture = InsererFactureMono_Repository.get_current_facture(codesite, exercice, idclient);

		return "redirect:/pdf_rd/" + facture + "/" + exercice + "/" + idsite;
	}
	@GetMapping("/save_facture_mono_flotte/{exercice}/{quittances}")
	public String save_facture_mono_flotte(@PathVariable String exercice, @PathVariable String quittances) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		Long idoperateur = customUser.getidoperateur();
		String codesite = Facturation_UserRepository.get_codesite_byidsite(idsite);
        String facture = InsererFactureFlotte_Repository.insererfacture_mono_flotte(idsite, 111, exercice, quittances,idoperateur);
		return "redirect:/pdf_details_mono_flotte/" + facture + "/" + exercice + "/" + idsite;
	}
	@GetMapping("/save_facture_flotte_avec_retrait/{exercice}/{quittances}")
	public String save_facture_flotte_avec_retrait(@PathVariable String exercice, @PathVariable String quittances) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		Long idoperateur = customUser.getidoperateur();
		String codesite = Facturation_UserRepository.get_codesite_byidsite(idsite);
        String facture = InsererFactureFlotte_Repository.insererfacture_mono_flotte(idsite, 111, exercice, quittances,idoperateur);
		return "redirect:/pdf_flotte_avec_retrait/" + facture + "/" + exercice + "/" + idsite;
	}
	@GetMapping("/save_facture_flotte/{idclient}/{exercice}/{quittances}")
	public String save_facture_flotte(@PathVariable Integer idclient, @PathVariable String exercice, @PathVariable String quittances) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		Long idoperateur = customUser.getidoperateur();
		String codesite = Facturation_UserRepository.get_codesite_byidsite(idsite);
		//String quittances2 = "2026457&2026464&2104264&";
		InsererFactureFlotte_Repository.insererfactureflotte(idsite, idclient, exercice, quittances,idoperateur);
		String facture = InsererFactureFlotte_Repository.get_current_facture_flotte(codesite, exercice, idclient);

		return "redirect:/pdf_details_flotte/" + facture + "/" + exercice + "/" + idsite;
	}
	@GetMapping(value = "/pdf_edition_facture_flotte_globale")
	public void genegere_details_factures_globale(HttpServletRequest request, HttpServletResponse response) {
		List<Edition_Facture_Flotte_Globale> data_facture = edition_facture_flotte_globale_repository
				.Edition_Facture_Flotte_Globalee();
		Edition_Facture_Flotte_Globale info_data_facturec = edition_facture_flotte_globale_repository
				.get_top1_Edition_Facture_Flotte_Globale();
		boolean isFlag = facture.createPdf3(data_facture, info_data_facturec, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}

	@GetMapping("/save_facture_globale/{idclient}/{exercice}/{quittances}")
	public String save_facture_globale(@PathVariable Integer idclient, @PathVariable String exercice,
			@PathVariable String quittances) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		Long idoperateur = customUser.getidoperateur();
		String codesite = Facturation_UserRepository.get_codesite_byidsite(idsite);
		String quittances2 = "2026457&2026464&2104264&";
		InsererFactureMono_Repository.insererfactureglobale(idsite, idclient, exercice, quittances,idoperateur);
		String facture = InsererFactureMono_Repository.get_current_facture_globale(codesite, exercice, idclient);

		return "redirect:/pdf_globale/" + facture + '/' + exercice + '/' + idsite;
	}

	@GetMapping(value = "/pdf/{num_facture}/{exercice}/{idsite}")
	public void allPdf(HttpServletRequest request, HttpServletResponse response, @PathVariable String num_facture,
			@PathVariable String exercice, @PathVariable Integer idsite) {
		List<Details_facture> quittances = Test_Repository.details_facture(num_facture);
		Info_Client_Facture info_client = info.info_client_detail(num_facture);
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		boolean isFlag = facture.createPdf(quittances, info_client, exercice, infos_site, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}
	

	//*** pdf rd ***
	@GetMapping(value = "/pdf_rd/{num_facture}/{exercice}/{idsite}")
	public void allPdf_rd(HttpServletRequest request, HttpServletResponse response, @PathVariable String num_facture,
			@PathVariable String exercice, @PathVariable Integer idsite) {
		List<Facturation_Details_facture_Rd> quittances = Facturation_Details_Facture_Rd_Repository.details_facture_rd(num_facture);
		Info_Client_Facture info_client = info.info_client_detail(num_facture);
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		boolean isFlag = facture.createPdf_rd(quittances, info_client, exercice, infos_site, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}
	// ** Génération de Facture Flotte  Avec Retrait***
	@GetMapping(value = "/pdf_flotte_avec_retrait/{num_facture}/{exercice}/{idsite}")
	public void pdf_flotte_avec_retrait(HttpServletRequest request, HttpServletResponse response, @PathVariable String num_facture,
			@PathVariable Integer exercice, @PathVariable Integer idsite) {
		// *** récupérer les quittances mono ***
		List<Facturation_EditerFactureDetails_Mono> quittances_mono = editerfacturedetails_mono_repository.get_data_facture_mono(num_facture);
		// *** récupérer les quittances flotte ***
		List<Details_Flotte_Avec_Retrait> details_flotte_avec_retrait =  Details_Flotte_Avec_Retrait_Repository.Details_Flote_Avec_Retrait(idsite,num_facture,exercice); 
		List<Facturation_EditerFactureDetails_Flotte> quittances_flotte = editerfacturedetails_Flotte_repository.get_data_facture_flotte(num_facture);
		Info_Client_Facture info_client = info.info_client_detail_flotte(num_facture);
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		boolean isFlag = facture.createPdf_flotte_avec_retrait(quittances_flotte,details_flotte_avec_retrait, info_client,exercice, infos_site, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}
	//*** pdf quittances facture avec retrait (Quittances) ****
	@GetMapping(value = "/pdf_flotte_avec_retrait_quittances/{num_facture}/{exercice}/{idsite}")
	public void pdf_flotte_avec_retrait_quittance(HttpServletRequest request, HttpServletResponse response, @PathVariable String num_facture,
			@PathVariable Integer exercice, @PathVariable Integer idsite) {
		// *** récupérer les quittances mono ***
		List<Facturation_EditerFactureDetails_Mono> quittances_mono = editerfacturedetails_mono_repository.get_data_facture_mono(num_facture);
		// *** récupérer les quittances flotte ***
		List<Details_Flotte_Avec_Retrait> details_flotte_avec_retrait =  Details_Flotte_Avec_Retrait_Repository.Details_Flote_Avec_Retrait(idsite,num_facture,exercice); 
		List<Facturation_EditerFactureDetails_Flotte> quittances_flotte = editerfacturedetails_Flotte_repository.get_data_facture_flotte(num_facture);
		Info_Client_Facture info_client = info.info_client_detail_flotte(num_facture);
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		boolean isFlag = facture.createPdf_flotte_avec_retrait_Quittances(details_flotte_avec_retrait, info_client,exercice, infos_site, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}
	
	
	@GetMapping(value = "/pdf_details_mono_flotte/{num_facture}/{exercice}/{idsite}")
	public void facture_mono_flotte(HttpServletRequest request, HttpServletResponse response, @PathVariable String num_facture,
			@PathVariable Integer exercice, @PathVariable Integer idsite) {
		// *** récupérer les quittances mono ***
		List<Facturation_EditerFactureDetails_Mono> quittances_mono = editerfacturedetails_mono_repository.get_data_facture_mono(num_facture);
		// *** récupérer les quittances flotte ***
		List<Facturation_EditerFactureDetails_Flotte> quittances_flotte = editerfacturedetails_Flotte_repository.get_data_facture_flotte(num_facture);
		Info_Client_Facture info_client = info.info_client_detail(num_facture);
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		boolean isFlag = facture.createPdf_mono_flotte(quittances_mono,quittances_flotte, info_client, exercice, infos_site, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}
	
	
	@GetMapping(value = "/pdf_details_flotte/{num_facture}/{exercice}/{idsite}")
	public void pdf_details_flotte(HttpServletRequest request, HttpServletResponse response, @PathVariable String num_facture,
			@PathVariable String exercice, @PathVariable Integer idsite) {
		List<Details_facture_flotte> quittances = details_facture_flotte_repository.details_facture_flotte(num_facture);
		Info_Client_Facture info_client = info.info_client_detail(num_facture);
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		boolean isFlag = facture.createPdf_flotte(quittances, info_client, exercice, infos_site, context, request, response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}
	@GetMapping(value = "/pdf_globale/{num_facture}/{exercice}/{idsite}")
	public void allPdf_global(HttpServletRequest request, HttpServletResponse response,
			@PathVariable String num_facture, @PathVariable String exercice,@PathVariable Integer idsite) {
		NtsSites infos_site = NtsSites_Repository.get_infos_site(idsite);
		List<Details_facture> quittances = Test_Repository.details_facture(num_facture);
		List<Details_facture_globale> quittances_globale = Globale_Facture_Repository.globale_facture(num_facture);
		Info_Client_Facture info_client = info.info_client_detail(num_facture);
		boolean isFlag = facture.createPdf_globale(quittances_globale,quittances, info_client, exercice,infos_site, context, request,
				response);
		if (isFlag) {
			String fullPath = request.getServletContext().getRealPath("/resources/report/" + "employees" + ".pdf");
			fileHandelService.filedownload(fullPath, response, "employees.pdf");
		}
	}

	@GetMapping(value = "/etat_factures")
	public String etat_factures(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser = (UserPrincipal) authentication.getPrincipal();
		int idsite = customUser.getidsite();
		/*
		 * List<WAFAIMA_Contrats> offers_clients =
		 * contratrepository.clients_ayant_offers(idsite); List<WAFAIMA_Garanties>
		 * garanties_formule = garantiesrepository.get_garanties_by_idformule(2);
		 */
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		UserPrincipal customUser2 = (UserPrincipal) authentication.getPrincipal();
		String libelle_user = customUser2.getlibelle();
		//model.addAttribute("offers_clients", offers_clients);
		model.addAttribute("libelle_user", libelle_user);

		return "etat_factures";
	}

	@GetMapping("/user")
	public String userIndex() {
		return "user/index";
	}



}
