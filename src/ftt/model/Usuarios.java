package ftt.model;

public class Usuarios {
	private int id_usuario;
	
	private String email;
	private String senha;
	
	public Usuarios () {
		
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	
	//Metodo setId_Aluno sobrescrito para receber String
	public void setId_usuario(String id_usuario) {
		if(id_usuario == null || id_usuario.isEmpty()) {
			this.id_usuario = 0;
		}else {
			this.id_usuario = Integer.valueOf(id_usuario);
		}	
		
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
