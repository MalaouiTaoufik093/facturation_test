package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Facturations_Client;
import com.matu.mvc.models.Get_Facturation_Details;

public interface Facturations_Details  extends JpaRepository<Facturations_Client, Long>{
	@Query(nativeQuery = true, value = "select distinct \r\n"
			+ "h.idclient \r\n"
			+ ",client = (select raisonsociale from stdclients where idclient = h.idassure) \r\n"
			+ ",h.IdSite\r\n"
			+ ",adresse=c.adresse1\r\n"
			+ "from OtoHistorique h join ntssites s on s.idsite=h.idsite\r\n"
			+ "join stdclients c on c.idclient = h.idclient\r\n"
			+ "join StdQuittances q on q.IdQuittance = h.idquittance\r\n"
			+ "where\r\n"
			+ "h.IdAnnulHist=0  and flotte ='N' and h.idsite=:idsite and year(date_effet) =:exercice\r\n"
			+ "and h.IdAnnulHist=0 and q.Encaissee='O'")
	List<Facturations_Client> Clients_by_site_and_exercice(@Param("idsite") int idsite,@Param("exercice") int exercice);
	// *************** Clients Risque Divers ********************
	@Query(nativeQuery = true, value = "select distinct \r\n" + 
			"c.idclient \r\n" + 
			",client = (select raisonsociale from stdclients where idclient = h.idassure) \r\n" + 
			",h.IdSite\r\n" + 
			",adresse=c.adresse1\r\n" + 
			"from rdpolices h join ntssites s on s.idsite=h.idsite\r\n" + 
			"join stdclients c on c.idclient = h.idclient\r\n" + 
			" where\r\n" + 
			" h.IdAnnulHist=0  and  h.idsite=:idsite and year(date_effet) =:exercice ")
	List<Facturations_Client> Clients_by_site_and_exercice_rd(@Param("idsite") int idsite,@Param("exercice") int exercice);
	
	@Query(nativeQuery = true, value = "select distinct \r\n" + 
			"c.idclient \r\n" + 
			",client = (select raisonsociale from stdclients where idclient = h.idclient) \r\n" + 
			",h.IdSite\r\n" + 
			",adresse=c.adresse1\r\n" + 
			"from OtoHistorique h join ntssites s on s.idsite=h.idsite\r\n" + 
			"join stdclients c on c.idclient = h.idclient\r\n" + 
			" where\r\n" + 
			" h.IdAnnulHist=0  and flotte ='O' and h.idsite=:idsite and year(date_effet) =:exercice ")
	List<Facturations_Client> Clients_flotte__by_site_and_exercice(@Param("idsite") int idsite,@Param("exercice") int exercice);

}
