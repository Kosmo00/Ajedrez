package ventanas.componentes;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;

import eventosMovimiento.EventosMouse;
import posicion.PosicionAjedrez;
import tableros.Tablero;

public class ComponenteTablero extends JLabel {

	private static final long serialVersionUID = 1L;

	private final Color COLOR_JAQUE = Color.RED;

	private static final Color COLOR_ML_CC = new Color(100, 100, 255);
	private static final Color COLOR_ML_CO = new Color(40, 40, 100);

	private static final Color COLOR_CO = new Color(100, 100, 100);
	private static final Color COLOR_CC = new Color(255, 255, 255);

	private static final int NUM_FILAS = 8;
	private static final int NUM_COLUMNAS = 8;

	private static final int DIMENSION_CASILLA = 60;

	private Tablero tablero;
	private PosicionAjedrez posicion;

	private EventosMouse eventoMovimiento;

	private final ComponenteCasilla[][] casilla;

	public ComponenteTablero() {

		setPreferredSize(new Dimension(DIMENSION_CASILLA * NUM_FILAS, DIMENSION_CASILLA * NUM_COLUMNAS));
		setLayout(new GridLayout(NUM_FILAS, NUM_COLUMNAS));
		setVisible(true);
		// setIcon(new ImageIcon("piezas/GIF/tablero.png"));

		posicion = new PosicionAjedrez();

		tablero = posicion.posicionInicial();

		eventoMovimiento = new EventosMouse(this);

		casilla = new ComponenteCasilla[NUM_FILAS][NUM_COLUMNAS];

		for (int i = NUM_FILAS - 1; i >= 0; i--) {
			for (int j = NUM_COLUMNAS - 1; j >= 0; j--) {
				casilla[i][j] = new ComponenteCasilla(i, j);
				add(casilla[i][j]);
				casilla[i][j].addMouseListener(eventoMovimiento);
			}
		}
	}

	public void actualiza() {

		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (tablero.getCasilla(i, j).getFabrica().isJaque()) {
					casilla[i][j].setBackground(COLOR_JAQUE);
				} else if (tablero.getCasilla(i, j).getMovimientoLegal()) {
					casilla[i][j].setBackground((i + j) % 2 == 0 ? COLOR_ML_CC : COLOR_ML_CO);
				} else {
					casilla[i][j].setBackground((i + j) % 2 == 0 ? COLOR_CC : COLOR_CO);
				}

				if (tablero.getCasilla(i, j).getPieza() != null) {
					casilla[i][j].setIcon(tablero.getCasilla(i, j).getPieza().getFigura());
				} else {
					casilla[i][j].setIcon(null);
				}
			}
		}
	}

	public Tablero getTablero() {
		return tablero;
	}

}
