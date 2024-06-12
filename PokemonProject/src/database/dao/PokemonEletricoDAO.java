package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonEletricoModel;

public class PokemonEletricoDAO {
	private String script = ""; //onde será implementado o script sql
	private PreparedStatement psScript;
	
	public PokemonEletricoDAO(Connection connection) throws SQLException {
		psScript = connection.prepareStatement(script);
	}
	
	public ArrayList<PokemonEletricoModel> selectAll() throws SQLException{
		ArrayList<PokemonEletricoModel> eletricPokemonList = new ArrayList<PokemonEletricoModel>();
		ResultSet result = psScript.executeQuery();
		if (result != null) {
			result.first();
			while(result.isAfterLast()) {
				PokemonEletricoModel pem = new PokemonEletricoModel();
				pem.setId(result.getInt("id_pokemon_eletrico"));
				pem.setPokemonEletrico("pokemon_eletrico");
				eletricPokemonList.add(pem);
				result.next();
			}
		}
		return eletricPokemonList;
	}
}
