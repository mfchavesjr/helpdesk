package com.mfchaves.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfchaves.helpdesk.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

}
