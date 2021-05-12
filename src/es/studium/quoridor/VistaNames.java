package es.studium.quoridor;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.Toolkit;

public class VistaNames {
	
	Image icon = Toolkit.getDefaultToolkit().getImage("./logo.png");    

	Frame ventana = new Frame("Nombres de jugadores.");
	
	
	Panel pnl_title = new Panel();
	Label lbl_title = new Label("Nombres de los Jugadores", Label.CENTER);
	Label lbl_player1 = new Label("Jugador 1");
	TextField tf_player1 = new TextField("", 46);
	Label lbl_player2 = new Label("Jugador 2");
	TextField tf_player2 = new TextField("", 46);
	Label lbl_errormsg = new Label("", Label.CENTER);
	Button btn_accept = new Button("Aceptar");
	

	/**
	 * los paremostros de String indican el contenido de los TextFields.
	 * @param name1
	 * @param name2
	 */
	public VistaNames(String name1, String name2) {
		
		ventana.setIconImage(icon);
		ventana.setLayout(new FlowLayout());
		ventana.setBackground( new Color(25,25,25) );
		
		// TITLE
		lbl_title.setFont( new Font( Font.SANS_SERIF , Font.PLAIN, 35) );
		lbl_title.setForeground( Color.white );
		lbl_title.setPreferredSize( new Dimension(500,70) );
		pnl_title.add(lbl_title);
		ventana.add(pnl_title);
		
		// INPUTS
		lbl_player1.setForeground( Color.white );
		lbl_player2.setForeground( Color.white );
		ventana.add(lbl_player1);
		ventana.add(tf_player1);
		ventana.add(lbl_player2);
		ventana.add(tf_player2);
		lbl_errormsg.setForeground( Color.red );
		lbl_errormsg.setPreferredSize( new Dimension(390,20) );
		ventana.add(lbl_errormsg);
		
		tf_player1.setText(name1);
		tf_player2.setText(name2);
		
		// SPACE
		Panel pnl_space = new Panel();
		pnl_space.setPreferredSize( new Dimension(390,20) );
		ventana.add(pnl_space);
		
		// BUTTON
		btn_accept.setPreferredSize( new Dimension(304, 40) );
		ventana.add(btn_accept);
		
		
		ventana.setSize(500, 300);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
		
	}
}
