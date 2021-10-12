package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Get_Facturation_Globale;




public interface Get_Facturation_Globale_Repository  extends JpaRepository<Get_Facturation_Globale, Integer>{
	@Query(nativeQuery = true, value = "exec [dbo].[ps_MatuFac_GetFactureGlobale] :idclient,:exercice")
	List<Get_Facturation_Globale> ps_MatuFac_GetFactureGlobale(@Param("idclient") int idclient,@Param("exercice") int exercice);

	
}
