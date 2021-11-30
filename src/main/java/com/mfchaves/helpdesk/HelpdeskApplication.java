package com.mfchaves.helpdesk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mfchaves.helpdesk.repository.ChamadoRepository;
import com.mfchaves.helpdesk.repository.ClienteRepository;
import com.mfchaves.helpdesk.repository.TecnicoRepository;

@SpringBootApplication
public class HelpdeskApplication {

	@Autowired
	private TecnicoRepository tecnicoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private ChamadoRepository chamadoRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		Tecnico tec1 = new Tecnico(null, "Mauricio", "46887763737", "mauricio@mfchaves.com", "123");
//		tec1.addPerfil(Perfil.ADMIN);
//
//		Cliente cli1 = new Cliente(null, "Tonny", "17663395069", "tonny@mfchaves.com", "123");
//		cli1.addPerfil(Perfil.CLIENTE);
//
//		Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1,
//				cli1);
//		
//		tecnicoRepository.saveAll(Arrays.asList(tec1));
//		clienteRepository.saveAll(Arrays.asList(cli1));
//		chamadoRepository.saveAll(Arrays.asList(c1));
//
//	}

}
