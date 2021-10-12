package com.matu.mvc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.matu.mvc.models.NtsSites;

public interface NtsSites_Repository extends JpaRepository<NtsSites, Integer> {
	
	@Query(nativeQuery = true, value = " select \r\n" + 
			"agence = RaisonSociale\r\n" + 
			",tel = Telephone\r\n" + 
			",fax=Fax\r\n" + 
			",email=EMail,adresse\r\n" + 
			",*\r\n" + 
			"from ntssites\r\n" + 
			"where idsite =:idsite ")
	NtsSites get_infos_site(@Param("idsite") Integer idsite);

}
