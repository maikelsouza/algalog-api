package com.algaworks.algalog.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.api.assembler.EntregaAssembler;
import com.algaworks.algalog.api.model.EntregaModel;
import com.algaworks.algalog.api.model.input.EntregaInputModel;
import com.algaworks.algalog.domain.model.Entrega;
import com.algaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {
	
	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@Autowired
	private FinalizacaoEntregaService finalizacaoEntregaService;

	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaModel solicitar(@Valid @RequestBody EntregaInputModel entregaInputModel) {
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(entregaAssembler.toEntity(entregaInputModel));
		return entregaAssembler.toModel(entregaSolicitada);
	}
	
	@GetMapping	
	public List<EntregaModel> listar() {
		return entregaAssembler.toCollectionModel(solicitacaoEntregaService.buscarTodas());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaModel> buscar(@PathVariable Long entregaId) {
		return solicitacaoEntregaService.buscarPeloId(entregaId).map(entrega -> {
			EntregaModel entregaModel = entregaAssembler.toModel(entrega);
			return ResponseEntity.ok(entregaModel);
		}).orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar (@PathVariable Long entregaId) {
		finalizacaoEntregaService.finalizar(entregaId);		
	}

}
