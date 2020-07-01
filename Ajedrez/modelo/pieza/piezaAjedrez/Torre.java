package pieza.piezaAjedrez;

import pieza.PiezaAjedrez;

public class Torre extends PiezaAjedrez {

	private final String nombre = "Torre";
	private boolean movimientosRealizados = false;

	public Torre(final int bando, final Desplazamientos desplazamientos) {
		super(bando, desplazamientos);

		setDireccionImagenPiezas("wrw.gif", "brw.gif");
		setNombrePieza(nombre);

		setTipoPieza('T');
	}

	public boolean isMoved() {
		return movimientosRealizados;
	}

	public void moved() {
		movimientosRealizados = true;
	}

	public void movimientosLegales(int filaPieza, int columnaPieza) {

		desplazamiento.desplazamientoColumnaFila(filaPieza, columnaPieza, getBando());

	}

	@Override
	public PiezaAjedrez clonar(Desplazamientos desplazamiento) {
		Torre torre = new Torre(getBando(), desplazamiento);
		if (isMoved()) {
			torre.moved();
		}
		return torre;
	}

}
