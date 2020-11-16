package ftt.enums;

public enum EnumMatStatus {
	
	ATIVA("ATIVA"),	
	INATIVA("INATIVA");

	private String matStatus;

	EnumMatStatus(String _matStatus) {		
		this.matStatus = _matStatus;
	}	
	

	public String getMatStatusString() {
		return matStatus;
	}

}
