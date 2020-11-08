package ftt.enums;

public enum EnumNivelCurso {
	// [BASICO | INTERMEDIARIO | AVANCADO]
	BASICO("BASICO"), INTERMEDIARIO("INTERMEDIARIO"), AVANCADO("AVANCADO");

	private String nivel;

	EnumNivelCurso(String _nivel) {
		
		this.nivel = _nivel;
	}	
	

	public String getNivelCurso() {
		return nivel;
	}

}
