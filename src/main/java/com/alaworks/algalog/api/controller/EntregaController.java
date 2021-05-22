package com.alaworks.algalog.api.controller;

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

import com.alaworks.algalog.api.assembler.EntregaAssembler;
import com.alaworks.algalog.api.model.EntregaDTO;
import com.alaworks.algalog.api.model.input.EntregaInput;
import com.alaworks.algalog.doamin.model.Entrega;
import com.alaworks.algalog.domain.repository.EntregaRepository;
import com.alaworks.algalog.domain.service.FinalizacaoEntregaService;
import com.alaworks.algalog.domain.service.SolicitacaoEntregaService;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;
	
	@Autowired
	private FinalizacaoEntregaService finalizacaoEntregaService;
	
	@Autowired
	private EntregaAssembler entregaAssembler;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);		
		Entrega entregaSolicitacao = solicitacaoEntregaService.solicitar(novaEntrega);
		
		return entregaAssembler.toModel(entregaSolicitacao);
	}
	
	@GetMapping
	public List<EntregaDTO> listar(){
		
		return entregaAssembler.toCollectionDTO(entregaRepository.findAll());
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable long entregaId){
	
		return entregaRepository.findById(entregaId)
				.map(entrega ->  ResponseEntity.ok(entregaAssembler.toModel(entrega)))
				.orElse(ResponseEntity.notFound().build());				
	}
	
	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizacao(@PathVariable Long entregaId) {
		
		finalizacaoEntregaService.finalizar(entregaId);
	}
}
