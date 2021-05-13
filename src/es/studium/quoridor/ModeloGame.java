package es.studium.quoridor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;

public class ModeloGame extends JPanel {

	private static final long serialVersionUID = 1L;

	Connections con = new Connections();
	Player players[] = new Player[2];
	Interpolate interpolate = new Interpolate(this);
	VistaGame vg;
	
	int turn = 0;
	boolean clicked = false;
	int x_click;
	int y_click;

	final Color BG_COLOR = new Color(245, 245, 245);
	final Color BLOCK_COLOR = new Color(50, 56, 58);
	final Color MOVEABLE_BLOCK = new Color(80, 86, 88);
	final Color DOWN_WALL_COLOR = new Color(112, 106, 106);
	final Color WALL_COLOR = new Color(226, 207, 104);
	final Color SIDE_COLOR = new Color(98, 44, 52);

	final int LOGO_HEIGHT = 80;
	final int BLOCK_WIDTH = 50;
	final int WALL_WIDTH = 10;
	final int WALL_HEIGHT = (BLOCK_WIDTH * 2) + WALL_WIDTH;

	final int BLOCK_AMMOUNT = 9;
	final int WALL_AMMOUNT = BLOCK_AMMOUNT + 1;
	final int WALLBLOCK_WIDTH = BLOCK_WIDTH + WALL_WIDTH;
	final int TABLE_WIDTH = (BLOCK_WIDTH * BLOCK_AMMOUNT) + (WALL_WIDTH * WALL_AMMOUNT);

	final int SIDE_PADDING = 10;
	final int SIDE_WIDTH = TABLE_WIDTH - 20;
	final int TABLE_SIDE = WALL_HEIGHT + (SIDE_PADDING * 2);
	final int TABLE_HEIGHT = TABLE_WIDTH + (TABLE_SIDE * 2);

	final int LINE_WIDTH = 2;
	final int HORIZONTAL = 0;
	final int VERTICAL = 1;
	final int EMPTY = 0;
	final int PLAYER = 1;
	final int WALL = 2;

	final int MAP_LEN = BLOCK_AMMOUNT + WALL_WIDTH;
	int posible_blocks[][] = { { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 }, { -1, -1 } };

	int walls_list[][] = { { 0, 0, 0 } };
	int map[][] = new int[19][19];

	/*int walls_list[][] = { { 0, 0, 0 }, { 2, 1, 0 }, { 2, 5, 0 }, { 2, 11, 0 }, { 2, 15, 0 }, { 3, 8, 1 }, { 6, 9, 0 }, { 5, 12, 1 }, { 4, 13, 0 }, { 5, 16, 1 }, { 9, 16, 1 }, { 9, 16, 1 }, { 14, 15, 0 }, { 11, 14, 1 }, { 10, 11, 0 } };
	int map[][] = { { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 2, 2, 2, 0, 2, 2, 2, 0, 0, 0, 2, 2, 2, 0, 2, 2, 2, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 2, 2, 2, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 2, 0, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 2, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 2, 2, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } };*/

	int[] last_player_pos = { 0, 0 };

	final int LOGO_FONT_SIZE = 32;
	final int NAMES_FONT_SIZE = 16;
	boolean valid_player_movement = false;
	int y_start;
	int last_type_action = -1;
	int last_wall_player = -1;
	static boolean MOVE_HACK = false;
	static boolean WIN = false;
	boolean game_status = true;
	String error = "";

	public ModeloGame(String name1, String name2, VistaGame vistaGame) {
		// dar tamaño y fondo
		this.setBackground(Color.black);
		this.setSize(TABLE_WIDTH, TABLE_HEIGHT);

		vg = vistaGame;
		
		// instanciar los jugadores
		players[0] = new Player(name1, 9, 17, new Color(159, 54, 255), 10, 0);
		players[1] = new Player(name2, 9, 1, new Color(150, 255, 54), 10, 0);

		// añadir los jugadores al mapa
		map[players[0].getY()][players[0].getX()] = PLAYER;
		map[players[1].getY()][players[1].getX()] = PLAYER;
		
		// intanciar las posiciones anteriores
		last_player_pos[0] = players[0].getX();
		last_player_pos[1] = players[0].getY();

		// pintar por primera vez;
		repaint();
	}


