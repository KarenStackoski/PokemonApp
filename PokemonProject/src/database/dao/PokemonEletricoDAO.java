package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonEletricoModel;

public class PokemonEletricoDAO {
	private String scriptSelect = ""; //onde será implementado o script sql
	private String scriptInsert = "";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	
	public PokemonEletricoDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
	}
	
	public boolean insert(PokemonEletricoModel eletricPokemon) throws SQLException {
		psScriptInsert.clearParameters();
		psScriptInsert.setInt(1, eletricPokemon.getId());
		psScriptInsert.setString(0, eletricPokemon.getPokemonEletrico());
		return psScriptInsert.execute();
	}
	
	public ArrayList<PokemonEletricoModel> selectAll() throws SQLException{
		ArrayList<PokemonEletricoModel> eletricPokemonList = new ArrayList<PokemonEletricoModel>();
		ResultSet result = psScriptSelect.executeQuery();
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
