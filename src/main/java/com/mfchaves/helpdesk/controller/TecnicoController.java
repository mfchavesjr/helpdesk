package com.mfchaves.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfchaves.helpdesk.domain.Tecnico;
import com.mfchaves.helpdesk.domain.dto.TecnicoDto;
import com.mfchaves.helpdesk.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnico")
public class TecnicoController {
	
	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDto(tecnico));
	}

}