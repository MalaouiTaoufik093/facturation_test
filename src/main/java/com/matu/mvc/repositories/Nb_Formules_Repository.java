package com.matu.mvc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.matu.mvc.models.Nb_Formules;

public interface Nb_Formules_Repository extends JpaRepository<Nb_Formules, Integer> {
	
	@Query(nativeQuery = true, value = "  select f.formule as 'formule',count(*) as 'nb' from wafaima_contrats c join wafaima_formules f on f.idformule=c.idformule\r\n" + 
			" where f.idusage = 'AVA_C1'\r\n" + 
			" group by f.formule")
	List<Nb_Formules> get_nb_formules_ava();
	@Query(nativeQuery = true, value = " select f.formule as 'formule',count(*) as 'nb' from wafaima_contrats c join wafaima_formules f on f.idformule=c.idformule\r\n" + 
			" where f.idusage = 'TT'\r\n" + 
			" group by f.formule")
	List<Nb_Formules> get_nb_formules_tt();

}
