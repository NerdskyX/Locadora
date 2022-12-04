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
		Cliente Cliente = null;
		String sql = "select * from Cliente where id = ?";

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
				Cliente = new Cliente();

				// atribui campo para atributo
				Cliente.setId(rset.getLong("id"));
				Cliente.setNome(rset.getString("nome"));
				Cliente.setEmail(rset.getString("email"));
				Cliente.setTelefone(rset.getString("telefone"));
				Cliente.setData_cadastro(rset.getString("data_cadastro"));
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
		return Cliente;
	}

	@Override
	public List<Cliente> getAll() {
		List<Cliente> Clientes = new ArrayList<Cliente>();

		String sql = "select * from Cliente";

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
				Cliente Cliente = new Cliente();

				// atribui campo para atributo
				Cliente.setId(rset.getLong("id"));
				Cliente.setNome(rset.getString("nome"));
				Cliente.setEmail(rset.getString("email"));
				Cliente.setTelefone(rset.getString("telefone"));
				Cliente.setData_cadastro(rset.getString("data_cadastro"));

				Clientes.add(Cliente);
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
		return Clientes;
	}

	@Override
	public int save(Cliente Cliente) {
		String sql = "insert into Cliente (nome, email, telefone, data_cadastro )" + " values (?, ?, ?. ?)";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, Cliente.getNome());
			stm.setString(2, Cliente.getEmail());
			stm.setString(3, Cliente.getTelefone());
			stm.setString(4, Cliente.getData_cadastro());

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
	public boolean update(Cliente Cliente, String[] params) {
		String sql = "update Cliente set nome = ?, email = ?, telefone = ?, data_cadastro = ? where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, Cliente.getNome());
			stm.setString(2, Cliente.getEmail());
			stm.setString(3, Cliente.getTelefone());
			stm.setString(4, Cliente.getData_cadastro());
			stm.setLong(5, Cliente.getId());

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
	public boolean delete(Cliente Cliente) {
		String sql = "delete from Cliente where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, Cliente.getId());
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

