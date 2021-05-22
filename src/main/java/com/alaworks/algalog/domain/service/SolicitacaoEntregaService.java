package com.alaworks.algalog.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alaworks.algalog.doamin.model.Cliente;
import com.alaworks.algalog.doamin.model.Entrega;
import com.alaworks.algalog.doamin.model.StatusEntrega;
import com.alaworks.algalog.domain.repository.EntregaRepository;

@Service
public class SolicitacaoEntregaService {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Transactional
	public Entrega solicitar(Entrega entrega) {
		
		Cliente cliente = clienteService.buscar(entrega.getCliente().getId());
		
		entrega.setCliente(cliente);
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido(OffsetDateTime.now());		
		
		return entregaRepository.save(entrega);
	}
}
