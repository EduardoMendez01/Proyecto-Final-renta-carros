import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Conexion {
	Connection conexion = null;
	Statement stm = null;
	ResultSet rs = null;
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	// EN ESTA LINEA SE PONE EL PUERTO AL QUE TE CONECTAS EN TU WORKBENCH Y EL
	// NOMBRE QUE TIENE LA BD
	private static final String URL = "jdbc:mysql://localhost:3306/car_rental";
	private static final String USUARIO = "root";
	// EN ESTA LINEA SE PONE LA CONTRASEÑA QUE CONFIGURASTE PARA TU USUARIO A LA
	// HORA DE INSTALAR WORKBENCH
	private static final String CLAVE = "Bahialucila219";

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

	public boolean validar_Inicio_Sesion(JTextField cliente, JPasswordField pwd) {
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

				if (nombre_completo.equals(cliente.getText()) && contrasena.equals(contrasena_Formato_String)) {
					acceso = true;
					break;
				}
			}
			if (acceso) {
				JOptionPane.showMessageDialog(null, "Bienvenido");
			} else {
				JOptionPane.showMessageDialog(null, "Error. Datos incorrectos");
				acceso = false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return acceso;
	}

	public void consultar_Categorias(DefaultTableModel tabla) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				tabla.addRow(new Object[] { rs.getString(1), rs.getInt(2), rs.getString(3), rs.getInt(4) });
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean añadir_Categorias(JTextField nombre, JTextField cant_llantas, JTextField uso, JTextField peso) {
		conexion = null;
		stm = null;
		rs = null;
		Boolean existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				if (rs.getString(1).equals(nombre.getText())) {
					existente = true;
				}
			}

			if (existente == false) {
				PreparedStatement stm = (PreparedStatement) conexion
						.prepareStatement("INSERT INTO categoria VALUE(?,?,?,?)");
				stm.setString(1, nombre.getText().trim());
				stm.setInt(2, Integer.valueOf(cant_llantas.getText()));
				stm.setString(3, uso.getText().trim());
				stm.setInt(4, Integer.valueOf(peso.getText()));
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}

	public void llenar_CMB_Categorias(JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				cmb.addItem(rs.getString(1));
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean editar_Categoria(JTextField nombre, JTextField cant_llantas, JTextField uso, JTextField peso,
			JComboBox cmb) {
		conexion = null;
		stm = null;
		rs = null;
		Boolean existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			stm = (Statement) conexion.createStatement();
			rs = stm.executeQuery("SELECT * FROM categoria");

			while (rs.next()) {
				if (rs.getString(1).equals(nombre.getText())) {
					existente = true;
				}
			}

			if (existente == false) {
				PreparedStatement stm = (PreparedStatement) conexion.prepareStatement(
						"UPDATE categoria set nombre = ?, cantidad_llantas = ?, uso = ?, peso_promedio = ? where nombre = Autobus");
				stm.setString(1, nombre.getText().trim());
				stm.setInt(2, Integer.valueOf(cant_llantas.getText()));
				stm.setString(3, uso.getText().trim());
				stm.setInt(4, Integer.valueOf(peso.getText()));
				stm.executeUpdate();
			}
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}

	public boolean eliminar_Categoria(JTextField nombre) {
		conexion = null;
		stm = null;
		rs = null;
		Boolean existente = false;

		try {
			Class.forName(CONTROLADOR);
			conexion = DriverManager.getConnection(URL, USUARIO, CLAVE);
			System.out.println("Conexión OK");

			PreparedStatement stm = (PreparedStatement) conexion
					.prepareStatement("delete from categoria where nombre = " + nombre.getText());
			
			stm.executeUpdate();

		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return existente;
	}
}
