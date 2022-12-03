package com.ifsc.tds.Vitor.Cassio.Igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.Vitor.Cassio.Igor.entity.Emprestimo;

public class EmprestimoDAO implements DAO<Emprestimo> {

	private ClienteDAO clienteDAO;

	private FilmesDAO filmesDAO;

	public EmprestimoDAO() {
		this.clienteDAO = new ClienteDAO();
		this.filmesDAO = new FilmesDAO();
	}

	@Override
	public Object get(Long id) {
		Emprestimo Emprestimo = null;
		String sql = "select * from Emprestimo where id = ?";

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
				Emprestimo = new Emprestimo();

				// atribui campo para atributo
				Emprestimo.setId(rset.getLong("id"));
				Emprestimo.setData_esmprestimo(rset.getDate("data_emprestimo"));
				Emprestimo.setData_entrega(rset.getDate("data_entrega"));
				Emprestimo.setValorEmprestimo(rset.getString("valorEmprestimo"));

				// buscando as chaves estrangeiras
				Emprestimo.setCliente(this.clienteDAO.get(rset.getLong("cliente_id")));
				Emprestimo.setFilmes(this.filmesDAO.get(rset.getLong("filmes_id")));
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
		return Emprestimo;
	}

	@Override
	public List<Emprestimo> getAll() {
		List<Emprestimo> Emprestimos = new ArrayList<Emprestimo>();

		String sql = "select * from Emprestimo";

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
				Emprestimo Emprestimo = new Emprestimo();

				// atribui campo para atributo
				Emprestimo.setId(rset.getLong("id"));
				Emprestimo.setData_esmprestimo(rset.getDate("data_emprestimo"));
				Emprestimo.setData_entrega(rset.getDate("data_entrega"));
				Emprestimo.setValorEmprestimo(rset.getString("valorEmprestimo"));

				// buscando as chaves estrangeiras
				Emprestimo.setCliente(this.clienteDAO.get(rset.getLong("cliente_id")));
				Emprestimo.setFilmes(this.filmesDAO.get(rset.getLong("filmes_id")));

				Emprestimos.add(Emprestimo);
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
		return Emprestimos;
	}

	@Override
	public int save(Emprestimo Emprestimo) {
		String sql = "insert into Emprestimo (data_emprestimo, data_entrega, valorEmrpestimo, cliente_id, filmes_id)" + " values (?, ?, ?, ?, ?)";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {

			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setDate(1, Emprestimo.getData_esmprestimo());
			stm.setDate(2, Emprestimo.getData_entrega());
			stm.setString(3, Emprestimo.getValorEmprestimo());
			stm.setLong(4, Emprestimo.getCliente().getId());
			stm.setLong(5, Emprestimo.getFilmes().getId());

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
	public boolean update(Emprestimo Emprestimo, String[] params) {
		String sql = "update Emprestimo set data_emprestimo = ?, data_entrega = ?, valorEmprestimo = ?, cliente_id = ?, filmes_id = ? where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setDate(1, Emprestimo.getData_esmprestimo());
			stm.setDate(2, Emprestimo.getData_entrega());
			stm.setString(3, Emprestimo.getValorEmprestimo());
			stm.setLong(4, Emprestimo.getCliente().getId());
			stm.setLong(5, Emprestimo.getFilmes().getId());
			stm.setLong(6, Emprestimo.getId());
			

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
	public boolean delete(Emprestimo Emprestimo) {
		String sql = "delete from Emprestimo where id = ?";

		// Recupera a conex�o com o banco
		Connection conexao = null;

		// Criar uma prepara��o da consulta
		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setLong(1, Emprestimo.getId());
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