package com.example.userregistrationsystem.services;

import com.example.userregistrationsystem.model.*;
import com.example.userregistrationsystem.repositories.AccountRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsServ implements UserDetailsService {

	private AccountRepo arepo;

	public CustomUserDetailsServ(AccountRepo arepo) {
		this.arepo = arepo;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Account acc = arepo.findByUserEmail(email);

		if(acc != null) {
			return new User(acc.getUserEmail(), acc.getUserPassword(), mapeRolesToAuthorities(acc.getRoles()));
		} else {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
	}

	private Collection<? extends GrantedAuthority> mapeRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
