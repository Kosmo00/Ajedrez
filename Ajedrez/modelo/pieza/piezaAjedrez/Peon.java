package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;

public final class Peon extends PiezaAjedrez {

	private final String nombre = "Peon";
	private boolean alPaso = false;

	public Peon(final int bando, final Desplazamientos desplazamientos) {

		super(bando, desplazamientos);

		setDireccionImagenPiezas("wpw.gif", "bpw.gif");

		setNombrePieza(nombre);

	}

	public void ponerAlPaso() {
		alPaso = true;
	}

	public void quitarAlPaso() {
		alPaso = false;
	}

	public boolean getAlPaso() {
		return alPaso;
	}

	public void movimientosLegales(final int filaPieza, final int columnaPieza) {

		desplazamiento.desplazamientoPeon(filaPieza, columnaPieza, getBando());

	}

	@Override
	public PiezaAjedrez clonar(Desplazamientos desplazamiento) {
		Peon peon = new Peon(getBando(), desplazamiento);
		if (getAlPaso())
			peon.ponerAlPaso();
		return peon;
	}
}
