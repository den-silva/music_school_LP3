package ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ftt.model.Alunos;
import ftt.model.Endereco;
import ftt.model.MetodosGerais;

public class AlunosDao implements IcrudPadrao<Alunos> {
	private Connection con;

	public AlunosDao() {
		this.con = DbUtil.getConnection();

	}

	@Override
	public void insert(Alunos entidade) throws SQLException {
		// TODO Auto-generated method stub
		/*
		 * private int id_aluno; private String nome; private Endereco endereco; private
		 * String email; private String senha;
		 */
		System.out.println("Inicio INSERT");
		String query = "INSERT INTO tb_alunos " + "(nome, endereco, email, senha) VALUES (?, ?, ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, entidade.getNome());
			String endereco = MetodosGerais.mudaEnderecoParaString(entidade.getEndereco());
			ps.setString(2, endereco);
			ps.setString(3, entidade.getEmail());
			ps.setString(4, entidade.getSenha());
			ps.executeUpdate();

			System.out.println("Final INSERT");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void update(Alunos entidade) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio UPDATE");
		String query = "UPDATE tb_alunos SET " + "nome = ?, endereco = ?, email = ?, senha = ? " + "WHERE id_aluno = ?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, entidade.getNome());
			String endereco = MetodosGerais.mudaEnderecoParaString(entidade.getEndereco());
			ps.setString(2, endereco);
			ps.setString(3, entidade.getEmail());
			ps.setString(4, entidade.getSenha());
			ps.setInt(5, entidade.getId_aluno());

			ps.executeUpdate();

			System.out.println("Final UPDATE");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Alunos entidade) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio DELETE");
		String query = "DELETE FROM tb_alunos WHERE id_aluno = ?";
		Alunos aluno = new Alunos();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, entidade.getId_aluno());			
			ps.executeUpdate();
			System.out.println("Aluno " + entidade.getId_aluno() + " excluido");
			
			System.out.println("Final DELETE");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Alunos findForId(int id) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio FIND_FOR_ID");
		String query = "SELECT * FROM tb_alunos where id_aluno = ?";
		Alunos aluno = new Alunos();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					aluno.setId_aluno(rs.getInt("id_aluno"));
					aluno.setNome(rs.getString("nome"));
					Endereco endereco = MetodosGerais.mudaStringParaEndereco(rs.getString("endereco"));
					aluno.setEndereco(endereco);
					aluno.setEmail(rs.getString("email"));
					aluno.setSenha(rs.getString("senha"));
				}
			}

			System.out.println("Final FIND_FOR_ID");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return aluno;
	}

	@Override
	public ArrayList<Alunos> findAll() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio FIND_ALL");
		String query = "SELECT * FROM tb_alunos";
		ArrayList<Alunos> listaAlunos = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Alunos aluno = new Alunos();
					aluno.setId_aluno(rs.getInt("id_aluno"));
					aluno.setNome(rs.getString("nome"));
					Endereco endereco = MetodosGerais.mudaStringParaEndereco(rs.getString("endereco"));
					aluno.setEndereco(endereco);
					aluno.setEmail(rs.getString("email"));
					aluno.setSenha(rs.getString("senha"));
					listaAlunos.add(aluno);
				}
			}

			System.out.println("Final FIND_ALL");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listaAlunos;

	}

	public int proximoId() {
		int proximo = 0;
		//String comandoSql = "select max(id_aluno) + 1 as proximo from tb_alunos";
		String comandoSql = "select max(id_aluno) + 1 as proximo from tb_alunos";
		
		try (PreparedStatement params = con.prepareStatement(comandoSql)) {
			try (ResultSet dados = params.executeQuery()) {
				if(dados.next()) {
					proximo = dados.getInt("proximo");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return proximo;
	}

}
