package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Details_Flotte_Avec_Retrait;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Edition_Facture_Flotte_Globale;

public interface Details_Flotte_Avec_Retrait_Repository  extends JpaRepository<Details_Flotte_Avec_Retrait, String>{

	@Query(nativeQuery = true, value = "select distinct\r\n"
			+ "			 Site='Bureau '+(select CodeSite+' / '+ RaisonSociale from NtsSites where Idsite=h.Idsite) +'--> Client : '+c.RAISONSOCIALE \r\n"
			+ "			 ,Police='Police : '+h.Police\r\n"
			+ "			 ,h.IdQuittance\r\n"
			+ "			 ,Quittance=cast(Quittance as varchar)\r\n"
			+ "			 ,Client=C.raisonsociale\r\n"
			+ "			 ,AdrClient1=C.ADRESSE1\r\n"
			+ "			 ,AdrClient2=C.ADRESSE2\r\n"
			+ "			 ,TelClient=C.TELEP1\r\n"
			+ "			 , Avenant=(select  Libelle from OtoAvenants where IdAvenant=h.IdAvenant)\r\n"
			+ "			 ,Attestation,h.Matricule,PLACES,R_VAL_NEUV,V_VAL_VENA ,DATE_EFFET=convert(varchar(10),DATE_EFFET,103) ,Date_Au=convert(varchar(10),h.Date_Au,103)\r\n"
			+ "			 ,V_VAL_GLA \r\n"
			+ "			  ,Rc=isnull((select sum( isnull( Q_PrimeNette,0)) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie in(1,9) and Acquise='O'),0)\r\n"
			+ "			 ,DR=isnull((select sum( isnull(Q_PrimeNette,0)) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie in(2,10) and Acquise='O'),0)\r\n"
			+ "			 ,Tierce=isnull((select isnull( Q_PrimeNette,0) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie in (7,13) and Acquise='O'),0)\r\n"
			+ "			 ,Inc=isnull((select isnull( Q_PrimeNette,0) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie=4 and Acquise='O'),0)\r\n"
			+ "			 ,Vol=isnull((select sum( isnull( Q_PrimeNette,0))  from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie in(3,12) and Acquise='O'),0)\r\n"
			+ "			 ,BDG=isnull((select isnull( Q_PrimeNette,0) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie=5 and Acquise='O'),0)\r\n"
			+ "			 ,DC=isnull((select isnull( Q_PrimeNette,0) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie=6 and Acquise='O'),0)\r\n"
			+ "			\r\n"
			+ "			 ,Autre=isnull((select sum(isnull( Q_PrimeNette,0))  from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and not IdGarantie in(1,9,2,10,7,13,4,5,6,29) and Acquise='O'),0)\r\n"
			+ "			 ,CATNAT=isnull((select sum( isnull(Q_PrimeNette,0)) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique and IdGarantie in(29) and Acquise='O'),0)\r\n"
			+ "		\r\n"
			+ "			 ,Cot_Ht=(select sum( isnull( Q_PrimeNette,0)) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique  and Acquise='O')\r\n"
			+ "			 \r\n"
			+ "			 ,TaxeParaFis=isnull(h.TaxeParaFis,0)\r\n"
			+ "			\r\n"
			+ "			 ,Taxe=(select sum( isnull( Q_Taxe,0)) from OtoHistoriqueDet  where IdHistorique=h.IdHistorique  and Acquise='O')\r\n"
			+ "			 ,AccTim=Q_ACCESS+Q_TaxePF+Q_TIMBRE\r\n"
			+ "			 ,CotTTC=h.Q_Total \r\n"
			+ "			 \r\n"
			+ "			 from OtoHistorique h inner join stdclients C on C.idclient=h.idclient \r\n"
			+ "inner join StdQuittances q on q.IdQuittance=h.IdQuittance\r\n"
			+ "inner join Matu_otoFactureDetail_Mono_Flotte fd on fd.IdQuittance = q.IdQuittance\r\n"
			+ "inner join Matu_otoFacture_Mono_Flotte f on f.IdFacture = fd.IdFacture\r\n"
			+ "\r\n"
			+ "where h.Idsite =:idsite  and f.NumFacture =:numfacture \r\n"
			+ "\r\n"
			+ "and year(date_effet)=:exercice")
	        List<Details_Flotte_Avec_Retrait> Details_Flote_Avec_Retrait(
	        		 @Param("idsite") int idsite
	        		,@Param("numfacture") String numfacture
	        		,@Param("exercice") int exercice);
	//@Param("numfacture") String numfacture,@Param("exercice") int exercice
}
