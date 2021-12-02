package com.mfchaves.helpdesk.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
