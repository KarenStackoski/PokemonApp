package main;

import database.ConnectionFactory;
import database.dao.PokemonDAO;
import database.dao.PokemonEletricoDAO;
import database.dao.PokemonFogoDAO;
import database.dao.PokemonTotalizadoresDAO;
import database.dao.PokemonVoadorDAO;
import database.model.PokemonEletricoModel;
import database.model.PokemonFogoModel;
import database.model.PokemonModel;
import database.model.PokemonVoadorModel;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionFactory.getConnection();
            PokemonDAO pokemonDAO = new PokemonDAO(connection);

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

            for (String[] p : pokemons) {
            	String pokemonName = StringUtils.removeAccents(p[0]);
            	String pokemonType = StringUtils.removeAccents(p[1]);
            	PokemonModel pokemon = new PokemonModel();
            	pokemon.setPokemon(pokemonName);
            	pokemon.setTipo(pokemonType);
            	pokemonDAO.insert(pokemon);

            }

            ArrayList<PokemonModel> pokemonList = pokemonDAO.selectAll();
            
            PokemonTotalizadoresDAO totalDAO = new PokemonTotalizadoresDAO(connection);
            
            totalDAO.insert(pokemonList);

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

            System.out.println("Dados inseridos com sucesso!");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.closeConnection(connection);
        }
        
    }
}
