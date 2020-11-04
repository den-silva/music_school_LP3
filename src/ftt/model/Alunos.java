package ftt.model;

public class Alunos {
	private int id_aluno;
	private String nome;
	private Endereco endereco;
	private String email;
	private String senha;
	
	public Alunos () {
		
	}

	public int getId_aluno() {
		return id_aluno;
	}

	public void setId_aluno(int id_aluno) {
		this.id_aluno = id_aluno;
	}
	
	//Metodo setId_Aluno sobrescrito para receber String
	public void setId_aluno(String id_aluno) {
		if(id_aluno == null || id_aluno.isEmpty()) {
			this.id_aluno = 0;
		}else {
			this.id_aluno = Integer.valueOf(id_aluno);
		}	
		
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	

}
