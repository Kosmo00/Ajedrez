/**
 * 
 * Esta clase esta destinada a la realizacion de los movimientos y la creacion de la notacion a partir de estos
 * y viceversa, ademas, posee una clase interna que es la encargada de verificar el estado de las posiciones
 * alcanzadas y los posiciones alcalzables, juzgando estas de acuerdo a su legalidad de acuerdo a las reglas del
 * ajedrez
 * 
 */

package movimiento;

import java.awt.Toolkit;

import javax.swing.JOptionPane;

import casilla.CasillaAjedrez;
import posicion.PosicionAjedrez;
import tableros.Tablero;

public class Movimiento {

	private final ValidaMovimientos reglas;

	private CasillaAjedrez casillaAnterior;
	private CasillaAjedrez casillaSiguiente;

	private int turno = 1;
	private int numMovimiento;

	private String notacion = "";

	/*************************************************************************************************
	 * 
	 * Constructores
	 * 
	 *************************************************************************************************/

	public Movimiento(Tablero tablero) {
		numMovimiento = 1;
		reglas = new ValidaMovimientos(tablero);
	}

	// Para una posicion configurada con un numero de movimiento diferente al 1
	public Movimiento(Tablero tablero, int movimiento) {
		numMovimiento = movimiento;
		reglas = new ValidaMovimientos(tablero);
	}

	/*************************************************************************************************
	 * 
	 * Funciones destinadas al movimiento de las piezas
	 * 
	 *************************************************************************************************/

	// Esta funcion selecciona una pieza, en caso de que ya una pieza ha sido
	// seleccionada pasa a ejecutar el movimiento.
	public void seleccionPiezaAMover(CasillaAjedrez casillaPieza) {

		if (casillaPieza.getPieza() != null) {
			if (casillaPieza.getPieza().getBando() == turno) {
				reglas.limpiarMovimientosLegales();
				casillaAnterior = casillaPieza;
				reglas.comprobarMovimientosPieza(casillaAnterior);
				return;
			}
		}
		if (casillaPieza.getMovimientoLegal()) {
			casillaSiguiente = casillaPieza;
			realizarMovimiento();
		}
		casillaAnterior = null;
		casillaSiguiente = null;
	}

	// Funcion que ejecuta el movimiento y actualiza el estado de la posicion
	private void realizarMovimiento() {

		reglas.quitarJaque();

		notacion += numMovimiento;
		if (turno == 1) {
			notacion += "-";
		} else {
			notacion += ".";
		}
		notacion += casillaAnterior.getNombre() + "-" + casillaSiguiente.getNombre() + " ";

		casillaAnterior.getFabrica().moved();

		casillaSiguiente.setPieza(casillaAnterior.getPieza());
		casillaAnterior.setPieza(null);
		reglas.ComprobarJaque();

		cambiarTurno();
		if (turno == 1) {
			numMovimiento++;
		}
		reglas.limpiarMovimientosLegales();

	}

