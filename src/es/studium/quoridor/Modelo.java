package es.studium.quoridor;

import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Modelo extends Connections {

	static int GOOD = 0;
	static int ERROR_LENGTH = 2;
	static int ERROR_CHAR = 1;
	static int ERROR_EQUALS = 3;
	
	/**
	 * se encarga de corregir los nombres de los jugadores
	 * @param name1
	 * @param name2
	 * @return
	 */
	public int correctNames(String name1, String name2) {

		// nombres con longitud no permitida
		
		if (name1.length() >= 3 && name1.length() <= 45 && name2.length() >= 3 && name2.length() <= 45) {
			// nombres !iguales
			if ( !name1.equals(name2) ) {
				Pattern pattern1 = Pattern.compile("[a-zA-Z0-9/_/.]+");
				Matcher matcher1 = pattern1.matcher(name1);
				// car?cteres no permitidos
				if (!matcher1.matches()) {
					return ERROR_CHAR;
				}
				
				Pattern pattern2 = Pattern.compile("[a-zA-Z0-9/_/.]+");
				Matcher matcher2 = pattern2.matcher(name2);
				// car?cteres no permitidos
				if (matcher2.matches()) {
					return GOOD;
				} else {
					return ERROR_CHAR;
				}
			} else {
				return ERROR_EQUALS;
			}
		} else {
			return ERROR_LENGTH;
		}
	}

	JTable jTable;
	String[] columnNames = { "Top", "Nombre", "Movimientos", "Paredes" };
	String[][] data = new String[10][4];
	JScrollPane scrollPanel;
	
	/**
	 * hace una conexion con la base de datos para traer las 10 mejores partidas y as? a?adirlas a la tabla
	 * @param top10
	 */
	public void add_top10(VistaTop10 top10) {

		String sentence;
		int index = 0;

		try {

			sentence = "SELECT * FROM players ORDER BY movementsPlayer, wallsPlayer DESC LIMIT 10;";
			connection = connect();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentence);

			while (rs.next()) {

				// a?adimos al array los datos
				data[index][0] = (index + 1) + "";
				data[index][1] = rs.getString(2);
				data[index][2] = rs.getString(3);
				data[index][3] = rs.getString(4);

				index++;
			}

		} catch (SQLException sqle) {
			System.out.println("Error 2-" + sqle.getMessage());
		} finally {
			closeConnection();
		}

		// le damos los datos como columnas
		jTable = new JTable(data, columnNames);
		scrollPanel = new JScrollPane(jTable);
		scrollPanel.setPreferredSize(new Dimension(300, 220));

		top10.lbl_loading.setVisible(false);
		top10.add(scrollPanel);
	}

}
