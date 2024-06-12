package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonFogoModel;

public class PokemonFogoDAO {
	private String script = ""; //onde será implementado o script sql
	private PreparedStatement psScript;
	
	public PokemonFogoDAO(Connection connection) throws SQLException {
		psScript = connection.prepareStatement(script);
	}
	
	public ArrayList<PokemonFogoModel> selectAll() throws SQLException{
		ArrayList<PokemonFogoModel> firePokemonList = new ArrayList<PokemonFogoModel>();
		ResultSet result = psScript.executeQuery();
		if (result != null) {
			result.first();
			while(result.isAfterLast()) {
				PokemonFogoModel pfm = new PokemonFogoModel();
				pfm.setId(result.getInt("id_pokemon_fogo"));
				pfm.setPokemonFogo(result.getString("pokemon_fogo"));
				firePokemonList.add(pfm);
				result.next();
			}
		}
		return firePokemonList;
	}
}
