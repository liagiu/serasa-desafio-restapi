package com.serasa.desafiorestapi.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.serasa.desafiorestapi.domain.Cliente;
import com.serasa.desafiorestapi.exception.ClienteException;
import com.serasa.desafiorestapi.service.ClienteService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	@ApiOperation(value = "Listagem de todos os clientes", notes = "Listagem de clientes")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Clientes listados com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<List<Cliente>> listar() {
		List<Cliente> clientes = clienteService.listar();
		return ResponseEntity.ok(clientes);
	}
	
	@GetMapping("{id}")
	@ApiOperation(value = "Buscar cliente por id", notes = "Buscar Clientes")
	@ApiResponses(value = {
	@ApiResponse(code = 200, message = "Cliente listado por id com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
		Cliente cliente = clienteService.buscarPorId(id);
		return ResponseEntity.ok(cliente);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@ApiOperation(value = "Cadastrar um cliente", notes = "Cadastrar cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Cliente cadastrado com sucesso"),
			@ApiResponse (code = 401, message = "Erro de autenticação"),
			@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
			@ApiResponse (code = 404, message = "Recurso indisponível"),
			@ApiResponse (code = 500, message = "Erro interno no servidor"),
			@ApiResponse (code = 505, message = "Ocorreu uma exceção")
			}
			)
	public ResponseEntity<?> inserir(@Valid @RequestBody Cliente cliente) throws ClienteException {
		Cliente novoCliente;
		
		try {
			novoCliente = clienteService.inserir(cliente);
		} catch (ClienteException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoCliente.getId()).toUri();
		return ResponseEntity.created(uri).body(novoCliente);
	}
	
	@PutMapping("{id}")
	@ApiOperation(value = "Atualizar um cliente por id", notes = "Atualizar cliente")
	@ApiResponses(value = {
	@ApiResponse(code = 201, message = "Cliente atualizado com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 404, message = "Recurso indisponível"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<Cliente> atualizar(@PathVariable Long id, @Valid @RequestBody Cliente cliente) throws ClienteException {
		if (clienteService.atualizar(id, cliente) == null) {
				return ResponseEntity.notFound().build();
		}
	
		return ResponseEntity.ok(clienteService.atualizar(id, cliente));
	}

	@DeleteMapping("{id}")
	@ApiOperation(value = "Deletar um cliente", notes = "Deletar cliente")
	@ApiResponses(value = {
	@ApiResponse(code = 204, message = "Cliente deletado com sucesso"),
	@ApiResponse (code = 401, message = "Erro de autenticação"),
	@ApiResponse (code = 403, message = "Você não tem permissão para acessar o recurso"),
	@ApiResponse (code = 500, message = "Erro interno no servidor"),
	@ApiResponse (code = 505, message = "Ocorreu uma exceção")
	}
	)
	public ResponseEntity<Cliente> deletar(@PathVariable Long id) {
		if (clienteService.deletar(id) == false) {
			return ResponseEntity.notFound().build();
		}
		clienteService.deletar(id);
		return ResponseEntity.noContent().build();
	}
}
