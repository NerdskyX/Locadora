package com.ifsc.tds.Vitor.Cassio.Igor.entity;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class Emprestimo {
	private Long id;
	private Date data_esmprestimo;
	private Date data_entrega;
	private String obs;
	private String valorEmprestimo;
	private Cliente cliente;
	private Filmes filmes;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Date getData_esmprestimo() {
		return data_esmprestimo;
	}
	public void setData_esmprestimo(Date data_esmprestimo) {
		this.data_esmprestimo = data_esmprestimo;
	}
	public Date getData_entrega() {
		return data_entrega;
	}
	public void setData_entrega(Date data_entrega) {
		this.data_entrega = data_entrega;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getValorEmprestimo() {
		return valorEmprestimo;
	}
	public void setValorEmprestimo(String valorEmprestimo) {
		this.valorEmprestimo = valorEmprestimo;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public Filmes getFilmes() {
		return filmes;
	}
	public void setFilmes(Filmes filmes) {
		this.filmes = filmes;
	}
	public String getAnoFormatado() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data_esmprestimo.toLocalDate().format(dataFormatada).toString();
	}
	public String getAnoFormatado2() {
		DateTimeFormatter dataFormatada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return this.data_entrega.toLocalDate().format(dataFormatada).toString();
	}
}
