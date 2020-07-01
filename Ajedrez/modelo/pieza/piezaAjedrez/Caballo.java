package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;

public final class Caballo extends PiezaAjedrez {

	private final String nombre = "Caballo";

	public Caballo(final int bando, final Desplazamientos desplazamientos) {
		super(bando, desplazamientos);

		setDireccionImagenPiezas("wnw.gif", "bnw.gif");
		setNombrePieza(nombre);
		setTipoPieza('C');
	}

	public void movimientosLegales(final int filaPieza, final int columnaPieza) {

		desplazamiento.desplazamientoCaballo(filaPieza, columnaPieza, getBando());

	}

	public PiezaAjedrez clonar(Desplazamientos desplazamiento) {
		Caballo caballo = new Caballo(getBando(), desplazamiento);
		return caballo;
	}

}
