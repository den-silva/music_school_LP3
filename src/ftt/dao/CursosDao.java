package ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ftt.model.Cursos;
import ftt.model.MetodosGerais;
import ftt.model.Cursos;

public class CursosDao implements IcrudPadrao<Cursos> {

	private Connection con;

	public CursosDao() {
		this.con = DbUtil.getConnection();
	}

	@Override
	public void insert(Cursos entidade) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement ps = con
				.prepareStatement("INSERT INTO tb_cursos" + " (nome, nivel, periodo) " + "VALUES (?, ?, ?)")) {
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getNivel().getNivelCurso());
			ps.setString(3, entidade.getPeriodo().getPeriodoCurso());

			ps.executeUpdate();
			System.out.println("Curso Adicionado!");

		}

	}

	@Override
	public void update(Cursos entidade) throws SQLException {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = 
				con.prepareStatement("UPDATE tb_cursos SET " 
		+ "nome=?, "
				+ "nivel=?, "
				+ "periodo=? " + "WHERE id_curso=?");) {
			ps.setString(1, entidade.getNome());
			ps.setString(2, entidade.getNivel().getNivelCurso());
			ps.setString(3, entidade.getPeriodo().getPeriodoCurso());
			ps.setInt(4, entidade.getId_curso());
			ps.executeUpdate();
			System.out.println("Professor " + entidade.getId_curso() + " atualizado");

		}

	}

	@Override
	public void delete(Cursos entidade) throws SQLException {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = con.prepareStatement("DELETE FROM tb_cursos WHERE id_curso = ?")) {

			ps.setInt(1, entidade.getId_curso());
			ps.execute();
			System.out.println("Professor " + entidade.getId_curso() + "excluido");
		}

		System.out.println("DELETE executado");
	}

	@Override
	public Cursos findForId(int id) throws SQLException {
		Cursos p = new Cursos();

		try (PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_cursos WHERE id_curso=?")) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					p.setId_curso(rs.getInt("id_curso"));
					p.setNome(rs.getString("nome"));					
					
					p.setNivel(MetodosGerais
							.stringParaEnumNivel(rs.getString("nivel")));
					p.setPeriodo(MetodosGerais
							.stringParaPeriodo(rs.getString("periodo")));
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
	public ArrayList<Cursos> findAll() throws SQLException {
		String comSql = "SELECT * FROM tb_cursos";
		ArrayList<Cursos> usList = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(comSql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Cursos p = new Cursos();
					p.setId_curso(rs.getInt("id_curso"));
					p.setNome(rs.getString("nome"));
					p.setNivel(MetodosGerais
							.stringParaEnumNivel(rs.getString("nivel")));
					p.setPeriodo(MetodosGerais
							.stringParaPeriodo(rs.getString("periodo")));
					usList.add(p);
				}
			}
		}

		// TODO Auto-generated method stub
		return usList;
	}
	
	public int proximoId() {
		int proximo = 1;
		// String comandoSql = "select max(id_aluno) + 1 as proximo from tb_alunos";
		String comandoSql = "select max(id_curso) + 1 as proximo from tb_cursos";

		try (PreparedStatement params = con.prepareStatement(comandoSql)) {
			try (ResultSet dados = params.executeQuery()) {
				if (dados.next()) {
					if (dados.getInt("proximo")!=0) {
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
