package es.studium.quoridor;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class VistaTop10 {

	Image icon = Toolkit.getDefaultToolkit().getImage("./logo.png");

	JFrame ventana = new JFrame("QUORIDOR");

	Panel pnl_title = new Panel();
	Label lbl_title = new Label("QUORIDOR", Label.CENTER);

	Label lbl_loading = new Label("Cargando...");

	public VistaTop10() {
		
		ventana.setIconImage(icon);
		ventana.setLayout(new FlowLayout());

		// TITLE
		lbl_title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		lbl_title.setPreferredSize(new Dimension(390, 70));
		pnl_title.add(lbl_title);
		ventana.add(pnl_title);

		// SPACE
		Panel pnl_space = new Panel();
		pnl_space.setPreferredSize(new Dimension(390, 20));
		ventana.add(pnl_space);

		lbl_loading.setForeground( Color.red );
		ventana.add(lbl_loading);

		// BUTTON

		ventana.setSize(450, 400);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);
	}
}
