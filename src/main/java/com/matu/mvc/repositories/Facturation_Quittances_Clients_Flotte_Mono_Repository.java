package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Details_facture_flotte;
import com.matu.mvc.models.Details_facture_globale;
import com.matu.mvc.models.Facturation_EditerFactureDetails_Mono;
import com.matu.mvc.models.Facturation_OtoConvention;
import com.matu.mvc.models.Facturation_Quittances_Clients_Flotte_Mono;
import com.matu.mvc.models.Facturations_Client;
import com.matu.mvc.models.Info_Client_Facture;

@CrossOrigin("*")
public interface Facturation_Quittances_Clients_Flotte_Mono_Repository extends JpaRepository<Facturation_Quittances_Clients_Flotte_Mono, Integer> {
    
	//*** get quittances by exercice and clients ***
	@Query(nativeQuery = true, value 
	= "declare @Msg_ varchar(500) ,@NomFac varchar(15)\r\n"
			+ "exec [dbo].[ps_Get_Quittances_Mono] :exercice,:clients, @NomFac  output,  @Msg_  output \r\n"
			+ "print @NomFac")
	List<Facturation_Quittances_Clients_Flotte_Mono> 
	Get_Quittances_Clients_Mono(@Param("exercice") Integer exercice,@Param("clients") String clients);
	
	



}
