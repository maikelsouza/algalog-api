package com.algaworks.algalog.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class EntregaInputModel {
	
	@Valid
	@NotNull
	private ClienteIdInputModel cliente;
	
	@Valid
	@NotNull
	private DestinatarioInputModel destinatario;
	
	@NotNull
	private BigDecimal taxa;

	public ClienteIdInputModel getCliente() {
		return cliente;
	}

	public void setCliente(ClienteIdInputModel cliente) {
		this.cliente = cliente;
	}

	public DestinatarioInputModel getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(DestinatarioInputModel destinatario) {
		this.destinatario = destinatario;
	}

	public BigDecimal getTaxa() {
		return taxa;
	}

	public void setTaxa(BigDecimal taxa) {
		this.taxa = taxa;
	}	

}
