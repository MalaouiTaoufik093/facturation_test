package com.matu.mvc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.matu.mvc.models.Facturations_User;
import com.matu.mvc.repositories.Facturation_UserRepository;

@Service
public class MyUserDetailsService  implements UserDetailsService{

	@Autowired
	Facturation_UserRepository userrepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
		Facturations_User wAFAIMA_User = userrepository.findByUsername(username);
		
		if (wAFAIMA_User == null) {
			
			throw new UsernameNotFoundException("user non trouvé");
		}
		
		return new UserPrincipal(wAFAIMA_User);
	}

}
