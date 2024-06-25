package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonModel;
import database.model.PokemonTotalizadoresModel;
import database.model.PokemonVoadorModel;

public class PokemonTotalizadoresDAO {
	private String scriptSelect = "select * from tb_totalizadores"; //onde será implementado o script sql
	private String scriptInsert = "insert into tb_totalizadores(tot_fogo, tot_voador, tot_eletrico, tot_duplicado) \r\n"
			+ "select\r\n"
			+ "	(select count(id_pokemon_fogo) from tb_pokemon_fogo tpf), \r\n"
			+ "	(select count(id_pokemon_voador) from tb_pokemon_voador tpv),\r\n"
			+ "	(select count(id_pokemon_eletrico) from tb_pokemon_eletrico tpe),\r\n"
			+ "	(select count(id_pokemon_deletado) from tb_pokemon_deletado tpd)\r\n"
			+ ";";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	
	public PokemonTotalizadoresDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
	}
	
	public boolean insert(ArrayList<PokemonModel> pokemonList) throws SQLException {
		psScriptInsert.clearParameters();
//		psScriptInsert.setInt(1, pokemonList.getId());
//		psScriptInsert.setInt(2, pokemonList.getTotalizadorDeletado());
//		psScriptInsert.setInt(3, pokemonList.getTotalizadorEletrico());
//		psScriptInsert.setInt(4, pokemonList.getTotalizadorFogo());
//		psScriptInsert.setInt(5, pokemonList.getTotalizadorVoador());
		return psScriptInsert.execute();
	}
	
	public ArrayList<PokemonTotalizadoresModel> selectAll() throws SQLException{
		ArrayList<PokemonTotalizadoresModel> totPokemonList = new ArrayList<PokemonTotalizadoresModel>();
		ResultSet result = psScriptSelect.executeQuery();
		while(result.next()) {
			PokemonTotalizadoresModel ptm = new PokemonTotalizadoresModel();
			ptm.setId(result.getInt("id_totalizadores"));
			ptm.setTotalizadorDeletado(result.getInt("tot_duplicado"));
			ptm.setTotalizadorEletrico(result.getInt("tot_eletrico"));
			ptm.setTotalizadorFogo(result.getInt("tot_fogo"));
			ptm.setTotalizadorVoador(result.getInt("tot_voador"));
			totPokemonList.add(ptm);
		}
		return totPokemonList;
	}
}
