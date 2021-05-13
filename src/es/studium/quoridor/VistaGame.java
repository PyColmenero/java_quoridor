package es.studium.quoridor;

import java.awt.Button;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class VistaGame extends JFrame {

	private static final long serialVersionUID = 1L;

	Image icon = Toolkit.getDefaultToolkit().getImage("./logo.png");

	Label lbl_title = new Label("Quoridor", Label.CENTER);
	Button btn_undo = new Button("Undo Wall");
	Button btn_help = new Button("Ayuda");
	Label lbl_turn = new Label("Turno de: ");
	Button btn_end = new Button("Salir de la partida");
	Label lbl_error = new Label(" ");
	Panel pnl_data = new Panel();

	String[] columnNames = { "Nombre", "Movimientos", "Paredes" };

	Dialog dlg_gameover = new Dialog(this);
	Label dlg_lbl_title = new Label("Jugador1 ha ganado!");
	JTable jTable;
	Button dlg_btn_again = new Button("Jugar otra vez");
	Button dlg_btn_close = new Button("Cerrar");

	public VistaGame() {

		this.setIconImage(icon);
		this.setLayout(new FlowLayout());
		
		
		this.setSize(565, 1000);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		dlg_gameover.setLayout(new FlowLayout());
		dlg_gameover.add(dlg_lbl_title);

		dlg_gameover.setSize(340, 180);
		dlg_gameover.setResizable(false);
		dlg_gameover.setLocationRelativeTo(null);
		dlg_gameover.setVisible(false);
	}

	
	
	
	boolean resize = false;
	public void reload_frame() {
        if (resize) {
        	this.setSize(565, 1000);
        } else {
        	this.setSize(565, 1001);
        }
        resize = !resize;
	}

	/**
	 * enseña un dialog de quien ha ganado y las estadísticas de la partidas
	 * @param player1
	 * @param player2
	 */
	public void game_over(Player player1, Player player2) {
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

	public void update_labels(String[] data) {
		
		lbl_turn.setText("Turno de " + data[0]);
		lbl_error.setText(data[1]);
		
		reload_frame();
		
	}

	public void build(ModeloGame modeloGame, String name1, String name2) {
		
		this.setTitle("QUORIDOR " + name1 + " vs " + name2);
		
		this.add(modeloGame);
		modeloGame.setPreferredSize(new Dimension(550, 900));
		pnl_data.add(btn_help);
		pnl_data.add(btn_end);
		pnl_data.add(btn_undo);
		lbl_turn.setText("Turno de: " + name1);
		pnl_data.add(lbl_turn);
		pnl_data.add(lbl_error);

		pnl_data.setPreferredSize(new Dimension(550, 100));
		this.add(pnl_data);
		
		reload_frame();
	}

	
}