	public void paint(Graphics g) {
		
		draw_background_table(g);

		// Titulo
		center_string(g, Color.BLACK, "TimesRoman", "QUORIDOR", LOGO_FONT_SIZE, 0, 0, TABLE_WIDTH, LOGO_HEIGHT);

		// Dibujar la maya de bloques
		draw_maya(g);


		// ===================== WALLS IN SIDE =====================
		y_start = LOGO_HEIGHT + TABLE_SIDE + TABLE_WIDTH;
		draw_sidewalls(g, 0);
		y_start = LOGO_HEIGHT;
		draw_sidewalls(g, 1);

		// ===================== WALLS =====================
		if (interpolate.status) {
			
			interpolate.interpolate(g);
			
		} else {
			// calcular si alguien ganó
			any_won(g);
			if (game_status != WIN) {
				// ===================== MOVIBLE BLOCKS =====================
				
				detect_posible_blocks(g);
				sound();
				
			}
		}

		// SET WALLS
		draw_walls(g);

		// ===================== PLAYERS =====================
		draw_player(g, 0, false, 0, 0);
		draw_player(g, 1, false, 0, 0);

	}

	/**
	 * dibuja el fondo del tablero
	 * @param g
	 */
	private void draw_background_table(Graphics g) {
		// ===================== RELENAR EL FONDO =====================
		g.setColor(BG_COLOR);
		g.fillRect(0, 0, TABLE_WIDTH, 900);
		g.setColor(BLOCK_COLOR);
		g.fillRect(0, (LOGO_HEIGHT + TABLE_SIDE), TABLE_WIDTH, TABLE_WIDTH);

		// ========== CONSTRUIR DONDE SE COLOCAN LAS PAREDES ==========
		g.setColor(SIDE_COLOR); // MARRÓN
		y_start = LOGO_HEIGHT;
		g.fillRect(0, y_start, TABLE_WIDTH, TABLE_SIDE);
		g.fillRect(0, TABLE_WIDTH + TABLE_SIDE + y_start, TABLE_WIDTH, TABLE_SIDE);
	}

	/**
	 * calcula si algun jugador ha ganado
	 * @param g
	 */
	private void any_won(Graphics g) {

		if (players[0].getY() == 1) {
			game_status = WIN;
			vg.game_over(players[0], players[1]);
			add_match(players[0]);
		}
		if (players[1].getY() == 17) {
			game_status = WIN;
			vg.game_over(players[1], players[0]);
			add_match(players[1]);
		}

	}

	String sentencia;

	/**
	 * añadir la partida a la base de datos
	 * @param player
	 */
	private void add_match(Player player) {

		try {

			sentencia = "INSERT INTO players VALUES(NULL, '" + player.getName() + "' , " + player.getMovements() + " , " + player.getWalls() + "  )";

			// conectamos a la base de datos
			con.connection = con.connect();
			con.statement = con.connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			con.statement.executeUpdate(sentencia);

		} catch (SQLException sqle) {
			System.out.println("Error 2-" + sqle.getMessage());
		} finally {
			con.closeConnection();
		}

	}

	/**
	 * 
	 * dadas unas coordanadas iniciales y otras finales, 
	 * centrar una cadena de texto en la superficie anterior
	 * 
	 * @param g
	 * @param color color de la fuente
	 * @param font	tipo de la fuente
	 * @param text	texto
	 * @param font_size	tamaño de la fuente
	 * @param x1	x inicial
	 * @param y1	y inicial
	 * @param x2	x final
	 * @param y2	y final
	 */
	private void center_string(Graphics g, Color color, String font, String text, int font_size, int x1, int y1, int x2, int y2) {

		g.setFont(new Font(font, Font.PLAIN, font_size));

		// width del texto con X fuente
		int text_width = g.getFontMetrics().stringWidth(text);
		
		// calcular el centro
		int center_x = x1 + ((x2 / 2) - (text_width / 2));
		int center_y = (int) (y1 + (y2 - ((y2 / 2) - (font_size * 0.30))));

		// dibujar el fondo del texto
		g.setColor(BG_COLOR);
		g.fillRect(center_x - 2, center_y - font_size - 2, text_width + 4, font_size + 4);

		g.setColor(color);
		g.drawString(text, center_x, center_y);
	}

