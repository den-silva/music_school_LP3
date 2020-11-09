package ftt.model;

public class Matriculas {
	
	private int id_matricula;
	private int id_aluno;
	private String nome_aluno;
	private int id_curso;	
	private String nome_curso;
	
	
	public Matriculas () {
		
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


	public int getId_aluno() {
		return id_aluno;
	}


	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}
	
	public void setId_aluno(String id_aluno) {
		if (id_aluno==null)
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
		if (id_matricula ==null)
			this.setId_matricula(0);
			//this.id_matricula =0;
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
	


	
	
	

}
