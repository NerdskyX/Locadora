package com.ifsc.tds.Vitor.Cassio.Igor.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.ifsc.tds.Vitor.Cassio.Igor.entity.Filmes;

public class FilmeDAO implements DAO<Filmes>{

	@Override
	public Filmes get(Long id) {
		Filmes filmes = null;
		String sql = "select * from filmes where id = ?";

		Connection conexao = null;

		PreparedStatement stm = null;

		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setInt(1, id.intValue());
			rset = stm.executeQuery();

			while (rset.next()) {
				filmes = new Filmes();

				filmes.setId(rset.getLong("id"));
				filmes.setNome(rset.getString("nome"));
				filmes.setDataLancamento(rset.getDate("data_lancamento"));
				
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

		return filmes;
	}

	@Override
	public List<Filmes> getAll() {
		List<Filmes> filmes = new ArrayList<Filmes>();
		String sql = "select * from filmes";

		Connection conexao = null;

		PreparedStatement stm = null;

		ResultSet rset = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			rset = stm.executeQuery();

			while (rset.next()) {
				Filmes filme = new Filmes();

				filme.setId(rset.getLong("id"));
				filme.setNome(rset.getString("nome"));
				filme.setDataLancamento(rset.getDate("data_lancamento"));

				filmes.add(filme);
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

		return filmes;
	}

	@Override
	public int save(Filmes filmes) {
		String sql = "insert into filmes(nome, data_lancamento)" + " values(?, ?)";

		Connection conexao = null;

		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, filmes.getNome());
			stm.setDate(2, filmes.getDataLancamento());

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
	public boolean update(Filmes filmes, String[] params) {
		String sql = "update filmes set nome =  ?, data_lancamento = ? where id = ?";

		Connection conexao = null;

		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);
			stm.setString(1, filmes.getNome());
			stm.setDate(2, filmes.getDataLancamento());
			stm.setLong(3, filmes.getId());

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
	public boolean delete(Filmes filmes) {
		String sql = "delete from filmes where id = ?";

		Connection conexao = null;

		PreparedStatement stm = null;

		try {
			conexao = new Conexao().getConnection();

			stm = conexao.prepareStatement(sql);

			stm.setLong(1, filmes.getId());

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
