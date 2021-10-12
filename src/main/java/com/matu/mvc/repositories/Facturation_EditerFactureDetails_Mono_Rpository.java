package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Facturation_EditerFactureDetails_Mono;
import com.matu.mvc.models.Facturations_Client;
import com.matu.mvc.models.Get_Facturation_Details;

public interface Facturation_EditerFactureDetails_Mono_Rpository  extends JpaRepository<Facturation_EditerFactureDetails_Mono, String>{
	
	      //*** Get data of EditionFacture_Mono   by Num facture ***
			@Query(nativeQuery = true, value = "exec [dbo].[ps_MatuFac_EditerFactureDetail_Mono] :facture ")
			List<Facturation_EditerFactureDetails_Mono> 
			get_data_facture_mono(@Param("facture") String facture);

}
