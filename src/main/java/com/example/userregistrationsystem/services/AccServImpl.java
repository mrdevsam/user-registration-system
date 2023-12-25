package com.example.userregistrationsystem.services;

import com.example.userregistrationsystem.model.*;
import com.example.userregistrationsystem.repositories.*;
import java.util.List;

public class AccServImpl implements AccServ {

	private final AccountRepo arepo;

	

	@Override
	public void saveOrUpdateAcc(AccDTO accdto) {
		
	}

	@Override
	public Account findAccByEmail(String email) {
		return null;
	}

	@Override
	public List<AccDTO> findAllAccounts() {
		return null;
	}

	@Override
	public void deleteAccByEmail(String email) {
		
	}
}
