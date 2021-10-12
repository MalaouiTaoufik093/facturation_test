package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Info_Client_Facture;



@CrossOrigin("*")
public interface Info_Client_Detail_Repository extends  JpaRepository<Info_Client_Facture, Integer> {


	
	@Query(nativeQuery = true, value = "select distinct\r\n" + 
			"c.idclient,NumFacture \r\n" + 
			",Client=c.RAISONSOCIALE \r\n" + 
			",Adresse=c.ADRESSE1 + ' '+c.ADRESSE2 \r\n" + 
			",Telephone=c.TELEP1 \r\n" + 
			",email=AEMAIL\r\n" + 
			",c.ice,cin_pat=CIN_PAT\r\n" + 
			"from Matu_otoFactureMono f \r\n" + 
			"inner join  Matu_otoFactureMonoDetail d on f.IdFacture=d.IdFacture \r\n" + 
			"inner join StdClients c on c.IdClient=f.IdClient \r\n" + 
			"where numfacture=:numefacture ")
	Info_Client_Facture info_client_detail(@Param ("numefacture") String  numefacture);
	
	
	@Query(nativeQuery = true, value = "select distinct \r\n"
			+ "c.idclient\r\n"
			+ ",NumFacture = f.NumFacture\r\n"
			+ ",Client=c.RAISONSOCIALE  \r\n"
			+ ",Adresse=c.ADRESSE1 + ' '+c.ADRESSE2 \r\n"
			+ ",Telephone=c.TELEP1 \r\n"
			+ ",email=AEMAIL\r\n"
			+ ",c.ice,f.datecreation,cin_pat=CIN_PAT\r\n"
			+ "from Matu_otoFacture_Mono_Flotte f \r\n"
			+ "inner join  Matu_otoFactureDetail_Mono_Flotte d on f.IdFacture=d.IdFacture  \r\n"
			+ "inner join StdQuittances q on q.IdQuittance = d.idquittance\r\n"
			+ "inner join StdClients c on c.IdClient=q.IdClient \r\n"
			+ "where numfacture=:numefacture ")
	Info_Client_Facture info_client_detail_flotte(@Param ("numefacture") String  numefacture);
	
	

} 
