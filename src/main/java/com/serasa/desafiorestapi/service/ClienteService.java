package com.serasa.desafiorestapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.serasa.desafiorestapi.domain.Cliente;
import com.serasa.desafiorestapi.exception.ClienteException;
import com.serasa.desafiorestapi.repository.ClienteRepository;

@Service
public class ClienteService {
	@Autowired
    private ClienteRepository clienteRepository;
	
	public List<Cliente> listar() {
        List<Cliente> clientes = clienteRepository.findAll();
        
        return clientes;
    }
	
	public Cliente buscarPorId(Long id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if(cliente.isPresent()) {
			return cliente.get();
		} else {
			return null;
		}
	}
	
	public Cliente inserir(Cliente cliente) throws ClienteException {
		Cliente clienteTelefone = clienteRepository.findByTelefone(cliente.getTelefone());
	
		if(clienteTelefone != null) {
			throw new ClienteException("Este telefone já está cadastrado!");
		}
		
		clienteRepository.save(cliente);
		return cliente;
	}
	
	public Cliente atualizar(Long id, Cliente cliente) throws ClienteException {
		if (!clienteRepository.existsById(id)) {
			return null;
		} else if (clienteRepository.findByTelefone(cliente.getTelefone()).getId() != id) {
			throw new ClienteException("Este telefone já está cadastrado!");
		}
		cliente.setId(id);
		clienteRepository.save(cliente);
		return cliente;
	}
	
	public Boolean deletar(Long id) {
		if (!clienteRepository.existsById(id)) {
			return false;
		}
		clienteRepository.deleteById(id);
		return true;
	}
}
