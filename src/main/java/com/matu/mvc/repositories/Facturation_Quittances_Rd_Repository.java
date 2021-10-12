package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Details_facture;
import com.matu.mvc.models.Facturation_Quittances_Rd;
import com.matu.mvc.models.Get_Facturation_Details;
import com.matu.mvc.models.Get_Facturation_Details_flotte;




public interface Facturation_Quittances_Rd_Repository  extends JpaRepository<Facturation_Quittances_Rd, Integer>{
	
	@Query(nativeQuery = true, value = "exec [dbo].[ps_MatuFac_GetFactureDetail_rd] :idclient,:exercice")
	List<Facturation_Quittances_Rd> ps_MatuFac_GetFactureDetail_rd(@Param("idclient") int idclient,@Param("exercice") int exercice);
	/*
	 * @Query(nativeQuery = true, value =
	 * "exec [dbo].[ps_MatuFac_GetFactureDetail_flotte] :idclient,:exercice")
	 * List<Get_Facturation_Details_flotte>
	 * ps_MatuFac_GetFactureDetail_flotte(@Param("idclient") int
	 * idclient,@Param("exercice") int exercice);
	 */

}
