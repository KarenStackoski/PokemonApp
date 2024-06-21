package main;

import database.ConnectionFactory;
import database.dao.PokemonDAO;
import database.dao.PokemonDeletadoDAO;
import database.dao.PokemonEletricoDAO;
import database.dao.PokemonFogoDAO;
import database.dao.PokemonTotalizadoresDAO;
import database.dao.PokemonVoadorDAO;
import database.model.PokemonDeletadoModel;
import database.model.PokemonEletricoModel;
import database.model.PokemonFogoModel;
import database.model.PokemonModel;
import database.model.PokemonVoadorModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            PokemonDAO pokemonDAO = new PokemonDAO(connection);
            PokemonDeletadoDAO deletadoDAO = new PokemonDeletadoDAO(connection);
            PokemonDeletadoModel deletadoModel = new PokemonDeletadoModel();
            
            String[][] pokemons = {
                {"picachu", "eletrico"},
                {"miraidon", "eletrico"},
                {"charmander", "fogo"},
                {"fuecoco", "fogo"},
                {"miraidon", "eletrico"},
                {"pidgeotto", "voador"},
                {"butterfree", "voador"},
                {"butterfree", "voador"},
                {"fuecoco", "fogo"}
            };
            
            //Adiciona os dados previamente digitados na String
            for (String[] p : pokemons) {
            	String pokemonName = StringUtils.removeAccents(p[0]);
            	String pokemonType = StringUtils.removeAccents(p[1]);
            	PokemonModel pokemon = new PokemonModel();
            	pokemon.setPokemon(pokemonName);
            	pokemon.setTipo(pokemonType);
            	pokemonDAO.insert(pokemon);
            }
            //Cria o ArrayList da tabela princípal dos pokemons
            ArrayList<PokemonModel> pokemonList = pokemonDAO.selectAll();
            
            //O For aqui adiciona cada um no seu tipo, mantendo as tabelas organizadas
            for (PokemonModel pokemon : pokemonList) {
                switch (pokemon.getTipo().toLowerCase()) {
                    case "voador":
                        PokemonVoadorModel voadorPokemon = new PokemonVoadorModel();
                        voadorPokemon.setId(pokemon.getId());
                        voadorPokemon.setPokemonVoador(pokemon.getPokemon());

                        PokemonVoadorDAO voadorDAO = new PokemonVoadorDAO(connection);
                        voadorDAO.insert(voadorPokemon);
                        break;
                    case "eletrico":
                        PokemonEletricoModel eletricoPokemon = new PokemonEletricoModel();
                        eletricoPokemon.setId(pokemon.getId());
                        eletricoPokemon.setPokemonEletrico(pokemon.getPokemon());

                        PokemonEletricoDAO eletricoDAO = new PokemonEletricoDAO(connection);
                        eletricoDAO.insert(eletricoPokemon);
                        break;
                    case "fogo":
                        PokemonFogoModel fogoPokemon = new PokemonFogoModel();
                        fogoPokemon.setId(pokemon.getId());
                        fogoPokemon.setPokemonFogo(pokemon.getPokemon());

                        PokemonFogoDAO fogoDAO = new PokemonFogoDAO(connection);
                        fogoDAO.insert(fogoPokemon);
                        break;
                    default:
                        System.out.println("Tipo de Pokémon não reconhecido: " + pokemon.getTipo());
                }
            }
            
             
              
            //Responsável por validar a existencia de pokemons duplicados na tabela princípal
            Set<String> set = new HashSet<>();   
            for(PokemonModel lista: pokemonList) {
                if(!set.add(lista.getPokemon())) {
                	deletadoModel.setPokemonDeletado(lista.getPokemon());
                	deletadoModel.setTipoPokemonDeletado(lista.getTipo());
                	deletadoDAO.insert(deletadoModel);
                	deletadoDAO.delete(lista.getId());
                }
            }
            
            //Roda o SQL direto no banco, onde roda o count dos tipos de pokemons e adiciona eles nas tabelas
            PokemonTotalizadoresDAO totalDAO = new PokemonTotalizadoresDAO(connection);    
            totalDAO.insert(pokemonList);   
        }catch (Exception e) {
			System.out.println("!!Programa não execultado!!");
		}
	}
}   
        

