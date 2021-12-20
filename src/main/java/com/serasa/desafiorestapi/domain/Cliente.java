package com.serasa.desafiorestapi.domain;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Cliente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_cliente")
	@ApiModelProperty(value = "Identificador único do cliente")
	private Long id;

	@NotBlank(message = "Campo nome não pode ser vazio")
	@Column(name = "nome_completo")
	@ApiModelProperty(value = "Define o nome completo de um cliente ")
	private String nomeCompleto;
	
	@Past(message = "Data inválida!")
	@Column(name = "data_nascimento")
	@ApiModelProperty(value = "Define a data de nascimento de um cliente ")
	private LocalDate dataNascimento;
	
	@NotBlank(message = "Campo telefone não pode ser vazio")
	@Size(max = 11, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o telefone de um cliente ")
	private String telefone;

	public Cliente(Long id, String nomeCompleto, LocalDate dataNascimento, String telefone) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
	}
	
	public Cliente() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		return Objects.equals(id, other.id);
	}
}
