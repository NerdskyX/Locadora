package com.ifsc.tds.Vitor.Cassio.Igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.Vitor.Cassio.Igor.entity.Cliente;

public class ClienteDAO implements DAO<Cliente> {

	@Override
	public Cliente get(Long id) {
		Cliente cliente = null;
		String sql = "select * from cliente where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		// Criar uma classe que guarde o retorno da opera��o
		ResultSet rset = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id.intValue());
			rset = stm.executeQuery();

			while (rset.next()) {
				cliente = new Cliente();

				// atribui campo para atributo
				cliente.setId(rset.getLong("id"));
				cliente.setNome(rset.getString("nome"));
				cliente.setEmail(rset.getString("email"));
				cliente.setTelefone(rset.getString("telefone"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return cliente;
	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "select * from cliente";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		// Criar uma classe que guarde o retorno da opera��o
		ResultSet rset = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			rset = stm.executeQuery();

			while (rset.next()) {
				Cliente cliente = new Cliente();

				// atribui campo para atributo
				cliente.setId(rset.getLong("id"));
				cliente.setNome(rset.getString("nome"));
				cliente.setEmail(rset.getString("email"));
				cliente.setTelefone(rset.getString("telefone"));

				clientes.add(cliente);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return clientes;
	}

	@Override
	public int save(Cliente cliente) {
		String sql = "insert into cliente (nome, email, telefone)" + " values (?, ?, ?)";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getTelefone());
			
			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return 1;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return 0;
	}

	@Override
	public boolean update(Cliente cliente, String[] params) {
		String sql = "update Cliente set nome = ?, email = ?, telefone = ? where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, cliente.getNome());
			stm.setString(2, cliente.getEmail());
			stm.setString(3, cliente.getTelefone());
	
			stm.setLong(5, cliente.getId());

			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean delete(Cliente cliente) {
		String sql = "delete from cliente where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, cliente.getId());
			stm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stm != null) {
					stm.close();
				}

				if (conexao != null) {
					conexao.close();
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}

