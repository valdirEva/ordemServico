package com.algaworks.osworks.domain.service;



import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.model.OrdemServico;
import com.algaworks.osworks.domain.model.StatusOrdemServico;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.repository.ComentarioRepository;
import com.algaworks.osworks.domain.repository.OrdemServicoRepository;

@Service// componente do spring para colocar nossos serviços.
public class GestaoOrdemServicoService {
	
	@Autowired// injeta a interface OrdemServicoRepository.
	private OrdemServicoRepository ordemServicoRepository;
	
	@Autowired//injeta a interface ClienteRepository
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ComentarioRepository comentarioRepository;
	
	public OrdemServico criar(OrdemServico ordemServico) {
		Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
				.orElseThrow(() -> new NegocioException("Cliente não encontrado"));//caso não encontre o cliente lancara a exception.
		
		ordemServico.setCliente(cliente);//insere o id do cliente na ordem de servico depois de verificar se o mesmo existe no BD.
		ordemServico.setStatus(StatusOrdemServico.ABERTA);//quando uma nova ordem de servico for criada o Status sempre sera ABERTA.
		ordemServico.setDataAbertura(OffsetDateTime.now());//quando uma nova ordem de servico for criada salvara com data e hora atual.
		
		
		return ordemServicoRepository.save(ordemServico);//chama a interface OrdemServicoRepository e salva no BD na tabela ordem de servico.
	}
	
	
	public void finalizar(Long ordemServicoId) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		ordemServico.finalizar();
		
		ordemServicoRepository.save(ordemServico);
		
	}


	private OrdemServico buscar(Long ordemServicoId) {
		return ordemServicoRepository.findById(ordemServicoId)
				.orElseThrow(() -> new EntidadeNaoEncontradaException("Ordem de servico não encontrada"));
	}
	
	
	
	public Comentario adicionarComentario(Long ordemServicoId, String descricao) {
		OrdemServico ordemServico = buscar(ordemServicoId);
		
		Comentario comentario = new Comentario();
		comentario.setDataEnvio(OffsetDateTime.now());
		comentario.setDescricao(descricao);
		comentario.setOrdemServico(ordemServico);
		
		return comentarioRepository.save(comentario);
		
	}
	

}
