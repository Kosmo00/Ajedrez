/*******************************************************************************************************************************
 * 
 * @author Kosmo
 * 
 * @date
 * 22/04/2020 14:25
 * 
 * @description:
 * El objetivo de esta clase radica en imitar las funciones friend globales de las clases de C++. Debido a que las funciones 
 * desplazamientoDiagonal y desplazamientoColumna pueden ser utilizadas en mas de una clase, se declaran globales dentro de la
 * visibilidad del package y se llaman desde donde se neesiten dentro del package. Esta clase contiene todos los tipos de
 * movimientos existentes en el ajedrez y se llaman sus funciones para comprobar tanto las casillas controladas de una posicion
 * como los legales de esta.
 * 
 * OJO: En esta clase no se utilizan bloques try-catch debido a que las condicionales reflejan en mayor medida la naturaleza de
 * resolucion del problema como algoritmo. Debido a que este algoritmo no esta optimizado, las condicionales reflejan la 
 * naturaleza del problema para futuras optimizaciones. En proximas persiones, cuando el algoritmo se optimice, se reemplazaran
 * las condicionales por sus respectivos bloques try-catch(en los casos necesarios).
 * 
 * Esta clase es un enlace con la clase tablero.
 * 
 *******************************************************************************************************************************/

package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;
import tableros.Tablero;

final public class Desplazamientos {

	private final Tablero tablero;
	private int bando;// esta variable se hace necesaria a la hora de hacer llamados a funciones
						// internas, pero no es estrictamente necesaria, solo reduce la cantidad de
						// parametros.

	public Desplazamientos(final Tablero tablero) {
		this.tablero = tablero;
	}

	public void desplazamientoDiagonal(final int filaPieza, final int columnaPieza, final int bando) {

		this.bando = bando;
		// Se realizan los bucles en las 4 direcciones, debido a que en cada direccion
		// se comprueba la existencia de piezas en las diferentes casillas y se terminan
		// los bucles al momento en que se encuentra una.
		for (int j = 0; j < 2; j++) {
			int direccionFila;
			if (j % 2 == 0) {
				direccionFila = 1;
			} else {
				direccionFila = -1;
			}
			for (int k = 0; k < 2; k++) {
				int direccionColumna;
				if (k % 2 == 0) {
					direccionColumna = 1;
				} else {
					direccionColumna = -1;
				} // Hasta aqui se preparan las cuatro diagonales que sa van a recorrer

				for (int i = 1; i < 8; i++) {
					if (filaPieza + i * direccionFila < 8 && columnaPieza + i * direccionColumna < 8
							&& filaPieza + i * direccionFila >= 0 && columnaPieza + i * direccionColumna >= 0) {
						// Conjunto de condiciones que deben cumplirse para no salir de la matriz
						legalizarCasilla(filaPieza + i * direccionFila, columnaPieza + i * direccionColumna);

						if (piezaEnCasilla(filaPieza + i * direccionFila,
								columnaPieza + i * direccionColumna) != null) {
							break;
						}
					}
				}
			}
		}
	}

	public void desplazamientoColumnaFila(final int filaPieza, final int columnaPieza, final int bando) {

		this.bando = bando;

		for (int j = 0; j < 4; j++) {// se itera en las cuatro direcciones.
			int direccion;
			if (j % 2 == 0)
				direccion = 1;
			else
				direccion = -1;
			for (int i = 1; i < 8; i++) {
				if (j < 2) {// Se realiza la fila primero
					if (filaPieza + i * direccion < 8 && filaPieza + i * direccion >= 0) {// condiciones para no salir
																							// de la matriz
						legalizarCasilla(filaPieza + i * direccion, columnaPieza);

						if (piezaEnCasilla(filaPieza + i * direccion, columnaPieza) != null) {
							break;
						}
					}

				} else {// luego se itera en la columna y se repiten las condiciones anteriores para las
						// columnas
					if (columnaPieza + i * direccion < 8 && columnaPieza + i * direccion >= 0) {
						legalizarCasilla(filaPieza, columnaPieza + i * direccion);
						if (piezaEnCasilla(filaPieza, columnaPieza + i * direccion) != null) {
							break;
						}
					}

				}
			}
		}
	}

