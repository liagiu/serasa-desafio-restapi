package com.serasa.desafiorestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.serasa.desafiorestapi.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{
	public Cliente findByTelefone(String telefone);
}
