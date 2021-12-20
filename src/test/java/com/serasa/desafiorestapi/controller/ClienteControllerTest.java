package com.serasa.desafiorestapi.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serasa.desafiorestapi.domain.Cliente;
import com.serasa.desafiorestapi.exception.ClienteException;
import com.serasa.desafiorestapi.repository.ClienteRepository;
import com.serasa.desafiorestapi.service.ClienteService;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {
	
	@Autowired
    private MockMvc mockMvc;
	
	@Autowired
    private ObjectMapper objectMapper;
	
	@Autowired
    private ClienteRepository clienteRepository;
	
	Cliente cliente1 = new Cliente(1L, "João da Silva", LocalDate.of(1980, 01, 01), "24123456789");
	Cliente cliente2 = new Cliente(2L, "Maria da Silva", LocalDate.of(1999, 12, 12), "24987654321");

	@Test
	void testarInserir() throws JsonProcessingException, Exception {
		mockMvc.perform(post("/clientes")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(cliente1)))
		        .andExpect(status().is2xxSuccessful());
		
		mockMvc.perform(post("/clientes")
		        .contentType("application/json")
		        .content(objectMapper.writeValueAsString(cliente2)))
		        .andExpect(status().is2xxSuccessful());
	}
	
	@Test
	void testarFindAll() {
		List<Cliente> clientes = clienteRepository.findAll();
		assertThat(clientes.size()).isEqualTo(2);
		assertThat(clientes.get(0).getTelefone()).isEqualTo(cliente1.getTelefone());
		assertThat(clientes.get(1).getTelefone()).isEqualTo(cliente2.getTelefone());
	}
	
	@Test
	public void testarGetById() throws Exception {
		List<Cliente> clientes = clienteRepository.findAll();
		Long clienteId = clientes.get(0).getId();
	    mockMvc.perform(
	            get("/clientes/" + clienteId)
	            .contentType(MediaType.APPLICATION_JSON))
	            .andExpect(status().isOk())
	            .andDo(print());
	}
}
