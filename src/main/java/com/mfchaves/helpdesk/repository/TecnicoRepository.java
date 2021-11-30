package com.mfchaves.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfchaves.helpdesk.domain.Tecnico;

public interface TecnicoRepository extends JpaRepository<Tecnico, Integer> {

}
