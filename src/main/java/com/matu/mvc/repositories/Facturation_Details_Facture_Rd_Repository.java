package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Facturation_Details_facture_Rd;
import com.matu.mvc.models.Facturation_Quittances_Rd;
import com.matu.mvc.models.Info_Client_Facture;



@CrossOrigin("*")
public interface Facturation_Details_Facture_Rd_Repository extends  JpaRepository<Facturation_Details_facture_Rd, Integer> {



	
	@Query(nativeQuery = true, value = "exec [ps_MatuFac_EditerFactureDetailRD] :numefacture")
	List<Facturation_Details_facture_Rd> details_facture_rd(@Param ("numefacture") String  numefacture);
	
	


} 
