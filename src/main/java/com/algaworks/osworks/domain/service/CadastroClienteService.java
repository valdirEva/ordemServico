package com.algaworks.osworks.domain.service;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



import com.algaworks.osworks.domain.exception.NegocioException;
import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;



@Service// componente do spring para colocar nossos serviços.
public class CadastroClienteService {
	
	@Autowired// injeta a interface ClienteRepository.
	private ClienteRepository clienteRepository;
	
	
	public Cliente salvar(Cliente cliente) {
		Cliente clienteExistente = clienteRepository.findByEmail(cliente.getEmail());
		
		if (clienteExistente != null && !clienteExistente.equals(cliente)) {
			throw new NegocioException("Já existe um cliente cadastrado com este email.");
		}
		
		return clienteRepository.save(cliente);//salva ou edita cliente.
	}
	
	public void excluir(long clienteId) {
		clienteRepository.deleteById(clienteId);//exclui cliente.
	}

}
