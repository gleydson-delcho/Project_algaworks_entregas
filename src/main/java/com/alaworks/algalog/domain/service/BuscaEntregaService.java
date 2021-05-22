package com.alaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alaworks.algalog.doamin.model.Entrega;
import com.alaworks.algalog.domain.exception.EntityNotFoundException;
import com.alaworks.algalog.domain.repository.EntregaRepository;

@Service
public class BuscaEntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	public Entrega buscar(Long entregaId) {
		return entregaRepository.findById(entregaId)
				.orElseThrow(() -> new EntityNotFoundException("Entrega não encontrada!"));
	}
}
