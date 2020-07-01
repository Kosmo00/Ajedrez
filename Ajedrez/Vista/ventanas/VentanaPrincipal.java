package ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import eventosMovimiento.EventosBtnCerrar;
import eventosMovimiento.MovimientoVentana;
import ventanas.componentes.ComponenteTablero;

public class VentanaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;

	private final int ANCHO_VENTANA = 800;
	private final int ALTO_VENTANA = 600;

	private JButton btnCerrar;

	private JPanel contentPane;

	private ComponenteTablero tablero;

	private JPanel panel;

	private MovimientoVentana movVentana;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public VentanaPrincipal() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(ANCHO_VENTANA, ALTO_VENTANA));
		setUndecorated(true);
		setLocationRelativeTo(null);
		setBackground(new Color(240, 240, 240, 0));

		movVentana = new MovimientoVentana(this);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));
		setContentPane(contentPane);
		contentPane.setBackground(new Color(0, 0, 0, 200));
		contentPane.setLayout(null);

		tablero = new ComponenteTablero();
		tablero.setBounds(30, 75, 480, 480);
		tablero.actualiza();
		contentPane.add(tablero);

		btnCerrar = new JButton();
		btnCerrar.setBounds(750, 0, 50, 50);
		contentPane.add(btnCerrar);
		btnCerrar.setIcon(new ImageIcon("Iconos/btnCerrar.png"));
		btnCerrar.setBorderPainted(false);
		btnCerrar.setRolloverIcon(new ImageIcon("Iconos/btnCerrarMO.png"));
		btnCerrar.addMouseListener(new EventosBtnCerrar());

		panel = new JPanel();
		panel.setBounds(0, 0, 754, 50);
		contentPane.add(panel);
		panel.setBackground(new Color(35, 34, 40));
		panel.addMouseListener(movVentana);
		panel.addMouseMotionListener(movVentana);

	}

	public void actualizaPosicion(int x, int y) {
		setLocation(this.getLocation().x + x, this.getLocation().y + y);
	}
}
