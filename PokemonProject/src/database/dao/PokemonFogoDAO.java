package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonFogoModel;

public class PokemonFogoDAO {
	private String scriptSelect = "select * from tb_pokemon_fogo"; //onde será implementado o script sql
	private String scriptInsert = "insert into tb_pokemon_fogo  (id_pokemon_fogo, pokemon_fogo) values (?,?)";
    private String scriptExists = "SELECT 1 FROM tb_pokemon_fogo WHERE pokemon_fogo = ?";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
    private PreparedStatement psScriptExists;

	public PokemonFogoDAO(Connection connection) throws SQLException {
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
	
	public boolean insert(PokemonFogoModel fogoPokemon) throws SQLException {
		if (!pokemonExists(fogoPokemon.getPokemonFogo())) {
            psScriptInsert.clearParameters();
            psScriptInsert.setInt(1, fogoPokemon.getId());
            psScriptInsert.setString(2, fogoPokemon.getPokemonFogo());
            return psScriptInsert.execute();
        } else {
            System.out.println("Este Pokémon já existe na tabela de Pokémon de fogo.");
            return false;
        }
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
