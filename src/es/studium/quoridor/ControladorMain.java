package es.studium.quoridor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorMain implements ActionListener, WindowListener {

	private VistaMain vistaMain;
	private Modelo modelo;

	public ControladorMain(VistaMain vistaMain, Modelo modelo) {

		this.vistaMain = vistaMain;
		this.modelo = modelo;

		// añadir ActionListeners
		this.vistaMain.bnt_play.addActionListener(this);
		this.vistaMain.btn_help.addActionListener(this);
		this.vistaMain.btn_top10.addActionListener(this);
		this.vistaMain.ventana.addWindowListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// botón de JUGAR
		if (vistaMain.bnt_play.equals(e.getSource())) {

			// CREO OTRO MVC para elegir nombre de los jugadores
			VistaNames vistaNames = new VistaNames("", "");
			new ControladorNames(vistaNames, modelo);
			// Quito la Ventana MAIN
			vistaMain.ventana.setVisible(false);

		} else if (vistaMain.btn_help.equals(e.getSource())) { // botón de Ayuda

			// nueva vista de ayuda, sin CONTROLADOR ni MODELO, puesto que no va a ver
			// interacción con el usuario
			new VistaHelp();

		} else if (vistaMain.btn_top10.equals(e.getSource())) { // botón de Top10

			// instanciar TOP 10
			new ControladorTop10(new VistaTop10(), modelo);

		}
	}

	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) { System.exit(0); }

	@Override
	public void windowClosed(WindowEvent e) {}

	@Override
	public void windowIconified(WindowEvent e) {}

	@Override
	public void windowDeiconified(WindowEvent e) {}

	@Override
	public void windowActivated(WindowEvent e) {}

	@Override
	public void windowDeactivated(WindowEvent e) {}

}
