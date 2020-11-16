package ftt.model;

import ftt.enums.EnumNivelCurso;
import ftt.enums.EnumPeriodo;

public class Cursos {
	
	private int id_curso;
	private String nome;
	private EnumNivelCurso nivel;
	private EnumPeriodo periodo;	
	
	public Cursos () {
		
	}

	public int getId_curso() {
		return id_curso;
	}

	public void setId_curso(int id_curso) {
		this.id_curso = id_curso;
	}
	
	public void setId_curso(String id_curso) {
		if (id_curso ==null)
			this.setId_curso(0);
		else			
			this.id_curso = Integer.valueOf(id_curso);
	}

	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}

	public EnumNivelCurso getNivel() {
		return nivel;
	}

	public void setNivel(EnumNivelCurso nivel) {
		this.nivel = nivel;
	}

	public EnumPeriodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(EnumPeriodo periodo) {
		this.periodo = periodo;
	}


	
	
	

}
