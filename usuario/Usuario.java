package usuario;

public class Usuario {

	private String nome;
	
	private String tag;

	private String id;

	private String familia;

	private int acesso;

	public Usuario(String nome, String tag,String id, String familia, int acesso) {
		this.nome = nome;
		this.tag = tag;
		this.id = id;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
