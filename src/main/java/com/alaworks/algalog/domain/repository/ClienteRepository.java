package com.alaworks.algalog.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alaworks.algalog.doamin.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByName(String name);
	List<Cliente> findByNameContaining(String name);
	Optional<Cliente> findByMail(String mail);
}
