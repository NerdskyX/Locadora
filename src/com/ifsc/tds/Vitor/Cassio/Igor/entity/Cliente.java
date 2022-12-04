package com.ifsc.tds.Vitor.Cassio.Igor.entity;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Cliente {
	private Long id;
	private String nome;
	private String email;
	private String telefone;
	private String data_cadastro;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	public String getData_cadastro() {
		return data_cadastro;
	}
	public void setData_cadastro(String data_cadastro) {
		DateFormat df = new SimpleDateFormat(data_cadastro);
		Date today = Calendar.getInstance().getTime();
		String dateToString = df.format(today);
		this.data_cadastro = dateToString;
	}
	
	@Override
	public String toString() {
		return this.nome;
	}

}
