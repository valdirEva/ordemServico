package com.algaworks.osworks.domain.model;

import java.math.BigDecimal;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import com.algaworks.osworks.api.model.Comentario;
import com.algaworks.osworks.domain.ValidationGroups;
import com.algaworks.osworks.domain.exception.NegocioException;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;


@Entity
public class OrdemServico {
	
	//@Id = identificador da coluna id, @GeneratedValue(strategy = GenerationType.IDENTITY)= fala para o java que segue o tipo de implementaçao do BD
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid // utilizando anotacao para servir de cascateamento de validação em Cliente.clienteId.
	@ConvertGroup(from = Default.class, to = ValidationGroups.ClienteId.class)// validacao de grupo em cliente chamndo a classe Default e convertendo na interface ValidationGroups.ClienteId.class .
	@NotNull //anotacao para informar que campo não pode ser nulo.
	@ManyToOne //associaçao de muitas ordens de serviço para um cliente.//@joinColumn(name = "id_cliente")// indica com qual coluna se relaciona. se não especificar fica por padrao clienteId.
	private Cliente cliente;
	
	@NotBlank // anotacao para informa que campo que não pode estar em branco
	private String descricao;
	
	@NotNull //anotacao para informar que campo não pode ser nulo.
	private BigDecimal preco;
	
	@JsonProperty(access = Access.READ_ONLY)//anotação para que atributo seja apenas leitura e o sistema ira preencher.
	@Enumerated(EnumType.STRING)//o padrao é ORDINAl mas estamos utilizando String para que a coluna status armazene a String(ABERTA,FINALIZADA OU CANCELADA) DO CONTRARIO SALVARIA O NUMERO DA ENUMERAÇÃO.
	private StatusOrdemServico status;
	
	@JsonProperty(access = Access.READ_ONLY)//anotação para que atributo seja apenas leitura e o sistema ira preencher.
	private OffsetDateTime dataAbertura;
	
	@JsonProperty(access = Access.READ_ONLY)//anotação para que atributo seja apenas leitura e o sistema ira preencher.
	private OffsetDateTime dataFinalizacao;
	
	@OneToMany(mappedBy= "ordemServico")
	private List<Comentario> comentarios = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public BigDecimal getPreco() {
		return preco;
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public StatusOrdemServico getStatus() {
		return status;
	}
	public void setStatus(StatusOrdemServico status) {
		this.status = status;
	}
	public OffsetDateTime getDataAbertura() {
		return dataAbertura;
	}
	public void setDataAbertura(OffsetDateTime localDateTime) {
		this.dataAbertura = localDateTime;
	}
	public OffsetDateTime getDataFinalizacao() {
		return dataFinalizacao;
	}
	public void setDataFinalizacao(OffsetDateTime dataFinalizacao) {
		this.dataFinalizacao = dataFinalizacao;
	}
	
	
	
	
	public List<Comentario> getComentarios() {
		return comentarios;
	}
	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
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
		OrdemServico other = (OrdemServico) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	public void finalizar() {
		if (!StatusOrdemServico.ABERTA.equals(getStatus())) {
		throw new NegocioException("Ordem de servico nao pode ser finalizada");
		
	}
	
	setStatus(StatusOrdemServico.FINALIZADA);
	setDataFinalizacao(OffsetDateTime.now());
	}
	

}
