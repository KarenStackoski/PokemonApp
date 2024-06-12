package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonDeletadoModel;

public class PokemonDeletadoDAO {
	private String script = ""; //onde será implementado o script sql
	private PreparedStatement psScript;
	
	public PokemonDeletadoDAO(Connection connection) throws SQLException {
		psScript = connection.prepareStatement(script);
	}
	
	public ArrayList<PokemonDeletadoModel> selectAll() throws SQLException{
		ArrayList<PokemonDeletadoModel> deletedPokemonList = new ArrayList<PokemonDeletadoModel>();
		ResultSet result = psScript.executeQuery();
		if (result != null) {
			result.first();
			while(result.isAfterLast()) {
				PokemonDeletadoModel pdm = new PokemonDeletadoModel();
				pdm.setId(result.getInt("id_pokemon_deletado"));
				pdm.setPokemonDeletado(result.getString("pokemon_deletado"));
				pdm.setTipoPokemonDeletado(result.getString("tipo_pokemon_deletado"));
				deletedPokemonList.add(pdm);
				result.next();
			}
		}
		return deletedPokemonList;
	}
}
