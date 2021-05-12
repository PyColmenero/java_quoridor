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
	JLabel lbl_texthelp = new JLabel("<html>El Juego que he decidido Dise�ar es QUORIDOR, el cual se basa en un juego por turnos, de 2. <br>\r\n"
			+ "Consta de una cuadr�cula de 9x9. Cada jugador, controla un pe�n, que debe comenzar en alguno <br>\r\n"
			+ "de los bordes del tablero. Los peones deben ubicarse en el centro de la fila y enfrentados <br>\r\n"
			+ "el uno del otro. Adem�s de controlar un pe�n, cada jugador va a tener 10 paredes para colocar. <br>\r\n"
			+ "En cada turno puedes o mover el pe�n  o poner una pared. <br><br>\r\n"
			+ "\r\n"
			+ "Los movimientos de los peones no pueden ser diagonales ni de m�s de una casilla (foto del lado n�1), <br>\r\n"
			+ "ni a trav�s de una pared (foto del lado n�2), a no ser que tengas un pe�n pegado, que lo podr�s <br>\r\n"
			+ "saltar (foto del lado n�5). Si hay un pe�n pegado y en la misma direcci�n hay una pared, podr� <br>\r\n"
			+ "saltar de manera diagonal (foto del lado n�3, n�4). Seg�n estas reglas, cuando sea tu turno, se <br>\r\n"
			+ "marcar�n en color m�s claro, las casillas colindantes a tu pe�n, donde puedas moverte, as� como <br>\r\n"
			+ "en la foto del lado. Haciendo click en una de estas casillas, se efectuar� el movimiento y el <br>\r\n"
			+ "turno avanzar�.<br><br>\r\n"
			+ "\r\n"
			+ "Las paredes ocupan 2 casillas de la cuadricula, y hay que ponerla de manera exacta, que solo <br>\r\n"
			+ "ocupen dos casillas (en la foto del lado, la pared A, est� bien puesta y la B, est� mal).<br>\r\n"
			+ "Para poner una pared, esa no puede atravesar ninguna otra. Haciendo click en dos rendijas, <br>\r\n"
			+ "pegadas, se colocar� una pared, si tienes, y el turno avanzar�. <br>\r\n"
			+ "Una pared, no se puede quitar ni mover, a no ser que hagas click en el bot�n de �Undo Wall�. <br>\r\n"
			+ "Eso quitar� la �ltima pared puesta. Sirve para las ocasiones que se haya puesto una pared de <br>\r\n"
			+ "manera equ�voca que rompa la partida. <br><br>\r\n"
			+ "\r\n"
			+ "Para ganar, tu pe�n tiene que llegar hasta su fila m�s lejana, donde empez� otro jugador. Siguiendo <br>\r\n"
			+ "la foto del lado, para que el amarillo gane, tendr� que llegar a la fila a donde est� el pe�n verde. <br>\r\n"
			+ "Para que el verde gane tendr� que llegar a la fila donde est� el pe�n amarillo. Para que el pe�n <br>\r\n"
			+ "rojo gane, tendr� que llegar en la fila donde est� el pe�n azul.<br><br>\r\n"
			+ "<b>EST� TOTALMENTE PROHIBIDO DEJAR ENCERRADO UN PEON O TAPAR AL 100% TU FILA <br>\r\n"
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
