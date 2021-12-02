package com.mfchaves.helpdesk.controller;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mfchaves.helpdesk.domain.Chamado;
import com.mfchaves.helpdesk.domain.dto.ChamadoDto;
import com.mfchaves.helpdesk.service.ChamadoService;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoController {

	@Autowired
	private ChamadoService chamadoService;

	@GetMapping
	public ResponseEntity<List<ChamadoDto>> listAll() {
		List<Chamado> listChamados = chamadoService.listAll();
		List<ChamadoDto> listChamadosDto = listChamados.stream().map(obj -> new ChamadoDto(obj))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listChamadosDto);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<ChamadoDto> findById(@PathVariable Integer id) {
		Chamado chamado = chamadoService.findById(id);
		return ResponseEntity.ok().body(new ChamadoDto(chamado));
	}

	@PostMapping
	public ResponseEntity<ChamadoDto> create(@Valid @RequestBody ChamadoDto chamadoDto) {
		Chamado chamado = chamadoService.create(chamadoDto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(chamado.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<ChamadoDto> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDto chamadoDto) {
		Chamado chamado = chamadoService.update(id, chamadoDto);
		return ResponseEntity.ok().body(new ChamadoDto(chamado));
	}

}
