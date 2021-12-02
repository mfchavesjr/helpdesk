package com.mfchaves.helpdesk.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfchaves.helpdesk.domain.Cliente;
import com.mfchaves.helpdesk.domain.Pessoa;
import com.mfchaves.helpdesk.domain.dto.ClienteDto;
import com.mfchaves.helpdesk.exceptions.DataIntegrityViolationException;
import com.mfchaves.helpdesk.exceptions.ObjectNotFoundException;
import com.mfchaves.helpdesk.repository.ClienteRepository;
import com.mfchaves.helpdesk.repository.PessoaRepository;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> optional = clienteRepository.findById(id);
		return optional.orElseThrow(() -> new ObjectNotFoundException("Id inexistente: " + id));
	}

	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}

	public Cliente create(@Valid ClienteDto clienteDto) {
		clienteDto.setId(null);
		validaPorCpfEEmail(clienteDto);
		Cliente cliente = new Cliente(clienteDto);
		return clienteRepository.save(cliente);
	}

	public Cliente update(Integer id, @Valid ClienteDto clienteDto) {
		clienteDto.setId(id);
		Cliente clienteSaved = findById(id);
		validaPorCpfEEmail(clienteDto);
		clienteSaved = new Cliente(clienteDto);
		return clienteRepository.save(clienteSaved);

	}

	public void delete(Integer id) {
		Cliente clienteSaved = findById(id);
		if (clienteSaved.getChamados().size() > 0) {
			throw new DataIntegrityViolationException(
					"O cliente possui ordens de serviços, " + "não pode ser deletado!");
		}
		clienteRepository.delete(clienteSaved);

	}

	private void validaPorCpfEEmail(ClienteDto clienteDto) {
		Optional<Pessoa> optionalPessoa = pessoaRepository.findByCpf(clienteDto.getCpf());
		if (optionalPessoa.isPresent() && optionalPessoa.get().getId() != clienteDto.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}

		optionalPessoa = pessoaRepository.findByEmail(clienteDto.getEmail());
		if (optionalPessoa.isPresent() && optionalPessoa.get().getId() != clienteDto.getId()) {
			throw new DataIntegrityViolationException("EMAIL já cadastrado no sistema!");
		}
	}

}
