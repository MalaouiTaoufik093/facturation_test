package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Get_Facturation_Details;
import com.matu.mvc.models.Get_Facturation_Details_flotte;




public interface Get_Facturation_Details_Repository  extends JpaRepository<Get_Facturation_Details, Integer>{
	@Query(nativeQuery = true, value = "exec [dbo].[ps_MatuFac_GetFactureDetail] :idclient,:exercice")
	List<Get_Facturation_Details> ps_MatuFac_GetFactureDetail(@Param("idclient") int idclient,@Param("exercice") int exercice);

	/*
	 * @Query(nativeQuery = true, value =
	 * "exec [dbo].[ps_MatuFac_GetFactureDetail_flotte] :idclient,:exercice")
	 * List<Get_Facturation_Details_flotte>
	 * ps_MatuFac_GetFactureDetail_flotte(@Param("idclient") int
	 * idclient,@Param("exercice") int exercice);
	 */

	
}
