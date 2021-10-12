package com.matu.mvc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Info_Client_Facture;
import com.matu.mvc.repositories.Details_Facture_Repository;
import com.matu.mvc.repositories.Info_Client_Detail_Repository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class Rest_PS_Details_Facture {
	@Autowired
	Details_Facture_Repository details_Facture_Repository;
	@Autowired
	Info_Client_Detail_Repository info;
	
	
	@GetMapping("/details_facture/{num_facture}")
	public List<Details_facture> get_details_facture(@PathVariable String num_facture ) {
		return details_Facture_Repository.details_facture(num_facture);
	}
	@GetMapping("/info_client_detail/{num_facture}")
	public Info_Client_Facture info_client_detail(@PathVariable String num_facture) {
		return info.info_client_detail(num_facture);
	}

}
