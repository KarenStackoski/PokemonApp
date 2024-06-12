package database.model;

public class PokemonTotalizadoresModel {
	private int id;
	private int totalizadorFogo;
	private int totalizadorVoador;
	private int totalizadorEletrico;
	private int totalizadorDeletado;
	
	public PokemonTotalizadoresModel() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTotalizadorFogo() {
		return totalizadorFogo;
	}

	public void setTotalizadorFogo(int totalizadorFogo) {
		this.totalizadorFogo = totalizadorFogo;
	}

	public int getTotalizadorVoador() {
		return totalizadorVoador;
	}

	public void setTotalizadorVoador(int totalizadorVoador) {
		this.totalizadorVoador = totalizadorVoador;
	}

	public int getTotalizadorEletrico() {
		return totalizadorEletrico;
	}

	public void setTotalizadorEletrico(int totalizadorEletrico) {
		this.totalizadorEletrico = totalizadorEletrico;
	}

	public int getTotalizadorDeletado() {
		return totalizadorDeletado;
	}

	public void setTotalizadorDeletado(int totalizadorDeletado) {
		this.totalizadorDeletado = totalizadorDeletado;
	}
}
