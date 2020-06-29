package com.algaworks.osworks.api.controller;


import java.util.List;
import java.util.Optional;


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

import com.algaworks.osworks.domain.model.Cliente;
import com.algaworks.osworks.domain.repository.ClienteRepository;
import com.algaworks.osworks.domain.service.CadastroClienteService;

@RestController//anotacao para indicar ao spring que a classe é controller.
@RequestMapping("/clientes")//indica onde estao todas requisições.
public class ClienteController {
	
	
	
	//@Autowired = cria uma estancia de  ClienteRepository.
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired //injeta classe CadastroClienteService onde contem inclusao, exclusao e atualização.
	private CadastroClienteService cadastroCliente;
	
	//@GetMapping("/clientes") = mapeia o caminho da requisição.
	@GetMapping
	public List<Cliente> listar() {
		
		return clienteRepository.findAll();//retorna uma lista de todos clientes.
		
		//return clienteRepository.findBynome("valdir");//retorna cliente por nome.
		//return clienteRepository.findBynomeContaining("pri");//retorna cliente por letra ou parte do nome.
		
		}
	
	//@GetMapping("/clientes/{clienteId}") = mapeia o caminho da requisição.
	// retorna cliente pelo Id e caso não encontre retorna 404.
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscar(@PathVariable Long clienteId) {
		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
		
		if (cliente.isPresent()) {
			return ResponseEntity.ok(cliente.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping //método para adicionar dados noBD tabela cliente.
	@ResponseStatus(HttpStatus.CREATED)// retorna o valor correto quando adicionar sem erros ao BD.@valid para acionar o validation.
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		return cadastroCliente.salvar(cliente); //injeta classe CadastroClienteService onde contem inclusao, exclusao e atualização.
		
	}
	
	@PutMapping("/{clienteId}")//metodo para atualizar dados na tabela cliente pelo ID.@valid para acionar o validation.
	public ResponseEntity<Cliente> atualizar (@Valid @PathVariable Long clienteId, 
			 @RequestBody Cliente cliente){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cliente.setId(clienteId);
		cliente= cadastroCliente.salvar(cliente);
		
		return ResponseEntity.ok(cliente);
	}
	
	@DeleteMapping("/{clienteId}")//metodo para deletar dados da tabela cliente no BD.
	public ResponseEntity<Void> removefr(@PathVariable Long clienteId){
		if(!clienteRepository.existsById(clienteId)) {
			return ResponseEntity.notFound().build();
		}
		cadastroCliente.excluir(clienteId);
		return ResponseEntity.noContent().build();
	}

}
