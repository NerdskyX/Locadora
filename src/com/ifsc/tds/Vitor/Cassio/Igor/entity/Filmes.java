package com.ifsc.tds.Vitor.Cassio.Igor.entity;

public class Filmes {
	private Long id;
	private String nome;
	private String data_lancamento;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getData_lancamento() {
		return data_lancamento;
	}
	public void setData_lancamento(String data_lancamento) {
		this.data_lancamento = data_lancamento;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}

}
