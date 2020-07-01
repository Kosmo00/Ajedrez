/**
 * 
 * Esta clase contiene toda la informacion acerca del tablero de ajedrez. Tal como la informacion del
 * movimiento a realizarse y las casillas que este contiene.
 * 
 **/

package tableros;

import casilla.CasillaAjedrez;
import movimiento.Movimiento;

public class Tablero {

	public static final int NUM_FILAS = 8;
	public static final int NUM_COLUMNAS = 8;

	private final CasillaAjedrez[][] casilla;

	private final Movimiento movimiento;

	public Tablero() {

		casilla = new CasillaAjedrez[NUM_FILAS][NUM_COLUMNAS];

		movimiento = new Movimiento(this);

		for (int i = NUM_FILAS - 1; i >= 0; i--) {
			for (int j = NUM_COLUMNAS - 1; j >= 0; j--) {
				casilla[i][j] = new CasillaAjedrez(i, j);
			}
		}
	}

	public CasillaAjedrez getCasilla(final int numFila, final int numColumna) {
		return casilla[numFila][numColumna];
	}

	public Movimiento getMovimiento() {
		return movimiento;
	}
}
