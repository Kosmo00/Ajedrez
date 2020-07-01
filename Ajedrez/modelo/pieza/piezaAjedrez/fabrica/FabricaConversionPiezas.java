/**
 * 
 * El objeto de esta clase se encuentra en realizar los castings y comprobar funciones especificas
 * de clases como la clase rey, la clase torre y la clase peon. Esta clase se puede considerar una 
 * clase adaptadora. Cada funcion trabaja con una casilla propia de la clase y en cada funcion se 
 * comprueba si en la casilla existe dicha pieza y en caso de que exista se devuelve el valor que
 * tiene cada pieza en le parametro que se pide. 
 * OJO: En esta clase todos los valores de retorno son de tipo boolean.
 * OJO: Cada casillaAjedrez contiene su propia fabrica para facilitar en un futuro la concurrencia.
 * 
 */

package pieza.piezaAjedrez.fabrica;

import casilla.CasillaAjedrez;
import pieza.piezaAjedrez.Rey;
import pieza.piezaAjedrez.Torre;

public class FabricaConversionPiezas {

	private final CasillaAjedrez casilla;
	private Rey rey;
	private Torre torre;

	public FabricaConversionPiezas(CasillaAjedrez casilla) {
		this.casilla = casilla;
	}

	// funciones del rey

	public void ponerJaque() {
		if (tieneRey()) {
			rey.ponerJaque();
		}
	}

	public void quitarJaque() {
		if (tieneRey()) {
			rey.quitarJaque();
		}
	}

	public boolean isJaque() {
		if (tieneRey()) {
			return rey.isJaque();
		}
		return false;
	}

	public boolean reyIsMoved() {
		if (tieneRey()) {
			return rey.isMoved();
		}
		return true;// devuelve true porque si el rey no esta es que ya se movio de ahi :)
	}

	// funciones de torre.
	public boolean torreIsMoved() {
		if (tieneTorre()) {
			return torre.isMoved();
		}
		return true;// devuelve true porque si la torre no esta es que ya se movio de ahi :)
	}

	public void moved() {
		if (tieneTorre()) {
			torre.moved();
			return;
		}
		if (tieneRey()) {
			rey.moved();
		}
	}

	// funciones de comprobacion de piezas en la casilla.
	private boolean tieneRey() {
		if (casilla.getPieza() != null) {
			if (casilla.getPieza().getNombrePieza() == "Rey") {
				rey = (Rey) casilla.getPieza();
				return true;
			}
		}
		return false;
	}

	private boolean tieneTorre() {
		if(casilla.getPieza()!=null) {
		if (casilla.getPieza().getNombrePieza() == "Torre") {
			torre = (Torre) casilla.getPieza();
			return true;
		}
		}
		return false;
	}

}
