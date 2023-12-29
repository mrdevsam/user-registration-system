package com.example.userregistrationsystem.services;

import com.example.userregistrationsystem.model.*;
import com.example.userregistrationsystem.repositories.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccServImpl implements AccServ {

	private final AccountRepo arepo;
	private final RoleRepo rrepo;
	private final PasswordEncoder encoder;

	public AccServImpl(AccountRepo arepo, RoleRepo rrepo, PasswordEncoder encoder) {
		this.arepo = arepo;
		this.rrepo = rrepo;
		this.encoder = encoder;
	}

	@Override
	public void saveOrUpdateAcc(AccDTO accdto) {
		var new_acc = new Account();
		new_acc.setUserFirstName(accdto.getUserFirstName());
		new_acc.setUserLastName(accdto.getUserLastName());
		new_acc.setUserEmail(accdto.getUserEmail());
		// new_acc.setUserPassword(accdto.getUserPassword());
		new_acc.setUserPassword(encoder.encode(accdto.getUserPassword()));

		Role role = new Role();
		role = checkForRole(accdto.isAdmin());
		
		new_acc.setRoles(List.of(role));
		arepo.save(new_acc);
	}

	@Override
	public Account findAccByEmail(String email) {
		return arepo.findByUserEmail(email);
		
	}

	@Override
	public List<AccDTO> findAllAccounts() {
		List<Account> accounts = arepo.findAll();
		return accounts.stream().map(
			acc -> convertEntityToDto(acc)
		)
		.collect(Collectors.toList());
	}

	@Override
	public void deleteAccByEmail(String email) {
		arepo.deleteByUserEmail(email);
	}

	private Role checkForRole(boolean bl) {
		if(bl == true) {
			Role arl = rrepo.findByName("ROLE_ADMIN");

			if(arl == null) {
				Role brl = new Role();
				brl.setName("ROLE_ADMIN");
				return rrepo.save(brl);
			} else {
				return arl;
			}
			
		} else {
			Role url = rrepo.findByName("ROLE_USER");

			if(url == null) {
				Role vrl = new Role();
				vrl.setName("ROLE_USER");
				return rrepo.save(vrl);
			} else {
				return url;
			}
		}
	}

	private AccDTO convertEntityToDto(Account acc) {
		var accdto = new AccDTO();
		accdto.setUserFirstName(acc.getUserFirstName());
		accdto.setUserLastName(acc.getUserLastName());
		accdto.setUserEmail(acc.getUserEmail());
		return accdto;
	}

}
