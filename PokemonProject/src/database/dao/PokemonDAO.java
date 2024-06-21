package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonModel;

public class PokemonDAO {
	private String scriptSelect = "select * from tb_pokemon tp"; //onde será implementado o script sql
	private String scriptInsert = "insert into tb_pokemon(pokemon,tipo_pokemon) values (?,?)";
	private String scriptDelete = "delete from tb_pokemon where id_pokemon = (?);";

	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	private PreparedStatement psScriptDelete;

	public PokemonDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
		psScriptDelete = connection.prepareStatement(scriptDelete);
	}

	public boolean delete(PokemonModel pokemon) throws SQLException {
		psScriptDelete.clearParameters();
		psScriptDelete.setInt(1, pokemon.getId()); 
		//psScriptDelete.setString(2, pokemon.getPokemon()); Precisamos passar somente o id
		//psScriptDelete.setString(3, pokemon.getTipo());
		return psScriptDelete.execute();
	}

	public boolean insert(PokemonModel pokemon) throws SQLException {
		psScriptInsert.clearParameters();
		//psScriptInsert.setInt(1, pokemon.getId()); O id é auto increment
		psScriptInsert.setString(1, pokemon.getPokemon());
		psScriptInsert.setString(2, pokemon.getTipo());
		return psScriptInsert.execute();
	}
	//  result.next estava sendo chamado duas vezes então foi alterado para como está abaixo
	//	public ArrayList<PokemonModel> selectAll() throws SQLException{
	//		ArrayList<PokemonModel> pokemonList = new ArrayList<PokemonModel>();
	//		ResultSet result = psScriptSelect.executeQuery();
	//		if (result != null) {
	//			result.first();
	//			while(result.isAfterLast()) {
	//				PokemonModel pm = new PokemonModel();
	//				pm.setId(result.getInt("id_pokemon"));
	//				pm.setPokemon(result.getString("pokemon"));
	//				pm.setTipo(result.getString("tipo_pokemon"));
	//				pokemonList.add(pm);
	//				result.next();
	//			}
	//		}
	//		return pokemonList;
	//	}

	public ArrayList<PokemonModel> selectAll() throws SQLException {
		ArrayList<PokemonModel> pokemonList = new ArrayList<>();
		ResultSet result = psScriptSelect.executeQuery();

		while (result.next()) {
			PokemonModel pm = new PokemonModel();
			pm.setId(result.getInt("id_pokemon"));
			pm.setPokemon(result.getString("pokemon"));
			pm.setTipo(result.getString("tipo_pokemon"));
			pokemonList.add(pm);
		}

		return pokemonList;
	}




}
