package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonModel;

public class PokemonDAO {
	private String script = ""; //onde será implementado o script sql
	private PreparedStatement psScript;
	
	public PokemonDAO(Connection connection) throws SQLException {
		psScript = connection.prepareStatement(script);
	}
	
	public ArrayList<PokemonModel> selectAll() throws SQLException{
		ArrayList<PokemonModel> pokemonList = new ArrayList<PokemonModel>();
		ResultSet result = psScript.executeQuery();
		if (result != null) {
			result.first();
			while(result.isAfterLast()) {
				PokemonModel pm = new PokemonModel();
				pm.setId(result.getInt("id_pokemon"));
				pm.setPokemon(result.getString("pokemon"));
				pm.setTipo(result.getString("tipo_pokemon"));
				pokemonList.add(pm);
				result.next();
			}
		}
		return pokemonList;
	}
}
