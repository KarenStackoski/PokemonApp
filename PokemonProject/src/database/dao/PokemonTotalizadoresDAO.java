package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonTotalizadoresModel;
import database.model.PokemonVoadorModel;

public class PokemonTotalizadoresDAO {
	private String scriptSelect = ""; //onde será implementado o script sql
	private String scriptInsert = "";
	private PreparedStatement psScriptSelect;
	private PreparedStatement psScriptInsert;
	
	public PokemonTotalizadoresDAO(Connection connection) throws SQLException {
		psScriptSelect = connection.prepareStatement(scriptSelect);
		psScriptInsert = connection.prepareStatement(scriptInsert);
	}
	
	public boolean insert(PokemonTotalizadoresModel totPokemon) throws SQLException {
		psScriptInsert.clearParameters();
		psScriptInsert.setInt(1, totPokemon.getId());
		psScriptInsert.setInt(2, totPokemon.getTotalizadorDeletado());
		psScriptInsert.setInt(3, totPokemon.getTotalizadorEletrico());
		psScriptInsert.setInt(4, totPokemon.getTotalizadorFogo());
		psScriptInsert.setInt(5, totPokemon.getTotalizadorVoador());
		return psScriptInsert.execute();
	}
	
	public ArrayList<PokemonTotalizadoresModel> selectAll() throws SQLException{
		ArrayList<PokemonTotalizadoresModel> totPokemonList = new ArrayList<PokemonTotalizadoresModel>();
		ResultSet result = psScriptSelect.executeQuery();
		if (result != null) {
			result.first();
			while(result.isAfterLast()) {
				PokemonTotalizadoresModel ptm = new PokemonTotalizadoresModel();
				ptm.setId(result.getInt("id_totalizadores"));
				ptm.setTotalizadorDeletado(result.getInt("tot_duplicado"));
				ptm.setTotalizadorEletrico(result.getInt("tot_eletrico"));
				ptm.setTotalizadorFogo(result.getInt("tot_fogo"));
				ptm.setTotalizadorVoador(result.getInt("tot_voador"));
				totPokemonList.add(ptm);
				result.next();
			}
		}
		return totPokemonList;
	}
}
