package ftt.enums;

public enum EnumPeriodo {
	
	_3_MESES("3 meses"), 
	_6_MESES("6 meses"), 
	_9_MESES("9 meses"),
	_12_MESES("12 meses");

	private String periodo;

	EnumPeriodo(String _periodo) {		
		this.periodo = _periodo;
	}	
	

	public String getPeriodoCurso() {
		return periodo;
	}

}
