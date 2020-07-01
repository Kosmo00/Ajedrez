package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;

public final class Alfil extends PiezaAjedrez {

	private final String nombre = "Alfil";

	public Alfil(final int bando, final Desplazamientos desplazamientos) {
		super(bando, desplazamientos);

		setDireccionImagenPiezas("wbw.gif", "bbw.gif");
		setNombrePieza(nombre);
		setTipoPieza('A');
	}

	public void movimientosLegales(final int filaPieza, final int columnaPieza) {
		desplazamiento.desplazamientoDiagonal(filaPieza, columnaPieza, getBando());
	}

	public PiezaAjedrez clonar(Desplazamientos desplazamientos) {

		Alfil alfil = new Alfil(getBando(), desplazamientos);

		return alfil;

	}

}
