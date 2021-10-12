package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Facturations_Client;

public interface Details_Flotte_Globale_Repository  extends JpaRepository<Details_facture_globale, Integer>{

	@Query(nativeQuery = true, value = "select * from Matu_Imp_DetailFlotteGlobale('18144003','2019')")
	List<Details_facture_globale> detail_facture_flotte_globale();
	
}
