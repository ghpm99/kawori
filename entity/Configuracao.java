package entity;

public class Configuracao extends Entity {

	private String type = null;
	private String value = null;
			
	public Configuracao(String type, String value) {
		super();
		this.type = type;
		this.value = value;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}

	
}
