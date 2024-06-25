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
    private String scriptExists = "SELECT 1 FROM tb_pokemon_voador WHERE pokemon_voador = ?";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
    private PreparedStatement psScriptExists;

	
	public PokemonVoadorDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
        psScriptExists = connection.prepareStatement(scriptExists);

	}
	
	private boolean pokemonExists(String pokemonName) throws SQLException {
	    psScriptExists.clearParameters();
	    psScriptExists.setString(1, pokemonName);
	    ResultSet rs = psScriptExists.executeQuery();
	    return rs.next();
	}
	
	public boolean insert(PokemonVoadorModel voadorPokemon) throws SQLException {
		 if (!pokemonExists(voadorPokemon.getPokemonVoador())) {
	            psScriptInsert.clearParameters();
	            psScriptInsert.setInt(1, voadorPokemon.getId());
	            psScriptInsert.setString(2, voadorPokemon.getPokemonVoador());
	            return psScriptInsert.execute();
	        } else {
	            System.out.println("Este Pokémon já existe na tabela de Pokémon voadores.");
	            return false;
	        }
	    }	
	public ArrayList<PokemonVoadorModel> selectAll() throws SQLException{
		ArrayList<PokemonVoadorModel> flyingPokemonList = new ArrayList<PokemonVoadorModel>();
		ResultSet result = psScriptSelect.executeQuery();
		while(result.next()) {
			PokemonVoadorModel pvm = new PokemonVoadorModel();
			pvm.setId(result.getInt("id_pokemon_voador"));
			pvm.setPokemonVoador(result.getString("pokemon_voador"));
			flyingPokemonList.add(pvm);
		}
		return flyingPokemonList;
	}
}
