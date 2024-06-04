package database.model;

public class PokemonModel {
	private int id;
	private String pokemon;
	private String tipo;
	
	public PokemonModel() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPokemon() {
		return pokemon;
	}

	public void setPokemon(String pokemon) {
		this.pokemon = pokemon;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
