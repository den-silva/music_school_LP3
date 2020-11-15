package ftt.model;

import ftt.enums.EnumMatStatus;

public class Matriculas {

	private int id_matricula;
	private int id_aluno;
	private String nome_aluno;
	private int id_turma;
	private String nome_curso;
	private EnumMatStatus mat_status;

	public Matriculas() {

	}

	public int getId_turma() {
		return id_turma;
	}

	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}

	public void setId_turma(String id_turma) {
		if (id_turma == null)
			this.setId_turma(0);
		else
			this.id_turma = Integer.valueOf(id_turma);
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}

	public void setId_aluno(String id_aluno) {
		if (id_aluno == null)
			this.setId_aluno(0);
		else
			this.id_aluno = Integer.valueOf(id_aluno);
	}

	public int getId_matricula() {
		return id_matricula;
	}

	public void setId_matricula(int id_matricula) {
		this.id_matricula = id_matricula;
	}

	public void setId_matricula(String id_matricula) {
		if (id_matricula == null)
			this.setId_matricula(0);		
		else
			this.id_matricula = Integer.valueOf(id_matricula);
	}

	/**
	 * @return the nome_aluno
	 */
	public String getNome_aluno() {
		return nome_aluno;
	}

	/**
	 * @param nome_aluno the nome_aluno to set
	 */
	public void setNome_aluno(String nome_aluno) {
		this.nome_aluno = nome_aluno;
	}

	/**
	 * @return the nome_curso
	 */
	public String getNome_curso() {
		return nome_curso;
	}

	/**
	 * @param nome_curso the nome_curso to set
	 */
	public void setNome_curso(String nome_curso) {
		this.nome_curso = nome_curso;
	}

	public EnumMatStatus getMat_status() {
		return mat_status;
	}

	public void setMat_status(EnumMatStatus mat_status) {
		this.mat_status = mat_status;
	}

}
