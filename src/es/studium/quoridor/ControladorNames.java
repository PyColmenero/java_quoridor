package es.studium.quoridor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorNames implements ActionListener, WindowListener {
	
	private VistaNames vistaNames;
	private Modelo modelo;
	
	public ControladorNames(VistaNames vistaNames, Modelo modelo) {
		
		this.vistaNames = vistaNames;
		this.modelo = modelo;
		
		this.vistaNames.btn_accept.addActionListener(this);
		this.vistaNames.ventana.addWindowListener(this);
		
	}

	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// botón de aceptar para jugar
		if(vistaNames.btn_accept.equals(e.getSource())) {
			// obtenemos los dos nombres
			String name1 = vistaNames.tf_player1.getText();
			String name2 = vistaNames.tf_player2.getText();
			
			// comprobamos que están bn escritos los nombres
			int status = modelo.correctNames(name1, name2);
			
			// si es 0, todo bien
			if (status == Modelo.GOOD) {
				VistaGame vistaGame = new VistaGame();
				new ControladorGame(vistaGame, modelo, name1, name2);
				vistaNames.ventana.setVisible(false);
			}
			
			// error en nombres, se le muestra en Label que error es
			else if (status == Modelo.ERROR_LENGTH)
				vistaNames.lbl_errormsg.setText("Nombres entre 3 y 45 carácteres");
			else if (status == Modelo.ERROR_CHAR)
				vistaNames.lbl_errormsg.setText("Solo se permiten letras, números y guiones bajos");
			else if (status == Modelo.ERROR_EQUALS)
				vistaNames.lbl_errormsg.setText("Los nombres no pueden ser iguales");
		}
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}

	@Override
	public void windowClosing(WindowEvent e) {
		
		// cerramos esta ventana
		vistaNames.ventana.setVisible(false);
		
		// re instanciamos
		new ControladorMain(new VistaMain(), modelo);
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