	public void comprobarEstado() {
		Toolkit tool = Toolkit.getDefaultToolkit();
		if (reglas.comprobarMovimientosLegales() == false) {
			tool.beep();
			JOptionPane.showMessageDialog(null, "Jaque Mate", "Jaque Mate", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		if (casillaAnterior == null) {
			reglas.limpiarMovimientosLegales();
		}
	}

	/*************************************************************************************************
	 * 
	 * Trabajo con el turno.
	 * 
	 *************************************************************************************************/

	public void setTurno(final int turno) {
		this.turno = turno;
	}

	private void cambiarTurno() {
		turno *= -1;
	}

	/*************************************************************************************************
	 * 
	 * funciones getter
	 * 
	 *************************************************************************************************/

	public String getNotacion() {
		return notacion;
	}

	public int getTurno() {
		return turno;
	}

	/**
	 * @author Kosmo
	 * 
	 *         Clase Interna. A esta clase se le atribuye la responsabilidad de
	 *         validar las posiciones y movimientos legales ya sea a realizarse o
	 *         realizados.
	 * 
	 **/

	final class ValidaMovimientos {

		private final Tablero tablero;

		private boolean movimientosPiezaSeleccionada = false;

		public ValidaMovimientos(final Tablero tablero) {
			this.tablero = tablero;
		}

		// Verifica si encierta posicion un rey se encuentra en jaque
		public boolean ComprobarJaque() {
			return ComprobarJaque(this.tablero);
		}

		private boolean ComprobarJaque(Tablero tablero) {

			// bucle que genera los movimientos legales del otro bando
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (getCasilla(i, j, tablero).getPieza() != null) {
						if (getCasilla(i, j, tablero).getPieza().getBando() == tablero.getMovimiento().getTurno()) {
							getCasilla(i, j, tablero).getPieza().movimientosLegales(i, j);

						}
					}
				}
			}
			// bucle que se encarga de señalar los jaques
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (getCasilla(i, j, tablero).getPieza() != null) {
						if (getCasilla(i, j, tablero).getMovimientoLegal()
								&& getCasilla(i, j, tablero).getPieza().getNombrePieza() == "Rey"
								&& getCasilla(i, j, tablero).getPieza().getBando() != tablero.getMovimiento()
										.getTurno()) {
							tablero.getCasilla(i, j).getFabrica().ponerJaque();
							return true;
						}
					}
				}
			}
			return false;
		}

		// funcion que se encarga de comprobar la existencia de algun movimiento legal,
		// para asi declarar la partida como jaque mate, ahogado o si se puede continuar
		// esta
		public boolean comprobarMovimientosLegales() {

			for (int filaAnt = 0; filaAnt < 8; filaAnt++) {
				for (int columnaAnt = 0; columnaAnt < 8; columnaAnt++) {
					if (tablero.getCasilla(filaAnt, columnaAnt).getPieza() != null) {
						if (tablero.getCasilla(filaAnt, columnaAnt).getPieza().getBando() == getTurno()) {
							comprobarMovimientosPieza(getCasilla(filaAnt, columnaAnt));
							if (movimientosPiezaSeleccionada) {
								return true;
							}
						}
					}
				}
			}

			return false;
		}

		// Funcion que se encarga de comprobar la legalidad de los movimientos posibles
		// de una pieza seleccionada y validarlos
		public void comprobarMovimientosPieza(CasillaAjedrez casilla) {
			movimientosPiezaSeleccionada = false;
			casilla.getPieza().movimientosLegales(casilla.getFila(), casilla.getColumna());
			comprobarLegalidadMovimiento(casilla.getFila(), casilla.getColumna(), casilla);
		}

		// Funcion que comprueba la legalidad de los movimientos de una pieza
		// seleccionada
		private void comprobarLegalidadMovimiento(int filaCasilla, int columnaCasilla, CasillaAjedrez casilla) {
			for (int filaActual = 0; filaActual < 8; filaActual++) {
				for (int columnaActual = 0; columnaActual < 8; columnaActual++) {
					if (getCasilla(filaActual, columnaActual).getMovimientoLegal() == true) {
						Tablero tableroMR = tableroCopia();
						simularMovimiento(filaCasilla, columnaCasilla, filaActual, columnaActual, tableroMR);

						if (ComprobarJaque(tableroMR)) {
							getCasilla(filaActual, columnaActual).setMovimientoLegal(false);
						} else if (!movimientosPiezaSeleccionada) {
							movimientosPiezaSeleccionada = true;
						}
					}
				}
			}
		}

		// Funcion para crear un tablero virtual
		private Tablero tableroCopia() {
			PosicionAjedrez posicionNueva = new PosicionAjedrez(tablero);

			Tablero tableroCopia = posicionNueva.getTablero();
			return tableroCopia;
		}

		// Funciones para realizar movimientos en tableros virtuales
		public void simularMovimiento(int filaAnt, int columnaAnt, int filaNueva, int columnaNueva, Tablero tableroMR) {
			tableroMR.getCasilla(filaNueva, columnaNueva)
					.setPieza(tableroMR.getCasilla(filaAnt, columnaAnt).getPieza());
			tableroMR.getCasilla(filaAnt, columnaAnt).setPieza(null);
			tableroMR.getMovimiento().cambiarTurno();
		}

		// Funciones para agilizar el llamado a casillas de un tablero.
		private CasillaAjedrez getCasilla(int fila, int columna) {
			return getCasilla(fila, columna, tablero);
		}

		private CasillaAjedrez getCasilla(int fila, int columna, Tablero tablero) {
			return tablero.getCasilla(fila, columna);
		}

		// Funcion que elimina la legalidad de los movimientos de casillas seleccionadas
		// anteriormente para evitar que todo movimiento en el tablero sea legal
		public void limpiarMovimientosLegales() {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					getCasilla(i, j).setMovimientoLegal(false);
				}
			}
		}

		public void quitarJaque() {
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					getCasilla(i, j).getFabrica().quitarJaque();
				}
			}
		}
	}

}
