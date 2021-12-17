package com.serasa.desafiorestapi.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor
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
	
	@Size(max = 11, message = "Número de caracteres excedido")
	@ApiModelProperty(value = "Define o telefone de um cliente ")
	private String telefone;

	public Cliente(Long id, String nomeCompleto, LocalDate dataNascimento, String telefone) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
		this.telefone = telefone;
	}
}
