package com.mfchaves.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mfchaves.helpdesk.domain.Pessoa;
import com.mfchaves.helpdesk.domain.Tecnico;
import com.mfchaves.helpdesk.domain.dto.TecnicoDto;
import com.mfchaves.helpdesk.exceptions.DataIntegrityViolationException;
import com.mfchaves.helpdesk.exceptions.ObjectNotFoundException;
import com.mfchaves.helpdesk.repository.PessoaRepository;
import com.mfchaves.helpdesk.repository.TecnicoRepository;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Tecnico findById(Integer id) {
		Optional<Tecnico> optional = tecnicoRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Id inexistente: " + id));
	}

	public List<Tecnico> findAll() {
		return tecnicoRepository.findAll();
	}

	public Tecnico create(TecnicoDto tecnicoDto) {
		tecnicoDto.setId(null);
		validaPorCpfEEmail(tecnicoDto);
		Tecnico tecnico = new Tecnico(tecnicoDto);
		return tecnicoRepository.save(tecnico);
	}

	public Tecnico update(Integer id, @Valid TecnicoDto tecnicoDto) {
		tecnicoDto.setId(id);
		Tecnico tecnicoSaved = findById(id);
		validaPorCpfEEmail(tecnicoDto);
		tecnicoSaved = new Tecnico(tecnicoDto);
		return tecnicoRepository.save(tecnicoSaved);

	}

	private void validaPorCpfEEmail(TecnicoDto tecnicoDto) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findByCpf(tecnicoDto.getCpf());
		if (optionalPessoa.isPresent() && optionalPessoa.get().getId() != tecnicoDto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		optionalPessoa = pessoaRepository.findByEmail(tecnicoDto.getEmail());
		if (optionalPessoa.isPresent() && optionalPessoa.get().getId() != tecnicoDto.getId()) {
			throw new DataIntegrityViolationException("EMAIL já cadastrado no sistema!");
		}
	}

}
