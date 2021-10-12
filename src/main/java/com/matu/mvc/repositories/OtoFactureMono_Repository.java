package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.OtoFatctureMono;
import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;

public interface OtoFactureMono_Repository extends JpaRepository<OtoFatctureMono, Integer>{
	

	@Query(nativeQuery = true, value = "select * from Matu_otoFactureMono where numfacture=:numefacture ")
	List<OtoFatctureMono> get_facture_mono(@Param("numefacture") String numefacture);
	

	//*** facture Mono ***
	@Query(nativeQuery = true, value = "select f.*,c.raisonsociale as 'client',s.raisonsociale as 'site',\r\n" + 
			"type_facture = case when f.typefacture = 'D' then 'Détail' else 'Globale' end\r\n" + 
			",date_creation=  convert(varchar(12),f.datecreation,103)\r\n" + 
			"from Matu_otoFactureMono f join stdclients c on c.idclient  = f.idclient \r\n" + 
			"join ntssites s on s.idsite = f.idsite\r\n" + 
			"where s.IdSite =:idsite ")
	List<OtoFatctureMono> etat_factures(@Param("idsite") Integer idsite);
	
	//*** facture flotte ***
	@Query(nativeQuery = true, value = "	select distinct f.*,c.raisonsociale as 'client',s.raisonsociale as 'site' \r\n"
			+ "	,type_facture = case when f.typefacture = 'D' then 'Détail' else 'Globale' end \r\n"
			+ "	,f.NumFacture\r\n"
			+ "	,date_creation=  convert(varchar(12),f.datecreation,103) \r\n"
			+ "	from Matu_otoFacture_Mono_Flotte f\r\n"
			+ "	join Matu_otoFactureDetail_Mono_Flotte d on d.IdFacture = f.IdFacture\r\n"
			+ "	join StdQuittances q on q.IdQuittance  = d.IdQuittance\r\n"
			+ "	join stdclients c on c.idclient  = q.idclient  \r\n"
			+ "	join ntssites s on s.idsite = f.idsite \r\n"
			+ "	where s.IdSite  =:idsite ")
	List<OtoFatctureMono> etat_factures_flotte(@Param("idsite") Integer idsite);
	
   
}
