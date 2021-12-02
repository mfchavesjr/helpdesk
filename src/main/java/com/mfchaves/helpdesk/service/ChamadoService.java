package com.mfchaves.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfchaves.helpdesk.domain.Chamado;
import com.mfchaves.helpdesk.domain.Cliente;
import com.mfchaves.helpdesk.domain.Tecnico;
import com.mfchaves.helpdesk.domain.dto.ChamadoDto;
import com.mfchaves.helpdesk.domain.enums.Prioridade;
import com.mfchaves.helpdesk.domain.enums.Status;
import com.mfchaves.helpdesk.exceptions.ObjectNotFoundException;
import com.mfchaves.helpdesk.repository.ChamadoRepository;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository chamadoRepository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;

	public Chamado findById(Integer id) {
		Optional<Chamado> chamado = chamadoRepository.findById(id);
		return chamado.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! ID: " + id));
	}

	public List<Chamado> listAll() {
		return chamadoRepository.findAll();
	}

	public Chamado create(@Valid ChamadoDto chamadoDto) {
		return chamadoRepository.save(newChamado(chamadoDto));
	}

	public Chamado update(Integer id, @Valid ChamadoDto chamadoDto) {
		chamadoDto.setId(id);
		Chamado chamadoSaved = findById(id);
		chamadoSaved = newChamado(chamadoDto);
		return chamadoRepository.save(chamadoSaved);
	}

	private Chamado newChamado(ChamadoDto chamadoDto) {
		Tecnico tecnico = tecnicoService.findById(chamadoDto.getTecnico());
		Cliente cliente = clienteService.findById(chamadoDto.getCliente());

		Chamado chamado = new Chamado();

		if (chamadoDto.getId() != null) {
			chamado.setId(chamadoDto.getId());
		}
		chamado.setCliente(cliente);
		chamado.setTecnico(tecnico);
		chamado.setPrioridade(Prioridade.toEnum(chamadoDto.getPrioridade()));
		chamado.setStatus(Status.toEnum(chamadoDto.getStatus()));
		chamado.setTitulo(chamadoDto.getTitulo());
		chamado.setObservacoes(chamadoDto.getObservacoes());

		return chamado;

	}

}
