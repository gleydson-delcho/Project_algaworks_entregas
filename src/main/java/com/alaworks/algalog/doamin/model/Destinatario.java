package com.alaworks.algalog.doamin.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

@Embeddable
public class Destinatario {

	@NotBlank
	@Column(name="destinatario_nome")
	private String nome;

	@NotBlank
	@Column(name="destinatario_Logradouro")
	private String Logradouro;

	@NotBlank
	@Column(name="destinatario_numero")
	private String numero;
	
	@Column(name="destinatario_complemento")
	private String complemento;

	@NotBlank
	@Column(name="destinatario_bairro")
	private String bairro;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getLogradouro() {
		return Logradouro;
	}
	public void setLogradouro(String logradouro) {
		Logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
}
