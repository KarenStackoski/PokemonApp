package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonFogoModel;

public class PokemonFogoDAO {
	private String scriptSelect = ""; //onde será implementado o script sql
	private String scriptInsert = "";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	
	public PokemonFogoDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
	}
	
	public boolean insert(PokemonFogoModel firePokemon) throws SQLException {
		psScriptInsert.clearParameters();
		psScriptInsert.setInt(1, firePokemon.getId());
		psScriptInsert.setString(2, firePokemon.getPokemonFogo());
		return psScriptInsert.execute();
	}
	
	public ArrayList<PokemonFogoModel> selectAll() throws SQLException{
		ArrayList<PokemonFogoModel> firePokemonList = new ArrayList<PokemonFogoModel>();
		ResultSet result = psScriptSelect.executeQuery();
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
