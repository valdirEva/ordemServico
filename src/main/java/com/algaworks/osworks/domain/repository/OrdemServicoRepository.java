package com.algaworks.osworks.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.osworks.domain.model.OrdemServico;

@Repository//anotacao para indicar ao spring que é um repositorio e assim salvara no banco de dados.
public interface OrdemServicoRepository  extends JpaRepository<OrdemServico, Long>{

}//normalmente não é nescessario criar funcoes dentro desta interface pois se estende a classe JpaRepository e herda varias funcoes da mesma.
