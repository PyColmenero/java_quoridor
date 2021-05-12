package es.studium.quoridor;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class VistaHelp {
	Image icon = Toolkit.getDefaultToolkit().getImage("./logo.png");

	JFrame ventana = new JFrame("QUORIDOR - Ayuda");

	JPanel pnl_title = new JPanel();
	JLabel lbl_title = new JLabel("QUORIDOR", JLabel.CENTER);
	JPanel pnl_texthelp = new JPanel();
	JLabel lbl_texthelp = new JLabel("<html>El Juego que he decidido Diseñar es QUORIDOR, el cual se basa en un juego por turnos, de 2. <br>\r\n"
			+ "Consta de una cuadrícula de 9x9. Cada jugador, controla un peón, que debe comenzar en alguno <br>\r\n"
			+ "de los bordes del tablero. Los peones deben ubicarse en el centro de la fila y enfrentados <br>\r\n"
			+ "el uno del otro. Además de controlar un peón, cada jugador va a tener 10 paredes para colocar. <br>\r\n"
			+ "En cada turno puedes o mover el peón  o poner una pared. <br><br>\r\n"
			+ "\r\n"
			+ "Los movimientos de los peones no pueden ser diagonales ni de más de una casilla (foto del lado nº1), <br>\r\n"
			+ "ni a través de una pared (foto del lado nº2), a no ser que tengas un peón pegado, que lo podrás <br>\r\n"
			+ "saltar (foto del lado nº5). Si hay un peón pegado y en la misma dirección hay una pared, podrá <br>\r\n"
			+ "saltar de manera diagonal (foto del lado nº3, nº4). Según estas reglas, cuando sea tu turno, se <br>\r\n"
			+ "marcarán en color más claro, las casillas colindantes a tu peón, donde puedas moverte, así como <br>\r\n"
			+ "en la foto del lado. Haciendo click en una de estas casillas, se efectuará el movimiento y el <br>\r\n"
			+ "turno avanzará.<br><br>\r\n"
			+ "\r\n"
			+ "Las paredes ocupan 2 casillas de la cuadricula, y hay que ponerla de manera exacta, que solo <br>\r\n"
			+ "ocupen dos casillas (en la foto del lado, la pared A, está bien puesta y la B, está mal).<br>\r\n"
			+ "Para poner una pared, esa no puede atravesar ninguna otra. Haciendo click en dos rendijas, <br>\r\n"
			+ "pegadas, se colocará una pared, si tienes, y el turno avanzará. <br>\r\n"
			+ "Una pared, no se puede quitar ni mover, a no ser que hagas click en el botón de “Undo Wall”. <br>\r\n"
			+ "Eso quitará la última pared puesta. Sirve para las ocasiones que se haya puesto una pared de <br>\r\n"
			+ "manera equívoca que rompa la partida. <br><br>\r\n"
			+ "\r\n"
			+ "Para ganar, tu peón tiene que llegar hasta su fila más lejana, donde empezó otro jugador. Siguiendo <br>\r\n"
			+ "la foto del lado, para que el amarillo gane, tendrá que llegar a la fila a donde está el peón verde. <br>\r\n"
			+ "Para que el verde gane tendrá que llegar a la fila donde está el peón amarillo. Para que el peón <br>\r\n"
			+ "rojo gane, tendrá que llegar en la fila donde está el peón azul.<br><br>\r\n"
			+ "<b>ESTÁ TOTALMENTE PROHIBIDO DEJAR ENCERRADO UN PEON O TAPAR AL 100% TU FILA <br>\r\n"
			+ "PARA QUE NADIE LLEGE Y NO PUEDAN GANAR.</b></b>");
	
	JPanel pnl_imghelp = new JPanel();
	
	public VistaHelp() {

		System.out.println("Main");
		
		ventana.setIconImage(icon);
		ventana.setLayout(new FlowLayout());

		// TITLE
		lbl_title.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 35));
		lbl_title.setPreferredSize(new Dimension(390, 70));
		pnl_title.add(lbl_title);
		ventana.add(pnl_title);

		// SPACE
		Panel pnl_space = new Panel();
		pnl_space.setPreferredSize(new Dimension(1100, 20));
		ventana.add(pnl_space);

		lbl_texthelp.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 14));
		pnl_texthelp.setPreferredSize( new Dimension(650, 700) );
		pnl_texthelp.add(lbl_texthelp);
		ventana.add(pnl_texthelp);

		// cargar las imagenes
		BufferedImage bf_img1 = null;
		BufferedImage bf_img2 = null;
		try {
			bf_img1 = ImageIO.read(new File("./img/help.jpg"));
			bf_img2 = ImageIO.read(new File("./img/bad.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	    JLabel picLabel1 = new JLabel(new ImageIcon(bf_img1));
	    JLabel picLabel2 = new JLabel(new ImageIcon(bf_img2));
		
		pnl_imghelp.add(picLabel1);
		pnl_imghelp.add(picLabel2);
		pnl_imghelp.setPreferredSize( new Dimension(300, 700) );
		ventana.add(pnl_imghelp);
		
		ventana.setSize(1040, 800);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.setVisible(true);

	}

}
