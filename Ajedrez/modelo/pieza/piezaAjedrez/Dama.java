package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;

public final class Dama extends PiezaAjedrez {

	private final String nombre = "Dama";

	public Dama(final int bando, final Desplazamientos desplazamientos) {
		super(bando, desplazamientos);

		setDireccionImagenPiezas("wqw.gif", "bqw.gif");
		setNombrePieza(nombre);
		setTipoPieza('D');

	}

	public void movimientosLegales(final int filaPieza, final int columnaPieza) {

		desplazamiento.desplazamientoDiagonal(filaPieza, columnaPieza, getBando());
		desplazamiento.desplazamientoColumnaFila(filaPieza, columnaPieza, getBando());
	}

	@Override
	public PiezaAjedrez clonar(Desplazamientos desplazamiento) {
		Dama dama = new Dama(getBando(), desplazamiento);

		return dama;
	}

}
