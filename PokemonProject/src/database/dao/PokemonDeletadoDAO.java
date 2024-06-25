package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectionFactory;
import database.model.PokemonDeletadoModel;
import database.model.PokemonModel;

public class PokemonDeletadoDAO {
	private String scriptDelete = "delete from tb_pokemon where id_pokemon = ?;";
	private String scriptSelect = "select * from tb_pokemon_deletado"; //onde será implementado o script sql
	private String scriptInsert = "insert into tb_pokemon_deletado  (pokemon_deletado, tipo_pokemon_deletado) values (?,?)";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	private PreparedStatement psScriptDelete;
	ResultSet resultset = null;
	
	public PokemonDeletadoDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
		psScriptDelete = connection.prepareStatement(scriptDelete);
	}

	public boolean insert(PokemonDeletadoModel deletedPokemon) throws SQLException {
		psScriptInsert.clearParameters();
		//psScriptInsert.setInt(1, deletedPokemon.getId()); Comentado porque não precisa inserir o id
		psScriptInsert.setString(1, deletedPokemon.getPokemonDeletado());
		psScriptInsert.setString(2, deletedPokemon.getTipoPokemonDeletado());
		return psScriptInsert.execute();
	}
	
	public ArrayList<PokemonDeletadoModel> selectAll() throws SQLException{
		ArrayList<PokemonDeletadoModel> deletedPokemonList = new ArrayList<PokemonDeletadoModel>();
		ResultSet result = psScriptSelect.executeQuery();
		
		while(result.next()) {
			PokemonDeletadoModel pdm = new PokemonDeletadoModel();
			pdm.setId(result.getInt("id_pokemon_deletado"));
			pdm.setPokemonDeletado(result.getString("pokemon_deletado"));
			pdm.setTipoPokemonDeletado(result.getString("tipo_pokemon_deletado"));
			deletedPokemonList.add(pdm);
		}
		return deletedPokemonList;
	}
	
	public void delete(int ids) throws SQLException {
	        psScriptDelete.setInt(1, ids);
	        psScriptDelete.execute();
	    }
	}

