package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Edition_Facture_Flotte_Globale;

public interface Edition_Facture_Flotte_Globale_Repository  extends JpaRepository<Edition_Facture_Flotte_Globale, String>{

	@Query(nativeQuery = true, value = "select * from Matu_Imp_DetailFlotteGlobale('18144003','2019')")
	List<Edition_Facture_Flotte_Globale> Edition_Facture_Flotte_Globalee();
	@Query(nativeQuery = true, value = "select top 1 * from Matu_Imp_DetailFlotteGlobale('18144003','2019')")
	Edition_Facture_Flotte_Globale get_top1_Edition_Facture_Flotte_Globale();
}
