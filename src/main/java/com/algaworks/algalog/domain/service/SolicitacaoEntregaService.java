package com.algaworks.algalog.domain.service;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.domain.model.Cliente;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.model.StatusEntrega;
import com.algaworks.algalog.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private CatalagoClienteService catalagoClienteService;
	
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = catalagoClienteService.buscarPeloId(entrega.getCliente().getId());
		entrega.setCliente(cliente);
		entrega.setDataPedido(OffsetDateTime.now());
		entrega.setStatus(StatusEntrega.PENDENTE);
		return entregaRepository.save(entrega);
	}
	
	public List<Entrega> buscarTodas(){
		return entregaRepository.findAll();
	}
	
	public Optional<Entrega> buscarPeloId(Long entregaId){
		return entregaRepository.findById(entregaId);
	}

}
