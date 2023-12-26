package com.example.userregistrationsystem.services;

import com.example.userregistrationsystem.model.*;
import com.example.userregistrationsystem.repositories.*;
import java.util.List;
import java.util.stream.Collectors;

public class AccServImpl implements AccServ {

	private final AccountRepo arepo;
	private final RoleRepo rrepo;

	public AccServImpl(AccountRepo arepo, RoleRepo rrepo) {
		this.arepo = arepo;
		this.rrepo = rrepo;
	}

	@Override
	public void saveOrUpdateAcc(AccDTO accdto) {
		var new_acc = new Account();
		new_acc.setUserFirstName(accdto.getUserFirstName());
		new_acc.setUserLastName(accdto.getUserLastName());
		new_acc.setUserEmail(accdto.getUserEmail());
		new_acc.setUserPassword(accdto.getUserPassword());

		Role role = new Role();
		role = checkForRole(accdto.isAdmin());
		
		new_acc.setRoles(List.of(role));
		arepo.save(new_acc);
	}

	@Override
	public Account findAccByEmail(String email) {
		return arepo.findByUserEmail(email).get();
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
			Role arl = rrepo.findByName("ROLE_ADMIN").get();

			if(arl == null) {
				Role brl = new Role();
				brl.setName("ROLE_ADMIN");
				return rrepo.save(brl);
			} else {
				return arl;
			}
			
		} else {
			Role url = rrepo.findByName("ROLE_USER").get();

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
