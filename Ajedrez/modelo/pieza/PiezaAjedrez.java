package pieza;

import javax.swing.ImageIcon;

import pieza.piezaAjedrez.Desplazamientos;

public abstract class PiezaAjedrez {

	private final int bando;
	protected char tipoPieza;
	protected Desplazamientos desplazamiento;
	private ImageIcon figura;
	private String direccionImagenPieza = "piezas/GIF/";
	private String nombrePieza = "";

	private void setFigura() {
		figura = new ImageIcon(direccionImagenPieza);

	}

	protected void setNombrePieza(String nombre) {
		nombrePieza = nombre;
	}

	protected void setTipoPieza(final char tipoPieza) {
		this.tipoPieza = tipoPieza;
	}

	protected void setDireccionImagenPiezas(final String direccionImagenPiezaBlanca,
			final String direccionImagenPiezaNegra) {
		if (bando == 1)
			direccionImagenPieza += direccionImagenPiezaBlanca;
		else
			direccionImagenPieza += direccionImagenPiezaNegra;
		setFigura();
	}

	public PiezaAjedrez(final int bando, final Desplazamientos desplazamientos) {
		this.bando = bando;
		this.desplazamiento = desplazamientos;
	}

	/****************************************************************************************************************************
	 * 
	 * Funciones getters
	 * 
	 ****************************************************************************************************************************/
	public int getBando() {
		return bando;
	}

	public int getTipoPieza() {
		return tipoPieza;
	}

	public ImageIcon getFigura() {
		return figura;
	}

	public String getNombrePieza() {
		return nombrePieza;
	}

	public abstract void movimientosLegales(final int filaPieza, final int columnaPieza);

	public abstract PiezaAjedrez clonar(final Desplazamientos desplazamiento);

	protected void setDesplazamientos(final Desplazamientos desplazamiento) {
		this.desplazamiento = desplazamiento;
	}
}
