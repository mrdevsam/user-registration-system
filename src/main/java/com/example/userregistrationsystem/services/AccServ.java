package com.example.userregistrationsystem.services;

import com.example.userregistrationsystem.model.*;
import java.util.List;

public interface AccServ {
	void saveOrUpdateAcc(AccDTO accdto);
	Account findAccByEmail(String email);
	List<AccDTO> findAllAccounts();
}
