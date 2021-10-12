package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_flotte;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Facturation_OtoConvention;
import com.matu.mvc.models.Info_Client_Facture;

@CrossOrigin("*")
public interface Facturation_OtoConvention_Repository extends JpaRepository<Facturation_OtoConvention, Integer> {

	@Query(nativeQuery = true, value = "  select idconvention,flotte=libelle,police from OtoConventionsIdt where flotte = 'O' and Actif = 'O'\r\n"
			+ "  and idsouscripteur =:idclient")
	List<Facturation_OtoConvention> get_flottes_by_idclient(@Param("idclient") Integer idclient);

	

}
