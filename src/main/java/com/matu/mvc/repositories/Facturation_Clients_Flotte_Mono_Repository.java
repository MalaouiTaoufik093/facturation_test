package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_flotte;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Facturation_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturation_OtoConvention;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturations_Client;
import com.matu.mvc.models.Info_Client_Facture;

@CrossOrigin("*")
public interface Facturation_Clients_Flotte_Mono_Repository extends JpaRepository<Facturation_Clients_Flotte_Mono, Integer> {
    

	//*** Get Clients Flotte and Mono by  exercice and Site *** 
	@Query(nativeQuery = true, value = "select distinct \r\n" + 
			"c.idclient \r\n" + 
			",client = (select raisonsociale from stdclients where idclient = h.idclient) \r\n" + 
			",h.IdSite\r\n" + 
			",adresse=c.adresse1\r\n" + 
			"from OtoHistorique h join ntssites s on s.idsite=h.idsite\r\n" + 
			"join stdclients c on c.idclient = h.idclient\r\n" + 
			" where\r\n" + 
			" h.IdAnnulHist=0   and h.idsite=:idsite and year(date_effet) =:exercice ")
	List<Facturation_Clients_Flotte_Mono> Clients_flote_mono_by_site_and_exercice(@Param("idsite") int idsite,@Param("exercice") int exercice);

}
