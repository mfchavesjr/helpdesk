package com.mfchaves.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfchaves.helpdesk.domain.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {

}
