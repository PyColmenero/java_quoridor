package es.studium.quoridor;

import java.awt.Color;

public class Player {

	private String name;
	private int x;
	private int y;
	private Color color;
	private int walls;
	private int movements;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getWalls() {
		return walls;
	}
	public void setWalls(int walls) {
		this.walls = walls;
	}
	public void lessWall() {
		this.setWalls(walls-1);
	}
	public void addMovement() {
		movements++;
	}
	
	public Player(String name, int x, int y, Color color, int walls, int movements) {
		this.name = name;
		this.x = x;
		this.y = y;
		this.color = color;
		this.walls = walls;
		this.movements = movements;
	}
	public int getMovements() {
		return movements;
	}
	public void setMovements(int movements) {
		this.movements = movements;
	}
	
	/**
	 * -No poder colocar mas
	 * -Enseñar cuantos muros tienen
	 * Interpolación
	 * Ganar
	 */
	
	

}
