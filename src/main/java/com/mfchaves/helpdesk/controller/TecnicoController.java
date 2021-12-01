package com.mfchaves.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfchaves.helpdesk.domain.Tecnico;
import com.mfchaves.helpdesk.domain.dto.TecnicoDto;
import com.mfchaves.helpdesk.service.TecnicoService;

@RestController
@RequestMapping(value = "/tecnicos")
public class TecnicoController {

	@Autowired
	private TecnicoService tecnicoService;

	@GetMapping(value = "/{id}")
	public ResponseEntity<TecnicoDto> findById(@PathVariable Integer id) {
		Tecnico tecnico = tecnicoService.findById(id);
		return ResponseEntity.ok().body(new TecnicoDto(tecnico));
	}

	@GetMapping
	public ResponseEntity<List<TecnicoDto>> findAll() {
		List<Tecnico> list = tecnicoService.findAll();
		List<TecnicoDto> listDto = list.stream().map(obj -> new TecnicoDto(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@PostMapping
	public ResponseEntity<TecnicoDto> create(@RequestBody TecnicoDto tecnicoDto) {
		Tecnico tecnico = tecnicoService.create(tecnicoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(tecnico.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

}
