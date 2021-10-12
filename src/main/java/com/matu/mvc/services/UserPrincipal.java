package com.matu.mvc.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.matu.mvc.models.Facturations_User;

public class UserPrincipal implements UserDetails {

	private Facturations_User wAFAIMA_User;
	
	
	
	public UserPrincipal(Facturations_User wAFAIMA_User) {
//		super();
		this.wAFAIMA_User = wAFAIMA_User;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return wAFAIMA_User.getPassword();
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return wAFAIMA_User.getUsername();
	}
	
	//@Override
	public int getidsite() {
		// TODO Auto-generated method stub
		return wAFAIMA_User.getIdsite();
	}
	//@Override
		public Long getidoperateur() {
			// TODO Auto-generated method stub
			return wAFAIMA_User.getIdoperateur();
		}
	//@Override
		public String getlibelle() {
			// TODO Auto-generated method stub
			return wAFAIMA_User.getLibelle();
		}
	public Long getiduser() {
		// TODO Auto-generated method stub
		return wAFAIMA_User.getId();
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
