package SimonDiceV3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author emi
 */
public class Record {
	private final int MAX_JUGADORES= 10; 
	private Jugador[] players;
	private int cont;
	
	//CONSTRUCTORA QUE INICIALIZA LOS ATRIBUTOS
	/**
	 * Record constructora que inicializa los atributos
	 */
	public Record() {
		this.players = new Jugador[MAX_JUGADORES];
		this.cont = 0;
	}
	/**
	 * addPlayer metodo que se encarga de aniadir jugador al array players
	 * complejidad lineal
	 * @param player
	 */
	public void addPlayer(Jugador player) {
		//this.
		if (cont < MAX_JUGADORES) {
			this.players[cont] = player;
			
			this.cont++;
		}
		
	}
	/**
	 * getJugadorByName metodo que recibe por parametro un string y lo convierte en jugador, devolviendo un jugador
	 * @param _nombre
	 * @return
	 */
	public String getJugadorByName(String _nombre) {
		int stoper = 0;
		if (stoper < MAX_JUGADORES) {
			stoper = cont;
		}else {
			stoper = MAX_JUGADORES;
		}
		//stoper
		int i = 0;
		while (this.players[i].getNombre() != _nombre && i < stoper) {
			//equals
			if (this.players[i].getNombre().equals(_nombre)) {
				return this.players[i].getNombre();
			}
			
			i++;
		}
		return null;
	}
	/**
	 * ordenarRanking metodo que se encarga de ordenar el array players
	 * Complejidad del metodo 0(n2)
	 */
	public void ordenarRanking() {
		int stoper = 0;
		if (stoper < MAX_JUGADORES) {
			stoper = cont;
		}else {
			stoper = MAX_JUGADORES;
		}
		
	        for (int i = 0; i < stoper - 1; i++) {
	            for (int h = 0; h < stoper - i - 1; h++) {
	                if (this.players[h + 1].getScore() > this.players[h].getScore()) {
	                    Jugador saver = this.players[h + 1];
	                    this.players[h + 1] = this.players[h];
	                    this.players[h] = saver;
	                }
	            }
	        }
	}
	/**
	 * showRancking metodo que ordena los jugadores, acto seguido imprime los jugadores existentes (hasta 10), en pantalla al ser pedido
	 */
	public void showRancking() {
		ordenarRanking();
		System.out.println("TOP 10 PLAYERS RANKING");
		System.out.println("----------------------");
		
		for (int i = 0; i < cont; i++) {
	        System.out.println((i + 1) + "." + " " + this.players[i].getNombre() + ": " + this.players[i].getScore());
	    }
	}
	/**
	 * showBestPlayer metodo que ordena los jugadores, acto seguido elige el primer jugador de la lista y lo imprime
	 */
	public void showBestPlayer() {
		ordenarRanking();
		System.out.println("TOP 1 PLAYER - HIGH SCORE");
		System.out.println("-------------------------");
		
		int max = this.players[0].getScore();
		int stoper = 0;
		if (stoper < MAX_JUGADORES) {
			stoper = cont;
		}else {
			stoper = MAX_JUGADORES;
		}
		
		int i = 0;
		
		do {
			System.out.println(1 + "." + " " + this.players[i].getNombre() + ": " + this.players[i].getScore());
			
			i++;
		}while((i < stoper && this.players[i].getScore() == max));
	}
	
	public void cargarRancking() {
		File file = new File("src/Data1/best_players.txt");
		
		 try {
			 CustomReadFile dwnFile = new CustomReadFile(file);
			 //dwnFile = new CustomReadFile(new File("src/Data1/best_players.txt"));
		     ArrayList<Jugador> rankingFromFile = dwnFile.leerJugadores();
		    } catch (IOException e) {
		        //e.printStackTrace();
		    	System.out.print("Expecion en el metodo cargarRancking de la clase Record");
		    }
		
	}
	
	
	public void escribirRancking() throws IOException {
		File file = new File("src/Data1/best_players");
		
		int stoper = 0;
		if (stoper < MAX_JUGADORES) {
			stoper = cont;
		}else {
			stoper = MAX_JUGADORES;
		}
		int i = 0;
		
		//escribirJugadores();
		try {
			CustomWriteFile editFile = new CustomWriteFile(file);
			
			do {
				editFile.escribirJugadores(players[i].getNombre());
				
			} while (i < stoper && players[i] != null);
			editFile.closeFile();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Se ha producido una exepción en el metodo escribirRancking() de la clase Record");
		}
	}
}
