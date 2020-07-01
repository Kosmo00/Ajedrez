package eventosMovimiento;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EventosBtnCerrar extends MouseAdapter {

	public void mouseClicked(MouseEvent e) {
		super.mouseClicked(e);

		System.exit(0);
	}
}
