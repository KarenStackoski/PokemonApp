package front;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

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
import database.model.PokemonTotalizadoresModel;
import database.model.PokemonVoadorModel;

public class TestPokemon {
	private PokemonDAO pokemon;
	private PokemonDeletadoDAO deletedPokemon;
	private PokemonEletricoDAO eletricPokemon;
	private PokemonFogoDAO firePokemon;
	private PokemonVoadorDAO flyingPokemon;
	private PokemonTotalizadoresDAO totPokemon;
	
	public void DisplayAllPokemons() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			this.pokemon = new PokemonDAO(connection);
			ArrayList<PokemonModel> pokemons = pokemon.selectAll();
			for(PokemonModel pokemon : pokemons) {
				System.out.println(pokemon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayAllDeletedPokemons() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			this.deletedPokemon = new PokemonDeletadoDAO(connection);
			ArrayList<PokemonDeletadoModel> deletedPokemons = deletedPokemon.selectAll();
			for (PokemonDeletadoModel deletedPokemon : deletedPokemons) {
				System.out.println(deletedPokemon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayAllEletricPokemons() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			this.eletricPokemon = new PokemonEletricoDAO(connection);
			ArrayList<PokemonEletricoModel> eletricPokemons = eletricPokemon.selectAll();
			for (PokemonEletricoModel eletricPokemon : eletricPokemons) {
				System.out.println(eletricPokemon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayAllFirePokemons() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			this.firePokemon = new PokemonFogoDAO(connection);
			ArrayList<PokemonFogoModel> firePokemons = firePokemon.selectAll();
			for (PokemonFogoModel firePokemon : firePokemons) {
				System.out.println(firePokemon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayAllFlyingPokemons() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			this.flyingPokemon = new PokemonVoadorDAO(connection);
			ArrayList<PokemonVoadorModel> flyingPokemons = flyingPokemon.selectAll();
			for (PokemonVoadorModel flyingPokemon : flyingPokemons) {
				System.out.println(flyingPokemon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayAllTotPokemons() {
		try (Connection connection = ConnectionFactory.getConnection()) {
			this.totPokemon = new PokemonTotalizadoresDAO(connection);
			ArrayList<PokemonTotalizadoresModel> totPokemons = totPokemon.selectAll();
			for (PokemonTotalizadoresModel totPokemon : totPokemons) {
				System.out.println(totPokemon);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void DisplayMenu() {
		Scanner scanner = new Scanner(System.in);
		int option;
		
		do {
			System.out.println("Ola usuario!\n\n" + 
					   "Selecione algumas das opcoes abaixo para proseguir:\n" +
					   "[1] Mostrar Pokemons\n" +
					   "[2] Mostrar Pokemons de Fogo\n" +
					   "[3] Mostrar Pokemons Voadores\n" +
					   "[4] Mostrar Pokemons Eletricos\\n" +
					   "[5] Encerrar\n\n" +
					   " >>> ");
			option = scanner.nextInt();
			
			switch(option) {
			case 1:
				DisplayAllPokemons();
				break;
			case 2:
				DisplayAllFirePokemons();
				break;
			case 3:
				DisplayAllFlyingPokemons();
				break;
			case 4:
				DisplayAllEletricPokemons();
				break;
			case 5:
				System.out.println("Encerrando...");
				break;
			default:
				System.out.println("Opcao invalida!");
				break;
			}
		} while (option != 5);
		
		scanner.close();
	}
	
	public static void main(String[] args) {
        TestPokemon test = new TestPokemon();
        test.DisplayMenu();
    }
}