package database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.model.PokemonTotalizadoresModel;

public class PokemonTotalizadoresDAO {
	private String script = ""; //onde será implementado o script sql
	private PreparedStatement psScript;
	
	public PokemonTotalizadoresDAO(Connection connection) throws SQLException {
		psScript = connection.prepareStatement(script);
	}
	
	public ArrayList<PokemonTotalizadoresModel> selectAll() throws SQLException{
		ArrayList<PokemonTotalizadoresModel> totPokemonList = new ArrayList<PokemonTotalizadoresModel>();
		ResultSet result = psScript.executeQuery();
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
