package es.studium.quoridor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorTop10 implements ActionListener, WindowListener {
	
	private VistaTop10 vistaTop10;
	
	public ControladorTop10(VistaTop10 vistaTop10, Modelo modelo) {
		
		this.vistaTop10 = vistaTop10;
		this.vistaTop10.ventana.addWindowListener(this);
		
		// cargamos el TOP10 en una JTable
		modelo.add_top10(vistaTop10);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		
		// cerramos esta ventana
		vistaTop10.ventana.setVisible(false);
	}

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
