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
import java.awt.Toolkit;

public class VistaMain {

	Image icon = Toolkit.getDefaultToolkit().getImage("./logo.png");

	Frame ventana = new Frame("QUORIDOR");

	Panel pnl_title = new Panel();
	Label lbl_title = new Label("QUORIDOR", Label.CENTER);

	Button bnt_play = new Button("Jugar");
	Button btn_help = new Button("Ayuda");
	Button btn_top10 = new Button("Top 10");

	public VistaMain() {
		
		ventana.setIconImage(icon);
		ventana.setLayout(new FlowLayout());
		ventana.setBackground(new Color(25,25,25));

		// TITLE
		lbl_title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		lbl_title.setForeground(Color.white);
		lbl_title.setPreferredSize(new Dimension(390, 70));
		pnl_title.add(lbl_title);
		ventana.add(pnl_title);

		// SPACE
		Panel pnl_space = new Panel();
		pnl_space.setPreferredSize(new Dimension(390, 20));
		ventana.add(pnl_space);

		// INPUTS
		bnt_play.setPreferredSize(new Dimension(300, 40));
		btn_help.setPreferredSize(new Dimension(147, 40));
		btn_top10.setPreferredSize(new Dimension(147, 40));
		ventana.add(bnt_play);
		ventana.add(btn_help);
		ventana.add(btn_top10);

		// BUTTON
		ventana.setSize(450, 270);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);

		ventana.setVisible(true);

	}

}
