package com.algaworks.osworks.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.algaworks.osworks.domain.ValidationGroups;

//@Entity= anotação para ligar a tabela do banco de dados.
@Entity
public class Cliente {
	
	//@Id = identificador da coluna id, @GeneratedValue(strategy = GenerationType.IDENTITY)= fala para o java que segue o tipo de implementaçao do BD
	@NotNull(groups = ValidationGroups.ClienteId.class)// anotaçao para informar que campos nao pode ser nulo.
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank // informa que entrada de dado não pode ser null ou somente com espaço.https://projects.eclipse.org/projects/ee4j.bean-validation
	@Size(max= 60) //informa  o tamanho maximo da entrada.https://projects.eclipse.org/projects/ee4j.bean-validation
	private String nome;
	
	@NotBlank // informa que entrada de dado não pode ser null ou somente com espaço.https://projects.eclipse.org/projects/ee4j.bean-validation
	@Email // informa que entrada tem que ser email.https://projects.eclipse.org/projects/ee4j.bean-validation
	@Size(max= 255) //informa  o tamanho maximo da entrada.https://projects.eclipse.org/projects/ee4j.bean-validation
	private String email;
	
	@Column(name = "telefone")
	@Size(max= 20) //informa  o tamanho maximo da entrada.https://projects.eclipse.org/projects/ee4j.bean-validation
	private String telefone;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	// sempre gerar hashCode e equals automatico pelo Source.
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
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
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
