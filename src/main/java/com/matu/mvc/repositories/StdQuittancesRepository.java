package com.matu.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.matu.mvc.models.StdQuittances;

public interface StdQuittancesRepository extends JpaRepository<StdQuittances,Integer > {
	

	 @Modifying
	 @Query(value = "insert into StdQuittances \r\n" + 
	 		"( idSite,exercice,quittance,police,idclient,nature,date_Du,date_Au,source,statut,pNette,taxe,accessoire,timbre, commission,tva,     \r\n" + 
	 		"  coassurance,  idaperiteur,  directCie,  regleCoass,  dateEmis,  taxePF,netCie,  pTotale,  id_OldClient)\r\n" + 
	 		" values (:idSite,:exercice,:quittance,:police,:idclient,:nature,:date_Du,:date_Au,:source,:statut,:pNette,:taxe,:accessoire,:timbre,:commission,:tva,"
	 		+ ":coassurance,:idaperiteur,:directCie,:regleCoass,:dateEmis,:taxePF,:netCie,:pTotale,:id_OldClient)", nativeQuery = true)
	    @Transactional
	    void save_quittance(
	    		@Param ("idSite") Integer  idSite,
	    		@Param ("exercice") Integer  exercice,
	    		@Param ("quittance") Integer  quittance,
	    		@Param ("police") String  police,
	    		@Param ("idclient") Integer  idclient,
	    		@Param ("nature") String  nature,
	    		@Param ("date_Du") String  date_Du,
	    		@Param ("date_Au") String  date_Au,
	    		@Param ("source") Integer  source,
	    		@Param ("statut") Integer  statut,
	    		@Param ("pNette") double  pNette,
	    		@Param ("taxe") double  taxe,
	    		@Param ("accessoire") double  accessoire,
	    		@Param ("timbre") double  timbre,
	    		@Param ("commission") double  commission,
	    		@Param ("tva") double  tva,
	    		@Param ("coassurance") String  coassurance,
	    		@Param  ("idaperiteur") Integer  idaperiteur,
	    		@Param ("directCie") String  directCie,
	    		@Param ("regleCoass") String  regleCoass,
	    		@Param ("dateEmis") String  dateEmis,
	    		@Param ("taxePF") double  taxePF,
	    		@Param ("netCie") double  netCie,
	    		@Param ("pTotale") double  pTotale,
	    		@Param ("id_OldClient") Integer  id_OldClient
	    		);
	 
       	 
		@Query(nativeQuery = true, value = "select Numero From StdNumeros Where IdModule = 'QUITT' and Cloture = 'N' and Exercice = Year(getdate()) ")
		Integer get_quittance_from_stdnemeros();
		
		@Modifying
		@Query(value = "Update StdNumeros Set Numero = :numeros Where IdModule = 'QUITT' and Cloture = 'N' and Exercice = Year(getdate())", nativeQuery = true)
		@Transactional
		void update_quittance_stdnumeros(@Param ("numeros") Integer  numeros);
		 
}