	/**
	 * dibujar el jugador
	 * 
	 * @param g
	 * @param index
	 * @param pxl
	 * @param x
	 * @param y
	 */
	void draw_player(Graphics g, int index, boolean pxl, int x, int y) {

		// si las medidas están en Pixeles o Coordenadas
		if (!pxl) { // si son pixeles se convierten a coordenadas
			x = get_pixel_with_coor(players[index].getX());
			y = get_pixel_with_coor(players[index].getY()) + y_start;
		}

		// si se está interpolando una PARED
		// si se no eres el PLAYER que está siendo interpolado
		// si la interpolación se acabó
		if (pxl || interpolate.status == false || index != interpolate.last_turn || interpolate.mode != Interpolate.INTERPOLATE_PLAYER) {
			// si es el turno de ese jugador se le dibuja un contorno
			if (index == turn) {
				g.setColor(Color.BLACK);
				g.fillRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH);
				g.setColor(players[index].getColor());
				g.fillRect(x + LINE_WIDTH, y + LINE_WIDTH, BLOCK_WIDTH - (LINE_WIDTH * 2),
						BLOCK_WIDTH - (LINE_WIDTH * 2));
			} else {
				g.setColor(players[index].getColor());
				g.fillRect(x, y, BLOCK_WIDTH, BLOCK_WIDTH);
			}

			// dibujar el nombre del jugador
			center_string(g, Color.BLACK, "ComicSans", players[index].getName(), NAMES_FONT_SIZE, x, y, BLOCK_WIDTH, BLOCK_WIDTH);
		}

	}

	/**
	 * calcular las coordenadas en el mapa a partirde los pixeles clickados
	 * @param x
	 * @return
	 */
	public int get_pixel_with_coor(int x) {
		// calculamos las coordenadas en el canvas según la coordenada dentro del mapa
		// si es 9, lo dividimos entre 2, teniendo 5 y 4. Estos 5 lo multiplicamos por
		// el ancho de la pared y los 4 lo multiplicamos por el ancho del bloque;
		return ((x / 2) * BLOCK_WIDTH) + ((x - (x / 2)) * WALL_WIDTH);
	}

	/**
	 * detecta un click en el canvas
	 * @param x
	 * @param y
	 */
	public void click(int x, int y) {
		if(game_status != WIN) {
			clicked = true;
			x_click = x-8;
			y_click = y-31;
			System.out.println(x + ": " + y);
			repaint();
		}
		
	}

	public String[] getData() {
		repaint();
		//int current_turn = turn == 0 ? 1 : 0;
		String[] data = {players[turn].getName(), error, interpolate.status + ""};
		return data;
	}
	
	/**
	 * dibuja las paredes no puestas
	 * @param g
	 * @param i
	 */
	private void draw_sidewalls(Graphics g, int i) {
		g.setColor(WALL_COLOR);
		for (int x = 0; x < players[i].getWalls(); x++) {

			int x_init = (int) ((SIDE_PADDING * (x + 1)) + (x * ((SIDE_WIDTH - (10 * WALL_WIDTH)) / 9)));
			g.fillRect(x_init, (int) SIDE_PADDING + y_start, WALL_WIDTH, WALL_HEIGHT);
		}
	}

	/**
	 * dibuja las paredes puestas
	 * @param g
	 */
	private void draw_walls(Graphics g) {

		g.setColor(new Color(176, 157, 54));

		y_start = LOGO_HEIGHT + TABLE_SIDE;

		// si se está interpolando, dibujar una pared menos, pq está en movimiento
		int walls_ammount = (!interpolate.status || interpolate.mode != Interpolate.INTERPOLATE_WALLS)
				? walls_list.length
				: walls_list.length - 1;

		for (var j = 1; j < walls_ammount; j++) {

			if (walls_list[j] != null) {
				// get pixeles inicciales a dibujar con las coordenadas de las paredes
				int x_init = get_pixel_with_coor(walls_list[j][0]);
				int y_init = get_pixel_with_coor(walls_list[j][1]);

				if (walls_list[j][2] == HORIZONTAL) {
					// dibujar todo
					g.setColor(new Color(176, 157, 54));
					g.fillRect(y_init, x_init + y_start, WALL_HEIGHT, WALL_WIDTH);
					// dibujar sombra
					g.setColor(WALL_COLOR);
					g.fillRect(y_init, x_init + y_start, WALL_HEIGHT - LINE_WIDTH, WALL_WIDTH - LINE_WIDTH);
				}
				if (walls_list[j][2] == VERTICAL) {
					//dibujar todo
					g.setColor(new Color(176, 157, 54));
					g.fillRect(y_init, x_init + y_start, WALL_WIDTH, WALL_HEIGHT);
					//dibujar sombra
					g.setColor(WALL_COLOR);
					g.fillRect(y_init, x_init + y_start, WALL_WIDTH - LINE_WIDTH, WALL_HEIGHT - LINE_WIDTH);
				}
			}

		}

	}

	/**
	 * tipcal JavaScript push() función, quenoc siste en Java >:(
	 * @param array
	 * @param element
	 * @return
	 */
	public static int[][] append(int array[][], int element[]) {
		int newArr[][] = new int[array.length + 1][3];
		for (int x = 0; x < array.length; x++) {
			newArr[x] = array[x];
		}
		newArr[array.length] = element;
		return newArr;
	}
	// 
	/**
	 * quitar el último elemento de un Array
	 * jjajaj desAppend
	 * @param array
	 * @return
	 */
	public static int[][] desappend(int array[][]) {
		int newArr[][] = new int[array.length - 1][3];
		for (int x = 0; x < array.length - 1; x++) {
			newArr[x] = array[x];
		}
		return newArr;
	}

	/**
	 * Dibuja la maya del tabLero
	 * 
	 * @param g
	 */
	private void draw_maya(Graphics g) {
		y_start += TABLE_SIDE;
		// MAYA
		g.setColor(DOWN_WALL_COLOR);
		for (int y = 0; y < WALL_AMMOUNT; y++) {
			int y_init = (y * WALLBLOCK_WIDTH);
			g.fillRect(0, y_init + y_start, TABLE_WIDTH, WALL_WIDTH);
		}
		for (int x = 0; x < WALL_AMMOUNT; x++) {
			int x_init = (x * WALLBLOCK_WIDTH);
			g.fillRect(x_init, y_start, WALL_WIDTH, TABLE_WIDTH);
		}
	}

	/**
	 * Dadas unas coordenadas de donde se ha hecho click en el canvas. calcula la X
	 * y la Y de el bloque pulsado, entre 0:0 a 8:8
	 */
	public void detect_click_position() {
		
		// le restamos al eje Y, el espacio entre 0 y el comienzo del tablero
		y_click -= (LOGO_HEIGHT + TABLE_SIDE);

		int x_wallblock_pos = x_click % WALLBLOCK_WIDTH;
		int y_wallblock_pos = y_click % WALLBLOCK_WIDTH;

		// si click dentro del tablero
		if (y_click >= 0 && y_click <= TABLE_WIDTH) {

			// si se ha pulsado una WALL o un BLOCK
			// BLOCK, se mueve el jugador
			if ((x_wallblock_pos > WALL_WIDTH && x_wallblock_pos < WALLBLOCK_WIDTH) && (y_wallblock_pos > WALL_WIDTH && y_wallblock_pos < WALLBLOCK_WIDTH)) {

				move_player();

			} else { // PARED

				// si tiene paredes el ugador
				if (players[turn].getWalls() > 0) {
										
					// que no haga click en el centro entre paredes
					if (x_click % WALLBLOCK_WIDTH > WALL_WIDTH || y_click % WALLBLOCK_WIDTH > WALL_WIDTH) {
						
						if (x_click % WALLBLOCK_WIDTH < WALL_WIDTH) { // VERtical

							move_ver_wall();

						}
						if (x_click % WALLBLOCK_WIDTH > WALL_WIDTH) { // HORizontal

							move_hor_wall();
							
						}
					}
				}
			}
		}

		clicked = false;
	}

	/**
	 * calcular si el click ha sido efectuado en una coordenada correcta
	 * para cambiar la posición del jugafor
	 */
	private void move_player() {
		
		int x_block_pos = ((x_click / WALLBLOCK_WIDTH) * 2) + 1;
		int y_block_pos = ((y_click / WALLBLOCK_WIDTH) * 2) + 1;

		// para pruebas, movimientos ilegales
		valid_player_movement = MOVE_HACK;
		
		// detectar si es un bloque movible
		for (var j = 0; j < posible_blocks.length; j++) {
			if (posible_blocks[j][0] == x_block_pos && posible_blocks[j][1] == y_block_pos) {
				valid_player_movement = true;
				j = posible_blocks.length;
			}
		}

		if (valid_player_movement) {
			// BORRAR LAS COORDENADAS DEL PLAYER EN EL MAP
			map[players[turn].getY()][players[turn].getX()] = EMPTY;
			
			// asignar las coordenadas anteriores
			last_player_pos[0] = players[turn].getX();
			last_player_pos[1] = players[turn].getY();
			// cambiar la posicion del jugador
			players[turn].setX(x_block_pos);
			players[turn].setY(y_block_pos);

			// PONER LAS COORDENADAS DEL PLAYER EN EL MAP
			map[players[turn].getY()][players[turn].getX()] = PLAYER;

			// instanciar valores iniclaes de la interpolacion
			interpolate.get_initlast_interpolate_players();
			
			// añadir movimiento al jugador
			players[turn].addMovement();
			// cambiar de turno
			turn = (turn == 0) ? 1 : 0;
			// la ultima accion fue mover un jugador
			last_type_action = PLAYER;
			// vaciamos el Label de errores
			error = "";

		} else {
			last_type_action = -1;
			error = "Posición Inválida";
		}
		
	}
	
	/**
	 * calcular si el click ha sido efectuado en una coordenada correcta
	 * y así mover una pared vertical
	 */
	private void move_ver_wall() {
		
		int x_wall_pos = (y_click / WALLBLOCK_WIDTH) * 2;
		int y_wall_pos = (x_click / WALLBLOCK_WIDTH) * 2;

		// SI NO ESTÁ EN UN BORDE
		if (MAP_LEN > x_wall_pos + 3) {
			// SI NO PISO OTRA PARED
			if (map[x_wall_pos + 1][y_wall_pos] == EMPTY && map[x_wall_pos + 2][y_wall_pos] == EMPTY && map[x_wall_pos + 3][y_wall_pos] == EMPTY) {
				map[x_wall_pos + 1][y_wall_pos] = WALL;
				map[x_wall_pos + 2][y_wall_pos] = WALL;
				map[x_wall_pos + 3][y_wall_pos] = WALL;

				int[] wall_data = { x_wall_pos + 1, y_wall_pos, VERTICAL };
				walls_list = append(walls_list, wall_data);

				// instanciar valores iniclaes de la interpolacion
				interpolate.get_initlast_interpolate_walls();
				players[turn].lessWall();
				
				last_wall_player = turn;
				// cambiar de turno
				turn = (turn == 0) ? 1 : 0;
				// la ultima accion fue mover una pared
				last_type_action = WALL;
				// vaciamos el Label de errores
				error = "";
			} else {
				last_type_action = -1;
				error = "Pared encima de pared";
			}
		} else {
			last_type_action = -1;
			error = "Fuera del tablero";
		}
		
	}
	/**
	 * calcular si el click ha sido efectuado en una coordenada correcta
	 * y así mover una pared horizontal
	 */
	private void move_hor_wall() {
		int x_wall_pos = (y_click / WALLBLOCK_WIDTH) * 2;
		int y_wall_pos = (x_click / WALLBLOCK_WIDTH) * 2;

		// SI NO ESTÁ EN UN BORDE
		if (MAP_LEN > y_wall_pos + 3) {

			// SI NO PISO OTRA PARED
			if (map[x_wall_pos][y_wall_pos + 1] == EMPTY && map[x_wall_pos][y_wall_pos + 2] == EMPTY && map[x_wall_pos][y_wall_pos + 3] == EMPTY) {
				// añadir al mapa la pared
				map[x_wall_pos][y_wall_pos + 1] = WALL;
				map[x_wall_pos][y_wall_pos + 2] = WALL;
				map[x_wall_pos][y_wall_pos + 3] = WALL;
				
				// añadir a la lista de paredes, la pared
				int[] wall_data = { x_wall_pos, y_wall_pos + 1, HORIZONTAL };
				walls_list = append(walls_list, wall_data);

				// instanciar valores iniclaes de la interpolacion
				interpolate.get_initlast_interpolate_walls();
				// quitar pared al jugador
				players[turn].lessWall();
				// el ultimo turno
				last_wall_player = turn;
				// cambiar de turno
				turn = (turn == 0) ? 1 : 0;
				// la ultima accion fue mover una pared
				last_type_action = WALL;
				// vaciamos el Label de errores
				error = "";
			} else {
				last_type_action = -1;
				error = "Pared encima de pared";
			}
		} else {
			last_type_action = -1;
			error = "Fuera del tablero";
		}
	}


	/**
	 * emitir un sonido
	 */
	private void sound() {

		//System.out.println("Type: " + last_type_action);

		if (last_type_action != -1) {

			String sound_file = "";
			// audio aleatorio entre los 4
			int sound_index = (int) (Math.random() * 4);

			// según el movimiento, pared o jugador
			sound_file = (last_type_action == WALL) ? "w" + sound_index : "p" + sound_index;
			
			File sf = new File("./sounds/" + sound_file + ".wav");

			try {
				//cargar el audio y hacerlo sonar
				AudioFileFormat audioFileFormat = AudioSystem.getAudioFileFormat(sf);
				AudioInputStream ais = AudioSystem.getAudioInputStream(sf);
				AudioFormat af = audioFileFormat.getFormat();
				DataLine.Info info = new DataLine.Info(Clip.class, ais.getFormat(), ((int) ais.getFrameLength() * af.getFrameSize()));
				Clip ol = (Clip) AudioSystem.getLine(info);
				ol.open(ais);
				ol.loop(Clip.LOOP_CONTINUOUSLY);

				// Damos tiempo para que el sonido sea escuchado
				Long duration = (Long) audioFileFormat.properties().get("duration");
				if (duration == null) {
					duration = (long) (audioFileFormat.getFrameLength() / audioFileFormat.getFormat().getFrameRate() * 1000L);
				}
				duration = (long) (duration * 1.1);
				Thread.sleep(duration);
				ol.close();

			} catch (UnsupportedAudioFileException e) {
				System.out.println(e.getMessage());
			} catch (IOException e) {
				System.out.println(e.getMessage());
			} catch (LineUnavailableException LUE) {
				System.out.println(LUE.getMessage());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * vaciar el array de bloques donde se puede mover el jugador
	 */
	public void erase_posible_walls() {
		for (int x = 0; x < posible_blocks.length; x++) {
			posible_blocks[x][0] = -1;
			posible_blocks[x][1] = -1;
		}
	}

	/**
	 * dibujar los bloques donde se puede mover el jugador
	 * @param g
	 * @param own_y
	 * @param own_x
	 */
	void draw_clikable(Graphics g, int own_y, int own_x) {

		for (var x = 0; x < posible_blocks.length; x++) {
			if (posible_blocks[x][0] == -1) {
				posible_blocks[x][0] = own_x;
				posible_blocks[x][1] = own_y;
				x = posible_blocks.length;
			}
		}

		int x_init = get_pixel_with_coor(own_x);
		int y_init = get_pixel_with_coor(own_y);

		g.setColor(MOVEABLE_BLOCK);
		g.fillRect(x_init, y_init + y_start, BLOCK_WIDTH, BLOCK_WIDTH);
	}

	/**
	 * detectar los bloques donde se puede mover el jugador
	 * @param g
	 */
	public void detect_posible_blocks(Graphics g) {

		y_start = LOGO_HEIGHT + TABLE_SIDE;
		
		erase_posible_walls();

		int own_x = players[turn].getY();
		int own_y = players[turn].getX();

		// esto es totalmente incomentable ♥
		if (own_x + 2 < MAP_LEN) {
			// SI NO HAY PARED DELANTE
			if (map[own_x + 1][own_y] == EMPTY) {
				// is no hay player delante
				if (map[own_x + 2][own_y] != PLAYER) {
					draw_clikable(g, own_x + 2, own_y);
				} else { // si sí hay player
					// si no hay pared ahí
					if (map[own_x + 3][own_y] == EMPTY) {
						if (own_x + 4 < MAP_LEN) {
							if (map[own_x + 4][own_y] == EMPTY) {
								draw_clikable(g, own_x + 4, own_y);
							} else {
								// si hay una pared en el lado
								if (own_y - 2 < MAP_LEN) {
									if (map[own_x + 2][own_y - 1] == EMPTY) {
										if (map[own_x + 2][own_y - 2] == EMPTY) {
											draw_clikable(g, own_x + 2, own_y - 2);
										}
									}
								}
								if (own_y + 2 < MAP_LEN) {
									if (map[own_x + 2][own_y + 1] == EMPTY) {
										if (map[own_x + 2][own_y + 2] == EMPTY) {
											draw_clikable(g, own_x + 2, own_y + 2);
										}
									}
								}
							}
						}
					} else { // si sí hay pared en frente
						// si hay una pared en el lado
						if (own_y - 2 < MAP_LEN) {
							if (map[own_x + 2][own_y - 1] == EMPTY) {
								if (own_y - 2 > 0) {
									if (map[own_x + 2][own_y - 2] == EMPTY) {
										draw_clikable(g, own_x + 2, own_y - 2);
									}
								}
							}
						}
						if (own_y + 2 < MAP_LEN) {
							if (map[own_x + 2][own_y + 1] == EMPTY) {
								if (map[own_x + 2][own_y + 2] == EMPTY) {
									draw_clikable(g, own_x + 2, own_y + 2);
								}
							}
						}
					}
				}
			}
		}

		// is no hay muro en medio
		if (own_x - 2 > 0) {
			if (map[own_x - 1][own_y] == EMPTY) {
				// is no hay player delante
				if (map[own_x - 2][own_y] != PLAYER) {
					draw_clikable(g, own_x - 2, own_y);
				} else {
					// si sí hay player
					// si no hay muro
					if (map[own_x - 3][own_y] == EMPTY) {
						if (own_x - 4 > 0) {
							if (map[own_x - 4][own_y] == EMPTY) {
								draw_clikable(g, own_x - 4, own_y);
							} else {
								if (own_y - 2 > 0) {
									if (map[own_x - 2][own_y - 1] == 0) {
										if (map[own_x - 2][own_y - 2] == 1) {
											draw_clikable(g, own_x - 2, own_y - 2);
										}
									}
								}
								if (own_y + 2 > 0) {
									if (map[own_x - 2][own_y + 1] == 0) {
										if (map[own_x - 2][own_y + 2] == 1) {
											draw_clikable(g, own_x - 2, own_y + 2);
										}
									}
								}
							}
						}
					} else {
						if (own_y - 2 > 0) {
							if (map[own_x - 2][own_y - 1] == EMPTY) {
								if (map[own_x - 2][own_y - 2] == EMPTY) {
									draw_clikable(g, own_x - 2, own_y - 2);
								}
							}
						}
						if (own_y + 2 > 0) {
							if (map[own_x - 2][own_y + 1] == EMPTY) {
								if (map[own_x - 2][own_y + 2] == EMPTY) {
									draw_clikable(g, own_x - 2, own_y + 2);
								}
							}
						}
					}
				}
			}
		}

		// de izquierda a derecha
		// si no hay pared enfrente
		if (own_y + 2 < MAP_LEN) {
			if (map[own_x][own_y + 1] == EMPTY) {
				if (map[own_x][own_y + 2] != PLAYER) {
					draw_clikable(g, own_x, own_y + 2);
				} else {
					if (map[own_x][own_y + 3] == EMPTY) {
						if (own_y + 4 < MAP_LEN) {
							if (map[own_x][own_y + 4] == EMPTY) {
								draw_clikable(g, own_x, own_y + 4);
							} else {
								if (own_x + 2 < MAP_LEN) {
									if (map[own_x + 1][own_y + 2] == EMPTY) {
										if (map[own_x + 2][own_y + 2] == 0) {
											draw_clikable(g, own_x + 2, own_y + 2);
										}
									}
								}
								if (own_x - 2 < MAP_LEN) {
									if (map[own_x - 1][own_y + 2] == EMPTY) {
										if (map[own_x - 2][own_y + 2] == EMPTY) {
											draw_clikable(g, own_x - 2, own_y + 2);
										}
									}
								}
							}
						}
					} else {
						if (own_x + 2 < MAP_LEN) {
							if (map[own_x + 1][own_y + 2] == EMPTY) {
								if (map[own_x + 2][own_y + 2] == EMPTY) {
									draw_clikable(g, own_x + 2, own_y + 2);
								}
							}
						}
						if (own_x - 2 < MAP_LEN) {
							if (map[own_x - 1][own_y + 2] == EMPTY) {
								if (map[own_x - 2][own_y + 2] == EMPTY) {
									draw_clikable(g, own_x - 2, own_y + 2);
								}
							}
						}
					}
				}
			}
		}

		if (own_y - 2 > 0) {
			if (map[own_x][own_y - 1] == EMPTY) {
				if (map[own_x][own_y - 2] != PLAYER) {
					draw_clikable(g, own_x, own_y - 2);
				} else {
					if (map[own_x][own_y - 3] == EMPTY) {
						if (own_y - 4 > 0) {
							if (map[own_x][own_y - 4] == EMPTY) {
								draw_clikable(g, own_x, own_y - 4);
							} else {
								if (own_x + 2 < MAP_LEN) {
									if (map[own_x + 1][own_y - 2] == EMPTY) {
										if (map[own_x + 2][own_y - 2] == EMPTY) {
											draw_clikable(g, own_x + 2, own_y - 2);
										}
									}
								}
								if (own_x - 2 > 0) {
									if (map[own_x - 1][own_y - 2] == EMPTY) {
										if (map[own_x - 2][own_y - 2] == EMPTY) {
											draw_clikable(g, own_x - 2, own_y - 2);
										}
									}
								}
							}
						}
					} else {
						if (own_x + 2 < MAP_LEN) {
							if (map[own_x + 1][own_y - 2] == EMPTY) {
								if (map[own_x + 2][own_y - 2] == EMPTY) {
									draw_clikable(g, own_x + 2, own_y - 2);
								}
							}
						}
						if (own_x - 2 > 0) {
							if (map[own_x - 1][own_y - 2] == EMPTY) {
								if (map[own_x - 2][own_y - 2] == EMPTY) {
									draw_clikable(g, own_x - 2, own_y - 2);
								}
							}
						}
					}
				}
			}
		}

	}

	/*public void show_map() {
		for (int x = 0; x < MAP_LEN; x++) {
			for (int y = 0; y < MAP_LEN; y++) {
				if (map[x][y] == EMPTY) {
					System.out.print(". ");
				} else if (map[x][y] == PLAYER) {
					System.out.print("P ");
				} else {
					System.out.print("[]");
				}

			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}*/

	/*public void show_walls() {
		for (int x = 0; x < walls_list.length; x++) {
			for (int y = 0; y < 3; y++) {
				if (walls_list[x] != null)
					System.out.print(walls_list[x][y] + " ");
			}
			System.out.println(" ");
		}
		System.out.println(" ");
	}*/

	

	/**
	 * quitar la última pared puesta
	 */
	public void undowall() {

		// si hay alguna pared colocada
		if (walls_list.length != 1) {			
			
			// quitamos del mapa la pared
			int index = walls_list.length - 1;
			if (walls_list[index][2] == HORIZONTAL) {

				map[walls_list[index][0]][walls_list[index][1]] = EMPTY;
				map[walls_list[index][0]][walls_list[index][1] + 1] = EMPTY;
				map[walls_list[index][0]][walls_list[index][1] + 2] = EMPTY;

			} else {
				map[walls_list[index][0]][walls_list[index][1]] = EMPTY;
				map[walls_list[index][0] + 1][walls_list[index][1]] = EMPTY;
				map[walls_list[index][0] + 2][walls_list[index][1]] = EMPTY;
			}

			// si la ultima accion es poner una pared, cambia de turno
			if (last_type_action == WALL)
				turn = (turn == 0) ? 1 : 0;

			// le añadimos una pared al jugador
			players[last_wall_player].setWalls(players[last_wall_player].getWalls() + 1);
			// ultimo en poner pared
			last_wall_player = (last_wall_player==0) ? 1 : 0;
			//walls_list[index] = null;
			walls_list = desappend(walls_list);
			last_type_action = -1;

			repaint();
		}
	}
}
