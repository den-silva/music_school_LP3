package ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ftt.model.Usuarios;

public class UsuariosDao implements IcrudPadrao<Usuarios> {
	private Connection con;

	public UsuariosDao() {
		this.con = DbUtil.getConnection();

	}

	@Override
	public void insert(Usuarios entidade) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("Inicio usuarios INSERT");
		String query = "INSERT INTO tb_usuarios " + "(email, senha) VALUES (?, ?)";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, entidade.getEmail());
			ps.setString(2, entidade.getSenha());
			ps.executeUpdate();

			System.out.println("Final usuarios INSERT");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void update(Usuarios entidade) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio usuarios UPDATE");
		String query = "UPDATE tb_usuarios SET " + "email = ?, senha = ? " + "WHERE id_usuario = ?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setString(1, entidade.getEmail());
			ps.setString(2, entidade.getSenha());
			ps.setInt(3, entidade.getId_usuario());

			ps.executeUpdate();

			System.out.println("Final usuarios UPDATE");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Usuarios entidade) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio DELETE");
		String query = "DELETE FROM tb_usuarios WHERE id_usuario = ?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, entidade.getId_usuario());
			ps.executeUpdate();
			System.out.println("Usuario " + entidade.getId_usuario() + " excluido");

			System.out.println("Final usuarios DELETE");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Usuarios findForId(int id) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio usuarios FIND_FOR_ID");
		String query = "SELECT * FROM tb_usuarios where id_usuario = ?";
		Usuarios usuario = new Usuarios();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					usuario.setId_usuario(rs.getInt("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));
				}
			}

			System.out.println("Final usuarios FIND_FOR_ID");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return usuario;
	}

	@Override
	public ArrayList<Usuarios> findAll() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio usuarios FIND_ALL");
		String query = "SELECT * FROM tb_usuarios";
		ArrayList<Usuarios> listaUsuarios = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Usuarios usuario = new Usuarios();
					usuario.setId_usuario(rs.getInt("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setSenha(rs.getString("senha"));
					listaUsuarios.add(usuario);
				}
			}

			System.out.println("Final usuarios FIND_ALL");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listaUsuarios;

	}

	public int proximoId() {
		int proximo = 0;
		// String comandoSql = "select max(id_aluno) + 1 as proximo from tb_alunos";
		String comandoSql = "select max(id_usuario) + 1 as proximo from tb_usuarios";

		try (PreparedStatement params = con.prepareStatement(comandoSql)) {
			try (ResultSet dados = params.executeQuery()) {
				if (dados.next()) {
					if (dados.getInt("proximo") != 0) {
						proximo = dados.getInt("proximo");
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return proximo;
	}

}
