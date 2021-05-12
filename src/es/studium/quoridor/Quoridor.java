package es.studium.quoridor;

public class Quoridor {

	public static void main(String[] args) {
		VistaMain vistaMain = new VistaMain();
		Modelo modelo = new Modelo();
		new ControladorMain(vistaMain, modelo); 
	}

}

/*

VISTA/CONTROLADOR MainFrame
VISTA/CONTROLADOR Ayuda
VISTA/CONTROLADOR/MODELO Top 10
VISTA/CONTROLADOR Nombres
VISTA/CONTROLADOR/MODELO Juego

*/