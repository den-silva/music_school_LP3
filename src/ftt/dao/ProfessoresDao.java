package ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ftt.model.Endereco;
import ftt.model.MetodosGerais;
import ftt.model.Professores;

public class ProfessoresDao implements IcrudPadrao<Professores> {

	private Connection con;

	public ProfessoresDao() {
		this.con = DbUtil.getConnection();
	}

	@Override
	public void insert(Professores entidade) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement ps = con.prepareStatement(
				"INSERT INTO tb_professores" + " (nome, endereco, email, senha) VALUES (?, ?, ?, ?)")) {
			ps.setString(1, entidade.getNome());
			String endereco = MetodosGerais.mudaEnderecoParaString(entidade.getEndereco());
			ps.setString(2, endereco);
			ps.setString(3, entidade.getEmail());
			ps.setString(4, entidade.getSenha());
			ps.executeUpdate();
			System.out.println("Professor Adicionado!");

		}

	}

	@Override
	public void update(Professores entidade) throws SQLException {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = con.prepareStatement("UPDATE tb_professores SET " + "nome = ?, " + "endereco = ?, "
				+ "email = ?, " + "senha = ? " + "WHERE id_professor=?");) {
			ps.setString(1, entidade.getNome());
			String endereco = MetodosGerais.mudaEnderecoParaString(entidade.getEndereco());
			ps.setString(2, endereco);

			ps.setString(3, entidade.getEmail());
			ps.setString(4, entidade.getSenha());
			ps.setInt(5, entidade.getId_professor());
			ps.executeUpdate();
			System.out.println("Professor " + entidade.getId_professor() + " atualizado");

		}

	}

	@Override
	public void delete(Professores entidade) throws SQLException {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = con.prepareStatement("DELETE FROM tb_professores WHERE id_professor = ?")) {

			ps.setInt(1, entidade.getId_professor());
			ps.execute();
			System.out.println("Professor " + entidade.getId_professor() + "excluido");
		}

		System.out.println("DELETE executado");
	}

	@Override
	public Professores findForId(int id) throws SQLException {
		Professores p = new Professores();

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_professores WHERE id_professor=?")) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					p.setId_professor(rs.getInt("id_professor"));
					p.setNome(rs.getString("nome"));

					Endereco endereco = MetodosGerais.mudaStringParaEndereco(rs.getString("endereco"));
					p.setEndereco(endereco);
					p.setEmail(rs.getString("email"));
					p.setSenha(rs.getString("senha"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" FIND-FOR-ID executada");
		return p;
	}

	@Override
	public ArrayList<Professores> findAll() throws SQLException {
		String comSql = "SELECT * FROM tb_professores";
		ArrayList<Professores> usList = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(comSql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Professores p = new Professores();
					p.setId_professor(rs.getInt("id_professor"));
					p.setNome(rs.getString("nome"));

					Endereco endereco = MetodosGerais.mudaStringParaEndereco(rs.getString("endereco"));
					p.setEndereco(endereco);
					p.setEmail(rs.getString("email"));
					p.setSenha(rs.getString("senha"));
					usList.add(p);

				}
			}
		}

		// TODO Auto-generated method stub
		return usList;
	}

}
