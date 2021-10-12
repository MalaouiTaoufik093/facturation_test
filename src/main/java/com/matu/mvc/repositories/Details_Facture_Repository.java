package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Info_Client_Facture;



@CrossOrigin("*")
public interface Details_Facture_Repository extends  JpaRepository<Details_facture, Integer> {


	
	@Query(nativeQuery = true, value = "exec [ps_MatuFac_EditerFactureDetailMono] :numefacture")
	List<Details_facture> details_facture(@Param ("numefacture") String  numefacture);
	
	
	
	

	
	@Query(nativeQuery = true, value = "exec [ps_MatuFac_EditerFactureDetail] :numefacture")
	List<Details_facture> details_facture_flotte(@Param ("numefacture") String  numefacture);
	/*
	 * @Query(nativeQuery = true, value =
	 * "select exercice from Matu_otofactureMono where NumFacture = :numefacture")
	 * String get_exercice(@Param ("numefacture") String numefacture);
	 */
	
	/*
	 * @Query(nativeQuery = true, value =
	 * "exec [ps_MatuFac_EditerFactureGlobalMono] :numefacture")
	 * List<Details_facture_globale> globale_facture(@Param ("numefacture") String
	 * numefacture);
	 */
//	@Query(nativeQuery = true, value = "select distinct\r\n" + 
//			"			 NumFacture\r\n" + 
//			"			,Client=c.RAISONSOCIALE\r\n" + 
//			"			,Adresse=c.ADRESSE1 + ' '+c.ADRESSE2\r\n" + 
//			"			,Telephone=c.TELEP1\r\n" + 
//			"			 from Matu_otoFactureMono f\r\n" + 
//			"			inner join  Matu_otoFactureMonoDetail d on f.IdFacture=d.IdFacture\r\n" + 
//			"			inner join StdClients c on c.IdClient=f.IdClient\r\n" + 
//			"			where NumFacture = '2019144D00002'")
//	Info_Client_Facture info_client_detail();
	
	

} 
