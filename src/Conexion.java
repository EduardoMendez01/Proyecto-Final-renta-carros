import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.jdbc.Statement;

public class Conexion {
	Connection conexion = null;
	Statement stm = null;
	ResultSet rs = null;
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	//EN ESTA LINEA SE PONE EL PUERTO AL QUE TE CONECTAS EN TU WORKBENCH Y EL NOMBRE QUE TIENE LA BD
	private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
	private static final String USUARIO = "root";
	//EN ESTA LINEA SE PONE LA CONTRASEÑA QUE CONFIGURASTE PARA TU USUARIO A LA HORA DE INSTALAR WORKBENCH
	private static final String CLAVE = "";

	public Connection conectar() {
		Connection conexion = null;

		try {
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

		} catch (SQLException e) {
			System.out.println("Error en la conexión");
			e.printStackTrace();
		}

		return conexion;
	}

	/*
	public void consultar() {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");

			while (rs.next()) {
				int idUsuario = rs.getInt(1);
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				String numero_telefono = rs.getString(4);
				String contrasena = rs.getString(5);
				String fecha_nacimiento = rs.getString(6);

				System.out.println(idUsuario + " - " + nombre + " - " + apellidos + " - " + numero_telefono + " - "
						+ contrasena + " - " + fecha_nacimiento);
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/

	public void validar_Inicio_Sesion(JTextField cliente, JPasswordField pwd) {
		conexion = null;
		stm = null;
		rs = null;
		boolean acceso = false;
		
		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM cliente");

			while (rs.next()) {
				String nombre = rs.getString(2);
				String apellidos = rs.getString(3);
				String contrasena = rs.getString(5);
				String nombre_completo = nombre + " " + apellidos;
				String contrasena_Formato_String = new String(pwd.getPassword());

				if(nombre_completo.equals(cliente.getText()) && contrasena.equals(contrasena_Formato_String)) {
					acceso = true;
					break;
				}
			}
			if(acceso) {
				JOptionPane.showMessageDialog(null, "Bienvenido");
			}else {
				JOptionPane.showMessageDialog(null, "Error. Datos incorrectos");
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
