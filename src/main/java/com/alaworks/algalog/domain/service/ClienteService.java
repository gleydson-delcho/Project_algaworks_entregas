package com.alaworks.algalog.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alaworks.algalog.doamin.model.Cliente;
import com.alaworks.algalog.domain.exception.NegotialException;
import com.alaworks.algalog.domain.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;	

	public Cliente buscar(Long clienteId) {
		return clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegotialException("Cliente não encontrado!"));
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		
		boolean existsEmail = clienteRepository.findByMail(cliente.getMail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(existsEmail) {
			throw new NegotialException("Já existe cliente cadastrado com o e-mail informado!"); 
			
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clientId) {
		
		clienteRepository.deleteById(clientId);
	}
}
