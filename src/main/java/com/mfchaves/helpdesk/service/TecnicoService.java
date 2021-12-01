package com.mfchaves.helpdesk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfchaves.helpdesk.domain.Tecnico;
import com.mfchaves.helpdesk.exceptions.ObjectNotFoundException;
import com.mfchaves.helpdesk.repository.TecnicoRepository;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository tecnicoRepository;
	
	public Tecnico findById(Integer id) {
		Optional<Tecnico> optional = tecnicoRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Id inexistente: " + id));
	}
}
