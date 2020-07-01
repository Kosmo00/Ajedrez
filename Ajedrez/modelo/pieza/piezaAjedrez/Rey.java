package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;

public final class Rey extends PiezaAjedrez {

	private final String nombre = "Rey";
	private boolean tieneMovimientos = false;
	private boolean jaque = false;

	public Rey(final int bando, final Desplazamientos desplazamientos) {
		super(bando, desplazamientos);

		setDireccionImagenPiezas("wkw.gif", "bkw.gif");
		setNombrePieza(nombre);
		setTipoPieza('R');
	}

	public void movimientosLegales(final int filaPieza, final int columnaPieza) {

		desplazamiento.desplazamientoRey(filaPieza, columnaPieza, getBando());
	}

	public void ponerJaque() {
		jaque = true;
	}

	public void quitarJaque() {
		jaque = false;
	}

	public boolean isJaque() {
		return jaque;
	}

	public void moved() {
		tieneMovimientos = true;
	}

	public boolean isMoved() {
		return tieneMovimientos;
	}

	public PiezaAjedrez clonar(Desplazamientos desplazamiento) {

		Rey rey = new Rey(getBando(), desplazamiento);
		if (tieneMovimientos) {
			rey.moved();
			if (isJaque())
				ponerJaque();
		}

		return rey;
	}

}
