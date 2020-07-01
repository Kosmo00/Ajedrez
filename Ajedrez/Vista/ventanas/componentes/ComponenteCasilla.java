package ventanas.componentes;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ComponenteCasilla extends JLabel {

	private static final long serialVersionUID = 1L;

	private final int fila;
	private final int columna;

	/************************************************************************************************************************
	 * 
	 * constructores
	 * 
	 ************************************************************************************************************************/

	public ComponenteCasilla(final int fila, final int columna) {

		this.fila = fila;
		this.columna = columna;

		setOpaque(true);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
	}

	/************************************************************************************************************************
	 * 
	 * Funciones Getters
	 * 
	 ************************************************************************************************************************/

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}
}
