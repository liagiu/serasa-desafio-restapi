package com.serasa.desafiorestapi.domain;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.serasa.desafiorestapi.repository.ClienteRepository;

@SpringBootTest
class ClienteTest {
	
	@Autowired
    private ClienteRepository clienteRepository;

	@Test
	void testarCriacaoCliente() {
		Cliente cliente = new Cliente(1L, "João da Silva", LocalDate.of(1980, 01, 01), "24123456789");
		
		clienteRepository.save(cliente);
		assertNotNull(clienteRepository.findById(cliente.getId()));
	}

}
