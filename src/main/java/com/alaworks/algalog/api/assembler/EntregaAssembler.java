package com.alaworks.algalog.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alaworks.algalog.api.model.EntregaDTO;
import com.alaworks.algalog.api.model.input.EntregaInput;
import com.alaworks.algalog.doamin.model.Entrega;

@Component
public class EntregaAssembler {

	@Autowired
	private ModelMapper modelMapper;
	
	public EntregaDTO toModel(Entrega entrega) {
		
		return modelMapper.map(entrega, EntregaDTO.class);
	}
	
	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
		return entregas.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
	
}
