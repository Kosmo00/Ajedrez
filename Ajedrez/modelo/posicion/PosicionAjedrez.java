/****************************************************************************************************************************
 * 
 * @author Kosmo
 * 
 * @date
 * 20/04/2020 23:40
 * 
 * @description
 * El objetivo de esta clase reside en facilitar y validar la configuracion manual  de posiciones con ayuda del editor de 
 * posiciones. En esta no se validan las posiciones que excedan el limite reglamentario de piezas o posiciones ilegales o
 * terminales. Esta va almacenando dinamicamente un arreglo dinamico de casillas que seguidamente se cuentan.
 * Otros objetivos que persigue esta clase es comprobar los jaques, mate y ahogado que ocurran durante el juego.
 * Esta clase recibe como parametros posiciones escritas como un String, por tanto es capaz de recibir posiciones de ficheros.
 * Configura la posicion inicial.
 * 
 ****************************************************************************************************************************/

package posicion;

import pieza.piezaAjedrez.Alfil;
import pieza.piezaAjedrez.Caballo;
import pieza.piezaAjedrez.Dama;
import pieza.piezaAjedrez.Desplazamientos;
import pieza.piezaAjedrez.Peon;
import pieza.piezaAjedrez.Rey;
import pieza.piezaAjedrez.Torre;
import tableros.Tablero;

public class PosicionAjedrez {

	private final Tablero tablero;
	private final Desplazamientos desplazamiento;

	/************************************************************************************************************************
	 * 
	 * /Constructores
	 * 
	 ************************************************************************************************************************/

	public PosicionAjedrez() {// Constructor Predeterminado usado principalmente para la posicion inicial
		tablero = new Tablero();
		desplazamiento = new Desplazamientos(tablero);
	}

	public PosicionAjedrez(final String PosicionesPiezas) {// Constructor desde una fuente externa

		tablero = new Tablero();
		desplazamiento = new Desplazamientos(tablero);
	}

	public PosicionAjedrez(final Tablero tablero) {

		this.tablero = new Tablero();

		desplazamiento = new Desplazamientos(this.tablero);

		this.tablero.getMovimiento().setTurno(tablero.getMovimiento().getTurno());

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tablero.getCasilla(i, j).getPieza() != null) {
					this.tablero.getCasilla(i, j).setPieza(tablero.getCasilla(i, j).getPieza().clonar(desplazamiento));
				}
			}
		}
	}

	/************************************************************************************************************************
	 * 
	 * Funciones modificadoras
	 * 
	 ************************************************************************************************************************/

	/************************************************************************************************************************
	 * 
	 * Funcion para añadir posiciones a un tablero
	 * 
	 ************************************************************************************************************************/

	private boolean cuentaPiezas() {

		return true;
	}

	public void actualizaTablero(Tablero tableroDeJuego) {
		if (cuentaPiezas()) {

		}
	}

	// Configura la posicion inicial.
	public Tablero posicionInicial() {
		tablero.getCasilla(0, 0).setPieza(new Torre(1, desplazamiento));
		tablero.getCasilla(0, 7).setPieza(new Torre(1, desplazamiento));
		tablero.getCasilla(7, 0).setPieza(new Torre(-1, desplazamiento));
		tablero.getCasilla(7, 7).setPieza(new Torre(-1, desplazamiento));
		tablero.getCasilla(0, 1).setPieza(new Caballo(1, desplazamiento));
		tablero.getCasilla(0, 6).setPieza(new Caballo(1, desplazamiento));
		tablero.getCasilla(7, 1).setPieza(new Caballo(-1, desplazamiento));
		tablero.getCasilla(7, 6).setPieza(new Caballo(-1, desplazamiento));
		tablero.getCasilla(0, 2).setPieza(new Alfil(1, desplazamiento));
		tablero.getCasilla(0, 5).setPieza(new Alfil(1, desplazamiento));
		tablero.getCasilla(7, 2).setPieza(new Alfil(-1, desplazamiento));
		tablero.getCasilla(7, 5).setPieza(new Alfil(-1, desplazamiento));
		tablero.getCasilla(0, 4).setPieza(new Dama(1, desplazamiento));
		tablero.getCasilla(0, 3).setPieza(new Rey(1, desplazamiento));
		tablero.getCasilla(7, 4).setPieza(new Dama(-1, desplazamiento));
		tablero.getCasilla(7, 3).setPieza(new Rey(-1, desplazamiento));

		for (int i = 0; i < 8; i++) {
			tablero.getCasilla(1, i).setPieza(new Peon(1, desplazamiento));
			tablero.getCasilla(6, i).setPieza(new Peon(-1, desplazamiento));
		}
		return tablero;
	}

	public Tablero getTablero() {
		return tablero;
	}

}
