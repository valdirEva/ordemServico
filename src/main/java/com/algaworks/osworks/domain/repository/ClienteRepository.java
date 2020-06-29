package com.algaworks.osworks.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.Cliente;

//@Repository= componente do Spring, interface que extende a JpaRepository 
//e herda todas as implemantaçoes de JPA facilitando a interaçao com o BD.
@Repository
public interface ClienteRepository extends JpaRepository <Cliente, Long>{
	
	//faz a busca no BD por nome.
	List<Cliente> findBynome(String nome);
	
	//faz a busca no BD pela letra que tenha no nome.
		List<Cliente> findBynomeContaining(String nome);
		
	Cliente findByEmail(String email);
}

