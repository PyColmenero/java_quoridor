package es.studium.quoridor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class ControladorGame implements ActionListener, WindowListener, MouseListener {

	private VistaGame vistaGame;
	private Modelo modelo;
	
	String name1;
	String name2;
	
	public ControladorGame(VistaGame vistaGame, Modelo modelo, String name1, String name2) {
	
		this.vistaGame = vistaGame;
		this.modelo = modelo;
		this.name1 = name1;
		this.name2 = name2;
				
		this.vistaGame.canvas.addMouseListener(this);
		this.vistaGame.btn_help.addActionListener(this);
		this.vistaGame.btn_undo.addActionListener(this);
		this.vistaGame.btn_end.addActionListener(this);
		this.vistaGame.dlg_btn_again.addActionListener(this);
		this.vistaGame.dlg_btn_close.addActionListener(this);
		this.vistaGame.addWindowListener(this);
	}
	
	public static void main(String[] args) {
		VistaGame vistaGame = new VistaGame("Player1", "Player2");
		new ControladorGame(vistaGame, null, "Player1", "Player2"); 
	}
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		// botón de UNDO
		if(vistaGame.btn_undo.equals(e.getSource())) {
			
			vistaGame.canvas.undowall();
			
		} else if(vistaGame.dlg_btn_again.equals(e.getSource())) { // botón de JugarOtraVez
			
			vistaGame.setVisible(false);

			VistaNames vistaNames = new VistaNames(name1, name2);
			new ControladorNames(vistaNames, modelo); 
			
		} else if(vistaGame.dlg_btn_close.equals(e.getSource())) { // botón de Cerrar
			
			System.exit(0);
			
		} else if(vistaGame.btn_end.equals(e.getSource())) { // botón de Salir de la partida
			
			// quitar la vista del juego
			vistaGame.setVisible(false);

			// poner la vista del Inicio
			VistaMain vistaMain = new VistaMain();
			Modelo modelo = new Modelo();
			new ControladorMain(vistaMain, modelo); 
			
		} else if(vistaGame.btn_help.equals(e.getSource())) {
			new VistaHelp();
		} 
	}
	
	@Override
	public void windowOpened(WindowEvent e) {}
	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
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
	@Override
	public void mouseClicked(MouseEvent e) {
		vistaGame.canvas.click(e.getX(), e.getY());
	}
	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
	
}
