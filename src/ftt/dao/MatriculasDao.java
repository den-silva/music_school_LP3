package ftt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import ftt.model.Cursos;
import ftt.model.Matriculas;
import ftt.model.Cursos;

public class MatriculasDao implements IcrudPadrao<Matriculas> {

	private Connection con;

	public MatriculasDao() {
		this.con = DbUtil.getConnection();
	}

	@Override
	public void insert(Matriculas entidade) throws SQLException {
		// TODO Auto-generated method stub
		try (PreparedStatement ps = con
				.prepareStatement("INSERT INTO tb_matriculas " + "(id_aluno, id_curso) " + "VALUES (?, ?)")) {
			ps.setInt(1, entidade.getId_aluno());
			ps.setInt(2, entidade.getId_turma());

			ps.executeUpdate();
			System.out.println("Matricula Realizada!");
			

		}

	}

	@Override
	public void update(Matriculas entidade) throws SQLException {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = con.prepareStatement(
				"UPDATE tb_matriculas SET " + "id_aluno = ?, " + "id_curso = ? " + "WHERE id_matricula = ?");) {
			ps.setInt(1, entidade.getId_aluno());
			ps.setInt(2, entidade.getId_turma());
			ps.setInt(3, entidade.getId_matricula());
			ps.executeUpdate();
			System.out.println("Matricula " + entidade.getId_matricula() + "vinculando o aluno "
					+ entidade.getId_aluno() + "com o curso " + entidade.getId_turma() + " atualizada");

		}

	}

	@Override
	public void delete(Matriculas entidade) throws SQLException {
		// TODO Auto-generated method stub

		try (PreparedStatement ps = con.prepareStatement("DELETE FROM tb_matriculas WHERE id_matricula = ?")) {
			// Por enquanto o banco está permitindo duas matriculas repetidas, quando isso
			// for solucionado o "AND" na query
			// irá ser removido
			ps.setInt(1, entidade.getId_matricula());

			ps.execute();
			System.out.println("Matricula" + entidade.getId_matricula() + "excluido");
		}

		System.out.println("DELETE executado");
	}

	@Override
	public Matriculas findForId(int id) throws SQLException {
		Matriculas m = new Matriculas();

		//try (PreparedStatement ps = con.prepareStatement("SELECT * FROM tb_matriculas WHERE id_matricula = ?")) {
		try (PreparedStatement ps = con.prepareStatement("select id_matricula, a.id_aluno, a.nome as nome_aluno , c.id_curso, c.nome as nome_curso, valor_status as status from tb_matriculas m inner join "+ 
				"tb_alunos a on m.id_aluno = a.id_aluno " + 
				"inner join tb_cursos c on c.id_curso = m.id_curso where id_matricula = ?")) {

			ps.setInt(1, id);

			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					m.setId_matricula(rs.getInt("id_matricula"));
					m.setId_aluno(rs.getInt("id_aluno"));
					m.setNome_aluno(rs.getString("nome_aluno"));
					m.setId_turma(rs.getInt("id_curso"));					
					m.setNome_curso(rs.getString("nome_curso"));
					m.setStatus(rs.getNString("status"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(" FIND-FOR-ID executada");
		return m;
	}

	@Override
	public ArrayList<Matriculas> findAll() throws SQLException {
		String comSql = "select id_matricula, a.id_aluno, a.nome as nome_aluno , c.id_curso, c.nome as nome_curso, valor_status as status from tb_matriculas m inner join "+ 
				"tb_alunos a on m.id_aluno = a.id_aluno " + 
				"inner join tb_cursos c on c.id_curso = m.id_curso";
		ArrayList<Matriculas> usList = new ArrayList<>();

		try (PreparedStatement ps = con.prepareStatement(comSql)) {

			try (ResultSet rs = ps.executeQuery()) {

				while (rs.next()) {
					Matriculas m = new Matriculas();
					m.setId_matricula(rs.getInt("id_matricula"));
					m.setId_aluno(rs.getInt("id_aluno"));
					m.setNome_aluno(rs.getString("nome_aluno"));
					m.setId_turma(rs.getInt("id_curso"));					
					m.setNome_curso(rs.getString("nome_curso"));
					m.setStatus(rs.getString("status"));
				
					
					usList.add(m);

				}

			}

		}

		// TODO Auto-generated method stub
		return usList;
	}

}
