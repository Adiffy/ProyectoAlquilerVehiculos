package principal;

import java.sql.SQLException;
import java.util.ArrayList;

import db.Acceso;

public class Principall {

	public static void main(String[] args) {

		ArrayList<String> lista;
		Acceso.abreConexion();	//Creamos la conexion con Base de datos
		
		try {
			lista = Acceso.selectString("SELECT NOMBRE FROM EMPLEADO","NOMBRE");
			for (Object a: lista)	//Recorremos el ArrayList
			{
				System.out.println(a);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
