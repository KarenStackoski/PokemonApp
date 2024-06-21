package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonEletricoModel;

public class PokemonEletricoDAO {
	private String scriptSelect = "select * from tb_pokemon_eletrico"; //onde será implementado o script sql
	private String scriptInsert = "insert into tb_pokemon_eletrico (id_pokemon_eletrico, pokemon_eletrico) values (?,?)";
    private String scriptExists = "SELECT 1 FROM tb_pokemon_eletrico WHERE pokemon_eletrico = ?";

	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
    private PreparedStatement psScriptExists;

	
	public PokemonEletricoDAO(Connection connection) throws SQLException {
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
	
	public boolean insert(PokemonEletricoModel eletricoPokemon) throws SQLException {
		if (!pokemonExists(eletricoPokemon.getPokemonEletrico())) {
            psScriptInsert.clearParameters();
            psScriptInsert.setInt(1, eletricoPokemon.getId());
            psScriptInsert.setString(2, eletricoPokemon.getPokemonEletrico());
            return psScriptInsert.execute();
        } else {
            System.out.println("Este Pokémon já existe na tabela de Pokémon elétricos.");
            return false;
        }
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
