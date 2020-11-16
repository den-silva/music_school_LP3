package ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ftt.enums.EnumNivelCurso;
import ftt.model.MetodosGerais;
import ftt.model.Turmas;
import ftt.model.Usuarios;

public class TurmasDao implements IcrudPadrao<Turmas> {
	private Connection con;

	public TurmasDao() {
		this.con = DbUtil.getConnection();

	}

	@Override
	public void insert(Turmas entidade) throws SQLException {
		// TODO Auto-generated method stub

		System.out.println("Inicio turmas INSERT");
		String query = "INSERT INTO tb_turmas " + "(id_curso, id_professor, horario) VALUES (?, ?, ?)";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, entidade.getId_curso());
			ps.setInt(2, entidade.getId_professor());
			ps.setString(3, MetodosGerais.listaHorariosParaString(entidade.getHorarios()));
			ps.executeUpdate();

			System.out.println("Final turmas INSERT");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void update(Turmas entidade) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio turmas UPDATE");
		String query = "UPDATE tb_turmas SET " + "id_curso = ?, id_professor = ?, horario = ? " + "WHERE id_turma = ?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, entidade.getId_curso());
			ps.setInt(2, entidade.getId_professor());
			ps.setString(3, MetodosGerais.listaHorariosParaString(entidade.getHorarios()));
			ps.setInt(4, entidade.getId_turma());

			ps.executeUpdate();

			System.out.println("Final turmas UPDATE");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public void delete(Turmas entidade) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio turmas DELETE");
		String query = "DELETE FROM tb_turmas WHERE id_turma = ?";

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, entidade.getId_turma());
			ps.executeUpdate();
			System.out.println("Turma " + entidade.getId_turma() + " excluida");

			System.out.println("Final turmas DELETE");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	@Override
	public Turmas findForId(int id) throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio turmas FIND_FOR_ID");
		// String query = "SELECT * FROM tb_turmas where id_turma = ?";
		String query = "SELECT t.*, c.nome as nome_curso , p.nome as nome_professor " + "FROM tb_turmas t "
				+ "inner join tb_cursos c on c.id_curso=t.id_curso "
				+ "inner join tb_professores p on p.id_professor=t.id_professor where id_turma = ?";
		Turmas turma = new Turmas();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					turma.setId_turma(rs.getInt("id_turma"));
					turma.setId_curso(rs.getInt("id_curso"));
					turma.setNome_curso(rs.getString("nome_curso"));
					turma.setId_professor(rs.getInt("id_professor"));
					turma.setNome_professor(rs.getString("nome_professor"));

					String horarios = rs.getString("horario");

					turma.setHorarios(MetodosGerais.stringParaListaHorarios(horarios));

//					if (nivel.equals(EnumNivelCurso.BASICO.getNivelCurso()) ) {
//						turma.setNivel(EnumNivelCurso.BASICO);
//					} else if (nivel.equals(EnumNivelCurso.INTERMEDIARIO.getNivelCurso())) {
//						turma.setNivel(EnumNivelCurso.INTERMEDIARIO);
//					} else {
//						turma.setNivel(EnumNivelCurso.AVANCADO);
//					}
				}
			}

			System.out.println("Final turmas FIND_FOR_ID");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return turma;
	}

	@Override
	public ArrayList<Turmas> findAll() throws SQLException {
		// TODO Auto-generated method stub
		System.out.println("Inicio turmas FIND_ALL");
		// String query = "SELECT * FROM tb_turmas";
		String query = "SELECT t.*, c.nome as nome_curso , p.nome as nome_professor " + "FROM tb_turmas t "
				+ "inner join tb_cursos c on c.id_curso=t.id_curso "
				+ "inner join tb_professores p on p.id_professor=t.id_professor ";
		ArrayList<Turmas> listaTurmas = new ArrayList<>();
//		CursosDao cursoDao = new CursosDao();
//		ProfessoresDao profDao = new ProfessoresDao();

		try (PreparedStatement ps = con.prepareStatement(query)) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Turmas turma = new Turmas();

					turma.setId_turma(rs.getInt("id_turma"));
					turma.setId_curso(rs.getInt("id_curso"));
					turma.setNome_curso(rs.getString("nome_curso"));
					turma.setId_professor(rs.getInt("id_professor"));
					turma.setNome_professor(rs.getString("nome_professor"));

					String horarios = rs.getString("horario");

					turma.setHorarios(MetodosGerais.stringParaListaHorarios(horarios));

//					if (nivel == EnumNivelCurso.BASICO.getNivelCurso()) {
//						turma.setNivel(EnumNivelCurso.BASICO);
//					} else if (nivel == EnumNivelCurso.INTERMEDIARIO.getNivelCurso()) {
//						turma.setNivel(EnumNivelCurso.INTERMEDIARIO);
//					} else {
//						turma.setNivel(EnumNivelCurso.AVANCADO);
//					}
					listaTurmas.add(turma);
				}
			}

			System.out.println("Final turmas FIND_ALL");

		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return listaTurmas;

	}

	public int proximoId() {
		int proximo = 0;
		// String comandoSql = "select max(id_aluno) + 1 as proximo from tb_alunos";
		String comandoSql = "select max(id_turma) + 1 as proximo from tb_turmas";

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
