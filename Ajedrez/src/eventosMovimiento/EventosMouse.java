/**
 * 
 * Clase relacionada con los eventos de movimiento de las piezas.
 * 
 **/

package eventosMovimiento;

import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ventanas.componentes.ComponenteCasilla;
import ventanas.componentes.ComponenteTablero;

public final class EventosMouse extends MouseAdapter {

	// Funcion que modifica al cursor

	private final ComponenteTablero tablero;

	public EventosMouse(ComponenteTablero tablero) {
		this.tablero = tablero;
	}

	public void mouseEntered(MouseEvent e) {
		super.mouseEntered(e);

		ComponenteCasilla casilla = (ComponenteCasilla) e.getSource();

		if (casilla.getIcon() != null) {
			casilla.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	// funcion destinada a la seleccion de la pieza/casilla en/con la cual se
	// realizaran los movimientos
	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);
		ComponenteCasilla casilla = (ComponenteCasilla) e.getSource();

		tablero.getTablero().getMovimiento()
				.seleccionPiezaAMover(tablero.getTablero().getCasilla(casilla.getFila(), casilla.getColumna()));
		tablero.actualiza();
		tablero.getTablero().getMovimiento().comprobarEstado();
	}
}
