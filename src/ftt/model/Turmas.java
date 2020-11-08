package ftt.model;

import ftt.enums.EnumNivelCurso;

public class Turmas {
	private int id_turma;
	private int id_curso;
	private String nome_curso;
	private int id_professor;
	private String nome_professor;
	private EnumNivelCurso nivel;

	public Turmas() {

	}

	public int getId_turma() {
		return id_turma;
	}

	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}

	// Metodo setId_turma sobrescrito para receber String
	public void setId_turma(String id_turma) {
		if (id_turma == null || id_turma.isEmpty()) {
			this.id_turma = 0;
		} else {
			this.id_turma = Integer.valueOf(id_turma);
		}
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	// Metodo setId_curso sobrescrito para receber String
	public void setId_curso(String id_curso) {
		if (id_curso == null || id_curso.isEmpty()) {
			this.id_curso = 0;
		} else {
			this.id_curso = Integer.valueOf(id_curso);
		}
	}

	public String getNome_curso() {
		return nome_curso;
	}

	public void setNome_curso(String nome_curso) {
		this.nome_curso = nome_curso;
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}
	
	// Metodo setId_professor sobrescrito para receber String
		public void setId_professor(String id_professor) {
			if (id_professor == null || id_professor.isEmpty()) {
				this.id_professor = 0;
			} else {
				this.id_professor = Integer.valueOf(id_professor);
			}
		}

	public String getNome_professor() {
		return nome_professor;
	}

	public void setNome_professor(String nome_professor) {
		this.nome_professor = nome_professor;
	}

	public EnumNivelCurso getNivel() {
		return nivel;
	}

	public void setNivel(EnumNivelCurso nivel) {
		this.nivel = nivel;
	}

}
