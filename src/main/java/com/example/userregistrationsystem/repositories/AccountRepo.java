package com.example.userregistrationsystem.repositories;

import com.example.userregistrationsystem.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AccountRepo extends JpaRepository<Account, Long> {
	Optional<Account> findByUserEmail(String email);
}
