package database.model;

public class PokemonDeletado {
	private int id;
	private String pokemonDeletado;
	private String tipoPokemonDeletado;
	
	public PokemonDeletado() {
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPokemonDeletado() {
		return pokemonDeletado;
	}

	public void setPokemonDeletado(String pokemonDeletado) {
		this.pokemonDeletado = pokemonDeletado;
	}

	public String getTipoPokemonDeletado() {
		return tipoPokemonDeletado;
	}

	public void setTipoPokemonDeletado(String tipoPokemonDeletado) {
		this.tipoPokemonDeletado = tipoPokemonDeletado;
	}
}
