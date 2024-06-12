package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonVoadorModel;

public class PokemonVoadorDAO {
	private String script = ""; //onde será implementado o script sql
	private PreparedStatement psScript;
	
	public PokemonVoadorDAO(Connection connection) throws SQLException {
		psScript = connection.prepareStatement(script);
	}
	
	public ArrayList<PokemonVoadorModel> selectAll() throws SQLException{
		ArrayList<PokemonVoadorModel> flyingPokemonList = new ArrayList<PokemonVoadorModel>();
		ResultSet result = psScript.executeQuery();
		if (result != null) {
			result.first();
			while(result.isAfterLast()) {
				PokemonVoadorModel pvm = new PokemonVoadorModel();
				pvm.setId(result.getInt("id_pokemon_voador"));
				pvm.setPokemonVoador(result.getString("pokemon_voador"));
				flyingPokemonList.add(pvm);
				result.next();
			}
		}
		return flyingPokemonList;
	}
}
