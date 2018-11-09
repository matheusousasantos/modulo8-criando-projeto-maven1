package model;

public class Telefoneuser {
	
	private Long id;
	private String numero;
	private String tipo;
	
	private Long userPostegres;//Referênte a classe entrangeira...

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Long getUserPostegres() {
		return userPostegres;
	}

	public void setUserPostegres(Long userPostegres) {
		this.userPostegres = userPostegres;
	}
	
	

}
