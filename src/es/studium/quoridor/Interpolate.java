package es.studium.quoridor;

import java.awt.Graphics;

public class Interpolate {

	static int INTERPOLATE_MAX = 200;
	double interpolate_index = 0;
	boolean status = false;
	static boolean INTERPOLATE_WALLS = false;
	static boolean INTERPOLATE_PLAYER = true;
	boolean mode;
	int interpolate_x_init = 0;
	int interpolate_y_init = 0;
	int interpolate_x_last = 0;
	int interpolate_y_last = 0;
	int interpolate_dir = 0;
	int[] dot1 = { 0, 0 };
	int[] dot2 = { 0, 0 };
	double x_dis = 0;
	double y_dis = 0;
	double x_to_y_dis = 0;
	int last_turn = 0;
	static int BLOCK = 2;
	
	ModeloGame mc;
	
	public Interpolate(ModeloGame mc) {
		this.mc = mc;
	}

	/**
	 * calcular los ejes X e Y del punto inicial y final de la interpolación del jugador
	 */
	void get_initlast_interpolate_players() {
		mode = INTERPOLATE_PLAYER;
		last_turn = mc.turn;
		//g.setColor(Color.BLACK);

		// calcular los ejes X e Y iniciales y finales de la interpolación
		interpolate_x_init = mc.get_pixel_with_coor(mc.last_player_pos[0]);
		interpolate_y_init = mc.LOGO_HEIGHT + mc.TABLE_SIDE + mc.get_pixel_with_coor(mc.last_player_pos[1]);
		interpolate_x_last = mc.get_pixel_with_coor(mc.players[last_turn].getX());
		interpolate_y_last = mc.LOGO_HEIGHT + mc.TABLE_SIDE + mc.get_pixel_with_coor(mc.players[last_turn].getY());
		interpolate_dir = BLOCK;

		//g.drawLine(interpolate_x_init, interpolate_y_init, interpolate_x_last, interpolate_y_last);

		// punto inicial
		dot1[0] = interpolate_x_init;
		dot1[1] = interpolate_y_init;
		// punto final
		dot2[0] = interpolate_x_last;
		dot2[1] = interpolate_y_last;
		// distancias
		x_dis = Math.abs(dot1[0] - dot2[0]);
		y_dis = Math.abs(dot1[1] - dot2[1]);
		x_to_y_dis = Math.sqrt((x_dis * x_dis) + (y_dis * y_dis));

		status = true;
	}
	/**
	 * calcular los ejes X e Y del punto inicial y final de la interpolación de la pared
	 */
	public void get_initlast_interpolate_walls() {

		mode = INTERPOLATE_WALLS;
		last_turn = mc.turn;
		
		//g.setColor(Color.BLACK);

		// calcular los ejes X e Y iniciales y finales de la interpolación
		int paredes_restantes = mc.players[last_turn].getWalls() - 1;
		interpolate_x_init = (int) ((mc.SIDE_PADDING * (paredes_restantes + 1)) + (paredes_restantes * ((mc.SIDE_WIDTH - (10 * mc.WALL_WIDTH)) / 9)));
		if (last_turn == 0) {
			interpolate_y_init = mc.LOGO_HEIGHT + mc.TABLE_SIDE + mc.TABLE_WIDTH + mc.SIDE_PADDING;
		} else {
			interpolate_y_init = mc.LOGO_HEIGHT + mc.SIDE_PADDING;
		}
		interpolate_y_last = mc.get_pixel_with_coor(mc.walls_list[mc.walls_list.length - 1][0]) + (mc.LOGO_HEIGHT + mc.TABLE_SIDE);
		interpolate_x_last = mc.get_pixel_with_coor(mc.walls_list[mc.walls_list.length - 1][1]);
		interpolate_dir = mc.walls_list[mc.walls_list.length - 1][2];

		//g.drawLine(interpolate_x_init, interpolate_y_init, interpolate_x_last, interpolate_y_last);

		// punto inicial
		dot1[0] = interpolate_x_init;
		dot1[1] = interpolate_y_init;
		// punto final
		dot2[0] = interpolate_x_last;
		dot2[1] = interpolate_y_last;
		// distancias
		x_dis = Math.abs(dot1[0] - dot2[0]);
		y_dis = Math.abs(dot1[1] - dot2[1]);
		x_to_y_dis = Math.sqrt((x_dis * x_dis) + (y_dis * y_dis));

		status = true;
	}

	

	

	/**
	 * Funcion de recursión que interpola entre dos puntos dados
	 * @param g
	 */
	public void interpolate(Graphics g) {

		
		// System.out.println(interpolate_x_init + ":" + interpolate_y_init);
		// System.out.println(interpolate_x_last + ":" + interpolate_y_last);

		if (interpolate_index < INTERPOLATE_MAX) {

			
			double pcen = interpolate_index / INTERPOLATE_MAX;
			double x = ((x_dis / INTERPOLATE_MAX) * interpolate_index);

			// TEOREMA DE PITAGORAS
			double c = (x_to_y_dis * pcen);
			double b = (x_dis * pcen);
			double y = Math.sqrt((c * c) - (b * b));

			// según si las coordenadas son negativas respecto a su eje
			if (dot1[0] > dot2[0]) {
				x = x_dis - x;
				x += dot1[0] - x_dis;
			} else {
				x += dot1[0];
			}
			if (dot1[1] > dot2[1]) {
				y = y_dis - y;
				y += (dot1[1] - y_dis);
			} else {
				y += dot1[1];
			}

			//System.out.println("Pos " + x + ":" + y);
			// según el tipo de interpolación
			g.setColor(mc.WALL_COLOR);
			if (interpolate_dir == mc.HORIZONTAL) {
				g.fillRect((int) x, (int) y, mc.WALL_HEIGHT, mc.WALL_WIDTH);
			} else if (interpolate_dir == mc.VERTICAL) {
				g.fillRect((int) x, (int) y, mc.WALL_WIDTH, mc.WALL_HEIGHT);
			} else {
				mc.draw_player(g, last_turn, true, (int)x, (int)y);
			}

			interpolate_index++;
			
			mc.repaint();
			

		} else {

			interpolate_index = 0;
			status = false;
			
		}
		
		mc.repaint();
	}
	
}
