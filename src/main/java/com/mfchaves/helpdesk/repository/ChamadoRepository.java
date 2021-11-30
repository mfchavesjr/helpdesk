package com.mfchaves.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mfchaves.helpdesk.domain.Chamado;

public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

}
