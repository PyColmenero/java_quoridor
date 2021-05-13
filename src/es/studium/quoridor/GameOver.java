package es.studium.quoridor;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Label;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class GameOver extends Frame {

	private static final long serialVersionUID = 1L;
	Dialog dlg_gameover = new Dialog(this);
	Label dlg_lbl_title = new Label("Jugador1 ha ganado!");
	JTable jTable;
	Button dlg_btn_again = new Button("Jugar otra vez");
	Button dlg_btn_close = new Button("Cerrar");
	String[] columnNames = { "Nombre", "Movimientos", "Paredes" };
	
	public GameOver(Player player1, Player player2) {
		
		dlg_gameover.setLayout(new FlowLayout());
		dlg_gameover.add(dlg_lbl_title);

		dlg_gameover.setSize(340, 180);
		dlg_gameover.setResizable(false);
		dlg_gameover.setLocationRelativeTo(null);
		dlg_gameover.setVisible(false);
		
		// titulo de ganador
			dlg_lbl_title.setText("¡" + player1.getName() + " ha ganado!");
			// añadir datos de cada jugador
			String[][] data = { { player1.getName(), player1.getMovements() + "", player1.getWalls() + "" },
					{ player2.getName(), player2.getMovements() + "", player2.getWalls() + "" } };
			jTable = new JTable(data, columnNames);

			// añadir tabla a ScrolPanel
			JScrollPane scrollPanel = new JScrollPane(jTable);
			scrollPanel.setPreferredSize(new Dimension(300, 55));
			
			//añadir el resto
			dlg_gameover.add(scrollPanel);
			dlg_gameover.add(dlg_btn_again);
			dlg_gameover.add(dlg_btn_close);
			dlg_gameover.setVisible(true);
		
	}
	
}
