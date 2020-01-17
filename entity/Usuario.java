package entity;

public class Usuario extends Entity {

	private String nome;
	
	private String tag;

	private String idDiscord;

	private String familia;

	private int acesso;

	public Usuario(String nome, String tag,String idDiscord, String familia, int acesso) {
		this.nome = nome;
		this.tag = tag;
		this.idDiscord = idDiscord;
		this.familia = familia;
		this.acesso = acesso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public String getIdDiscord() {
		return idDiscord;
	}

	public void setIdDiscord(String idDiscord) {
		this.idDiscord = idDiscord;
	}

	public String getFamilia() {
		return familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public int getAcesso() {
		return acesso;
	}

	public void setAcesso(int acesso) {
		this.acesso = acesso;
	}

	
	
}
