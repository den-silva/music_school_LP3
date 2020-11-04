package ftt.model;

public class Professores {
	
	private int id_professor;
	private String nome;
	private Endereco endereco;
	private String email;
	private String senha;
	
	public Professores () {
		
	}

	public int getId_professor() {
		return id_professor;
	}

	public void setId_professor(int id_professor) {
		this.id_professor = id_professor;
	}
	
	public void setId_professor(String id_professor) {
		if (id_professor ==null)
			this.setId_professor(0);
		else			
			this.id_professor = Integer.valueOf(id_professor);
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
