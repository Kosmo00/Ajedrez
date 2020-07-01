package eventosMovimiento;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import ventanas.VentanaPrincipal;

public class MovimientoVentana extends MouseAdapter {
	private int x;
	private int y;

	private int xx;
	private int yy;

	VentanaPrincipal ventana;

	public MovimientoVentana(VentanaPrincipal ventana) {
		this.ventana = ventana;
	}

	public void mousePressed(MouseEvent e) {
		super.mousePressed(e);

		x = e.getLocationOnScreen().x;
		y = e.getLocationOnScreen().y;

	}

	public void mouseDragged(MouseEvent e) {
		super.mouseDragged(e);

		xx = e.getLocationOnScreen().x;
		yy = e.getLocationOnScreen().y;

		ventana.actualizaPosicion(xx - x, yy - y);

		x = xx;
		y = yy;
	}

}
