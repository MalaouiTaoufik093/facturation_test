package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.matu.mvc.models.ps_Matufac_EditerFactureDetailMono;
public interface ps_Matufac_EditerFactureDetailMono_Repository  extends JpaRepository<ps_Matufac_EditerFactureDetailMono, Integer>   {
	
	@Query(nativeQuery = true, value = "exec [ps_MatuFac_EditerFactureDetailMono] :numefacture")
	List<ps_Matufac_EditerFactureDetailMono> details_facture2(@Param ("numefacture") String  numefacture);
	
}
