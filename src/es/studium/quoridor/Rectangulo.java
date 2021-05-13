package es.studium.quoridor;

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Rectangulo extends Frame implements WindowListener {
	private static final long serialVersionUID = 1L;

	public Rectangulo() {
		setTitle("Dibujando...");
		addWindowListener(this);
		setSize(220, 100);
		setVisible(true);
	}

	public void paint(Graphics g) {
// Se dibuja un rectángulo de 25 pixeles de ancho x alto a partir de la posición 50,50
		g.drawRect(50, 50, 25, 25);
	}

	public void windowActivated(WindowEvent we) {
	}

	public void windowClosed(WindowEvent we) {
	}

	public void windowClosing(WindowEvent we) {
		System.exit(0);
	}

	public void windowDeactivated(WindowEvent we) {
	}

	public void windowDeiconified(WindowEvent we) {
	}

	public void windowIconified(WindowEvent we) {
	}

	public void windowOpened(WindowEvent we) {
	}

	public static void main(String[] args) {
		new Rectangulo();
	}
}