	public void desplazamientoRey(final int filaPieza, final int columnaPieza, final int bando) {

		this.bando = bando;
		// Se itera en las casillas aledanias al rey. Los bucles for evitan la
		// introduccion manual.
		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				if (filaPieza + i < 8 && filaPieza + i >= 0 && columnaPieza + j < 8 && columnaPieza + j >= 0) {
					if (i == j && j == 0)// se omite la casilla de origen
						continue;
					legalizarCasilla(filaPieza + i, columnaPieza + j);
				}
			}
		}
		// Se comprueban manualmente los enroques, comprobando solamente la legalidad
		// desde un punto de vista estetico.

		if (!tablero.getCasilla(filaPieza, columnaPieza).getFabrica().isJaque()
				&& !tablero.getCasilla(filaPieza, columnaPieza).getFabrica().reyIsMoved()) {
			// enroque corto
			if (!tablero.getCasilla(filaPieza, columnaPieza - 3).getFabrica().torreIsMoved()) {// se verifica si la
																								// torre
																								// se ha movido
				if (piezaEnCasilla(filaPieza, columnaPieza - 1) == null
						&& piezaEnCasilla(filaPieza, columnaPieza - 2) == null)// se verifica que no hayan piezas entre
																				// le rey y la torre
					legalizarCasilla(filaPieza, columnaPieza - 2);
			}

			// enroque largo
			if (!tablero.getCasilla(filaPieza, columnaPieza + 4).getFabrica().torreIsMoved()) {// se verifica si la
																								// torre
																								// se ha movido
				if (piezaEnCasilla(filaPieza, columnaPieza + 3) == null
						&& piezaEnCasilla(filaPieza, columnaPieza + 2) == null
						&& piezaEnCasilla(filaPieza, columnaPieza + 1) == null) { // se verifica que no hayan piezas
																					// entre el rey y la torre
					legalizarCasilla(filaPieza, columnaPieza + 2);
				}
			}
		}

	}

	public void desplazamientoCaballo(final int filaPieza, final int columnaPieza, final int bando) {

		this.bando = bando;

		for (int j = 0; j < 2; j++) {// se realiza el mismo procedimiento que en el alfil,tal vez luego deba
										// separarlo en una funcion.
			int direccionFila;
			if (j % 2 == 0) {
				direccionFila = 1;
			} else {
				direccionFila = -1;
			}
			for (int k = 0; k < 2; k++) {
				int direccionColumna;
				if (k % 2 == 0) {
					direccionColumna = 1;
				} else {
					direccionColumna = -1;
				}
				if (filaPieza + 2 * direccionFila < 8 && filaPieza + 2 * direccionFila >= 0
						&& columnaPieza + 1 * direccionColumna < 8 && columnaPieza + 1 * direccionColumna >= 0) {

					legalizarCasilla(filaPieza + 2 * direccionFila, columnaPieza + 1 * direccionColumna);
				}
				if (filaPieza + 1 * direccionFila < 8 && filaPieza + 1 * direccionFila >= 0
						&& columnaPieza + 2 * direccionColumna < 8 && columnaPieza + 2 * direccionColumna >= 0) {

					legalizarCasilla(filaPieza + 1 * direccionFila, columnaPieza + 2 * direccionColumna);
				}
			}
		}
	}

	// Se comprueban de manera manual los movimientos del peon y se realiza la misma
	// comprobacion que en las funciones anteriores
	public void desplazamientoPeon(final int filaPieza, final int columnaPieza, final int bando) {

		if (columnaPieza + 1 < 8) {
			if (piezaEnCasilla(filaPieza + 1 * bando, columnaPieza + 1) != null
					&& piezaEnCasilla(filaPieza + 1 * bando, columnaPieza + 1).getBando() != bando)
				tablero.getCasilla(filaPieza + 1 * bando, columnaPieza + 1).setMovimientoLegal(true);
		}
		if (columnaPieza - 1 >= 0) {
			if (piezaEnCasilla(filaPieza + 1 * bando, columnaPieza - 1) != null
					&& piezaEnCasilla(filaPieza + 1 * bando, columnaPieza - 1).getBando() != bando)
				tablero.getCasilla(filaPieza + 1 * bando, columnaPieza - 1).setMovimientoLegal(true);
		}
		// comprueba los movimientos en la columna
		if (piezaEnCasilla(filaPieza + 1 * bando, columnaPieza) == null) {
			tablero.getCasilla(filaPieza + 1 * bando, columnaPieza).setMovimientoLegal(true);
			if ((piezaEnCasilla(filaPieza, columnaPieza).getBando() == 1 && filaPieza == 1)
					|| piezaEnCasilla(filaPieza, columnaPieza).getBando() == -1 && filaPieza == 6)
				if (piezaEnCasilla(filaPieza + 2 * bando, columnaPieza) == null) {
					tablero.getCasilla(filaPieza + 2 * bando, columnaPieza).setMovimientoLegal(true);
				}
		}
	}

	// funcion que simplifica las condiciones de la evaluacion de la existencia de
	// pieza en una casilla seleccionada
	private PiezaAjedrez piezaEnCasilla(final int filaPieza, final int columnaPieza) {
		return tablero.getCasilla(filaPieza, columnaPieza).getPieza();
	}

	// funcion que simplifica las condiciones de la movilidad
	private void legalizarCasilla(int fila, int columna) {
		tablero.getCasilla(fila, columna).setMovimientoLegal(comprobarDesplazamiento(fila, columna));
	}

	// funcion que comprueba si es posible legal dentro del tablero el
	// desplazamiento a la vez que comprueba si la pieza en una casilla es del
	// propio bando o del rival.
	private boolean comprobarDesplazamiento(final int filaPieza, final int columnaPieza) {

		if ((piezaEnCasilla(filaPieza, columnaPieza) != null))
			if (piezaEnCasilla(filaPieza, columnaPieza).getBando() == bando)
				return false;
		return true;
	}
}
