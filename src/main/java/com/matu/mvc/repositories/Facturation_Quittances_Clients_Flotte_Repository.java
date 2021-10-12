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
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturations_Client;
import com.matu.mvc.models.Info_Client_Facture;

@CrossOrigin("*")
public interface Facturation_Quittances_Clients_Flotte_Repository extends JpaRepository<Facturation_Quittances_Clients_Flotte, Integer> {
    
	//*** get quittances Flotte by exercice and clients ***
	@Query(nativeQuery = true, value 
	= "declare @Msg_ varchar(500) ,@NomFac varchar(15)\r\n"
			+ "exec [dbo].[ps_Get_Quittances_flotte2] :exercice,:clients, @NomFac  output,  @Msg_  output \r\n"
			+ "print @NomFac")
	List<Facturation_Quittances_Clients_Flotte> 
	Get_Quittances_Clients_Flotte(@Param("exercice") Integer exercice,@Param("clients") String clients);



}
