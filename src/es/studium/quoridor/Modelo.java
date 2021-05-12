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
	
	public int correctNames(String name1, String name2) {

		// nombres iguales
		if (name1.equals(name2)) {
			return ERROR_EQUALS;
		} else {
			
			// nombres con longitud no permitida
			if (name1.length() >= 3 && name1.length() <= 45) {
				Pattern pattern = Pattern.compile("[a-zA-Z0-9/_]+");
				Matcher matcher = pattern.matcher(name1);
				// carácteres no permitidos
				if (!matcher.matches()) {
					return ERROR_CHAR;
				}
			} else {
				return ERROR_LENGTH;
			}
			
			// nombres con longitud no permitida
			if (name2.length() >= 3 && name2.length() <= 45) {
				Pattern pattern = Pattern.compile("[a-zA-Z0-9/_/.]+");
				Matcher matcher = pattern.matcher(name2);
				// carácteres no permitidos
				if (matcher.matches()) {
					return GOOD;
				} else {
					return ERROR_CHAR;
				}
			} else {
				return ERROR_LENGTH;
			}
		}
	}

	JTable jTable;
	String[] columnNames = { "Top", "Nombre", "Movimientos", "Paredes" };
	String[][] data = new String[10][4];
	JScrollPane scrollPanel;
	
	public void add_top10(VistaTop10 top10) {

		String sentence;
		int index = 0;

		try {

			sentence = "SELECT * FROM players ORDER BY movementsPlayer, wallsPlayer DESC LIMIT 10;";
			connection = connect();
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = statement.executeQuery(sentence);

			while (rs.next()) {

				// añadimos al array los datos
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
		top10.ventana.add(scrollPanel);
	}

}
