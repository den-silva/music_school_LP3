package ftt.model;

import ftt.enums.EnumNivelCurso;

public class Turmas {
	private int id_turma;
	private int id_curso;
	private int id_professor;
	private EnumNivelCurso nivel;	
	
	public Turmas() {
		
	}

	public int getId_turma() {
		return id_turma;
	}

	public void setId_turma(int id_turma) {
		this.id_turma = id_turma;
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}

	public EnumNivelCurso getNivel() {
		return nivel;
	}

	public void setNivel(EnumNivelCurso nivel) {
		this.nivel = nivel;
	}
	
	
	

}
