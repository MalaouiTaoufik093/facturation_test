package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.OtoFatctureMono;
import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;

public interface ps_MatuFac_EditerFactureGlobaleMono_Repository extends JpaRepository<ps_Matufac_EditerFactureDetailMono, Integer> {
	
	/*
	 * @Query(nativeQuery = true, value = "\r\n" +
	 * "exec [dbo].[ps_MatuFac_EditerFactureDetailMono]  '2019144D00002'")
	 * List<ps_Matufac_EditerFactureDetailMono> ps_MatuFac_GetFactureDetail22();
	 */
	@Query(nativeQuery = true, value = "select NumFacture\r\n" + 
			",DateFacture=convert(varchar(10),DateFacture,103)\r\n" + 
			",Client=c.RAISONSOCIALE\r\n" + 
			",Adresse=c.ADRESSE1 + ' '+c.ADRESSE2\r\n" + 
			",Telephone=c.TELEP1\r\n" + 
			",q.Police\r\n" + 
			",h.Matricule\r\n" + 
			",PHT=isnull(PNette,0)+isnull(Accessoire,0)+isnull(Timbre,0)+isnull(q.TaxeParaFis,0)+isnull(TaxePF,0)\r\n" + 
			",Taxe=q.Taxe\r\n" + 
			",PTTC=q.PTotale\r\n" + 
			",Tot_PHT=0\r\n" + 
			",Tot_Taxe=0\r\n" + 
			",Tot_PTTC=0\r\n" + 
			"--into #Prod\r\n" + 
			" from Matu_otoFactureMono f\r\n" + 
			"inner join  Matu_otoFactureMonoDetail d on f.IdFacture=d.IdFacture\r\n" + 
			"inner join StdClients c on c.IdClient=f.IdClient\r\n" + 
			"inner join StdQuittances q on q.IdQuittance=d.IdQuittance\r\n" + 
			"inner join OtoHistorique h on h.IdQuittance=q.IdQuittance\r\n" + 
			"where NumFacture = '2019144D00001'")
	List<ps_Matufac_EditerFactureDetailMono> get_detailsfacture_mono();
	


}
