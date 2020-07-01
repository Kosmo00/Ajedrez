/**
 * @author Kosmo
 * Esta clase almacena informacion relacionada con cada casilla del tablero, incluida la pieza que contiene,
 * su nomenclatura y si dicha casilla contiene algun movimiento legal.
 * A esta clase se le ha añadido tambien una clase fabrica de conversion de piezas, todo el trabajo de
 * almacenar las piezas, y  la conversion polimorfica de estas no puede estar mejor ubicada.
 * 
 **/

package casilla;

import pieza.PiezaAjedrez;
import pieza.piezaAjedrez.fabrica.FabricaConversionPiezas;

public class CasillaAjedrez {

	private final int fila;
	private final int columna;

	private final String nombre;

	private boolean casillaMLegal = false;

	private PiezaAjedrez piezaEnCasilla = null;

	private final FabricaConversionPiezas fabrica;

	/************************************************************************************************************************
	 * 
	 * constructores
	 * 
	 ************************************************************************************************************************/

	public CasillaAjedrez(final int fila, final int columna) {

		this.fila = fila;
		this.columna = columna;

		fabrica = new FabricaConversionPiezas(this);

		nombre = "" + (char) ('h' - columna) + (fila + 1);
	}

	public CasillaAjedrez(final String nombreCasilla) {

		columna = (int) 'h' - (int) nombreCasilla.charAt(0) + 1;
		fila = Integer.parseInt(nombreCasilla, 1);

		fabrica = new FabricaConversionPiezas(this);

		nombre = nombreCasilla;
	}

	/************************************************************************************************************************
	 * 
	 * Funciones Setter
	 * 
	 ************************************************************************************************************************/

	public void setPieza(final PiezaAjedrez piezaMovida) {// funcion para actualizar las piezas movidas
		piezaEnCasilla = piezaMovida;
	}

	public void setMovimientoLegal(boolean legalidad) {
		casillaMLegal = legalidad;
	}

	/***************************************************************************************************************************
	 * 
	 * Funciones getter
	 * 
	 ***************************************************************************************************************************/

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	public PiezaAjedrez getPieza() {
		return piezaEnCasilla;
	}

	public boolean getMovimientoLegal() {
		return casillaMLegal;
	}

	public String getNombre() {
		return nombre;
	}

	public FabricaConversionPiezas getFabrica() {
		return fabrica;
	}
}
