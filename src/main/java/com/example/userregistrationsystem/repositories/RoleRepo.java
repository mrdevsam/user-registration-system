package com.example.userregistrationsystem.repositories;

import com.example.userregistrationsystem.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Long> {
	Role findByName(String name);
}
