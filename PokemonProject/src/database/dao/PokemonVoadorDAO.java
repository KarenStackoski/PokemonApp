package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonVoadorModel;

public class PokemonVoadorDAO {
	private String scriptSelect = "select * from tb_pokemon_voador"; //onde será implementado o script sql
	private String scriptInsert = "insert into tb_pokemon_voador  (id_pokemon_voador , pokemon_voador) values (?,?)";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	
	public PokemonVoadorDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
	}
	
	public boolean insert(PokemonVoadorModel flyingPokemon) throws SQLException {
		psScriptInsert.clearParameters();
		psScriptInsert.setInt(1, flyingPokemon.getId());
		psScriptInsert.setString(2, flyingPokemon.getPokemonVoador());
		return psScriptInsert.execute();
	}
	
	public ArrayList<PokemonVoadorModel> selectAll() throws SQLException{
		ArrayList<PokemonVoadorModel> flyingPokemonList = new ArrayList<PokemonVoadorModel>();
		ResultSet result = psScriptSelect.executeQuery();
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
