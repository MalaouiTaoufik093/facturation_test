package com.matu.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.Facturations_User;
import com.matu.mvc.models.NtsSites;

public interface Facturation_UserRepository extends JpaRepository<Facturations_User, Long>{
	
	Facturations_User findByUsername(String username);
	
	@Query(nativeQuery = true, value = " select u.*,s.raisonsociale as 'site' from facturations_user u join NtsSites s on s.idsite=u.idsite where id=:id ")
	Facturations_User get_user_by_id(@Param("id") Long id);
	

	
	  @Query(nativeQuery = true, value = " \r\n" +
	  "	select distinct codesite from  facturations_user u join ntssites s on s.idsite = u.idsite where u.idsite =:idsite "
	  ) String get_codesite_byidsite(@Param("idsite") Integer idsite);
	 
	
	
}
