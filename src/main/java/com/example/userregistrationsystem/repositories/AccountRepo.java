package com.example.userregistrationsystem.repositories;

import com.example.userregistrationsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepo extends JpaRepository<Account, Long> {
	Account findByUserEmail(String userEmail);
	void deleteByUserEmail(String userEmail);
}
