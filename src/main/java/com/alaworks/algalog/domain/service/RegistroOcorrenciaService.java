package com.alaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alaworks.algalog.doamin.model.Entrega;
import com.alaworks.algalog.doamin.model.Ocorrencia;

@Service
public class RegistroOcorrenciaService {

	@Autowired
	private BuscaEntregaService buscaEntregaService;
	
	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		
		Entrega entrega = buscaEntregaService.buscar(entregaId);
		
		return entrega.adicionarOcorrenciaDescricao(descricao);
	}
}
